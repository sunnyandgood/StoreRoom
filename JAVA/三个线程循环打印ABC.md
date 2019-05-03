<div align="center"><h1>三个线程循环打印ABC</h1></div>

## 题目：

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;实例化三个线程，一个线程打印A，一个线程打印B，一个线程打印C，三个线程同时执行，要求打印出10个连着的ABC。


## 题目分析：

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;令打印字符A的线程为ThreadA，打印B的ThreadB，打印C的为ThreadC。问题为三线程间的同步唤醒操作，主要的目的就是使程序按ThreadA->ThreadB->ThreadC->ThreadA循环执行三个线程。

## 解题

* <h3>方法一：通过两个锁(不推荐，可读性和安全性比较差)</h3>

  ```java
  package com.edu.test.multithreading.printabc;
  
  /**
   * 基于两个lock实现连续打印abcabc....
   * @Author: 王仁洪
   * @Date: 2019/4/3 20:32
   */
  public class TwoLockPrinter implements Runnable {
      // 打印次数
      private static final int PRINT_COUNT = 10;
      // 前一个线程的打印锁
      private final Object fontLock;
      // 本线程的打印锁
      private final Object thisLock;
      // 打印字符
      private final char printChar;
  
      public TwoLockPrinter(Object fontLock, Object thisLock, char printChar) {
          this.fontLock = fontLock;
          this.thisLock = thisLock;
          this.printChar = printChar;
      }
  
      @Override
      public void run() {
          // 连续打印PRINT_COUNT次
          for (int i = 0; i < PRINT_COUNT; i++) {
              // 获取前一个线程的打印锁
              synchronized (fontLock) {
                  // 获取本线程的打印锁
                  synchronized (thisLock) {
                      //打印字符
                      System.out.print(printChar);
                      // 通过本线程的打印锁唤醒后面的线程
                      // notify和notifyall均可,因为同一时刻只有一个线程在等待
                      thisLock.notify();
                  }
                  // 不是最后一次则通过fontLock等待被唤醒
                  // 必须要加判断，不然虽然能够打印10次，但10次后就会直接死锁
                  if(i < PRINT_COUNT - 1){
                      try {
                          // 通过fontLock等待被唤醒
                          fontLock.wait();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                  }
              }
          }
      }
  
      public static void main(String[] args) throws InterruptedException {
          // 打印A线程的锁
          Object lockA = new Object();
          // 打印B线程的锁
          Object lockB = new Object();
          // 打印C线程的锁
          Object lockC = new Object();
  
          // 打印a的线程
          Thread threadA = new Thread(new TwoLockPrinter(lockC, lockA, 'A'));
          // 打印b的线程
          Thread threadB = new Thread(new TwoLockPrinter(lockA, lockB, 'B'));
          // 打印c的线程
          Thread threadC = new Thread(new TwoLockPrinter(lockB, lockC, 'C'));
  
          // 依次开启a b c线程
          threadA.start();
          Thread.sleep(100); // 确保按顺序A、B、C执行
          threadB.start();
          Thread.sleep(100);
          threadC.start();
          Thread.sleep(100);
      }
  }
  
  //ABCABCABCABCABCABCABCABCABCABC
  ```

	* 分析：
		* 此解法为了为了确定唤醒、等待的顺序，每一个线程必须同时持有两个对象锁，才能继续执行。
		* 一个对象锁是fontLock，就是前一个线程所持有的对象锁，还有一个就是自身对象锁thisLock。
		* **主要思想**就是，为了控制执行的顺序，必须要先持有fontLock锁，也就是前一个线程要释放掉前一个线程自身的对象锁，当前线程再去申请自身对象锁，两者兼备时打印，之后首先调用thisLock.notify()释放自身对象锁，唤醒下一个等待线程，再调用fontLock.wait()释放prev对象锁，暂停当前线程，等待再次被唤醒后进入循环。
		* 运行上述代码，可以发现三个线程循环打印ABC，共10次。程序运行的**主要过程**就是A线程最先运行，持有C，A对象锁，后释放A锁，唤醒B。线程B等待A锁，再申请B锁，后打印B，再释放B锁，唤醒C，线程C等待B锁，再申请C锁，后打印C，再释放C锁，唤醒A。
		* 看起来似乎没什么问题，但如果你仔细想一下，就会发现有问题，就是初始条件，三个线程按照A，B，C的顺序来启动，按照前面的思考，A唤醒B，B唤醒C，C再唤醒A。但是这种假设依赖于JVM中线程调度、执行的顺序，所以需要手动控制他们三个的启动顺序，即Thread.Sleep(100)。


- <h3>方法二：通过一个ReentrantLock和三个conditon实现(推荐，安全性，性能和可读性较高)（java多线程编程核心技术P234页）</h3>

  ```java
  import java.util.concurrent.locks.Condition;
  import java.util.concurrent.locks.ReentrantLock;
  
  /**
   * @Author: 王仁洪
   * @Date: 2019/4/18 9:26
   */
  public class ThreadPrintABC {
      volatile private static char nextPrintWho = 'A';
      private static ReentrantLock lock = new ReentrantLock();
      final private static Condition CONDITIONA = lock.newCondition();
      final private static Condition CONDITIONB = lock.newCondition();
      final private static Condition CONDITIONC = lock.newCondition();
      public static void main(String[] args) {
          Thread threadA = new Thread(){
              @Override
              public void run() {
                  try {
                      lock.lock();
                      while (nextPrintWho != 'A'){
                          CONDITIONA.await();
                      }
                      System.out.print("A");
                      nextPrintWho = 'B';
                      CONDITIONB.signalAll();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }
              }
          };
  
          Thread threadB = new Thread(){
              @Override
              public void run() {
                  try {
                      lock.lock();
                      while (nextPrintWho != 'B'){
                          CONDITIONB.await();
                      }
                      System.out.print("B");
                      nextPrintWho = 'C';
                      CONDITIONC.signalAll();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }
              }
          };
  
          Thread threadC = new Thread(){
              @Override
              public void run() {
                  try {
                      lock.lock();
                      while (nextPrintWho != 'C'){
                          CONDITIONC.await();
                      }
                      System.out.print("C");
                      nextPrintWho = 'A';
                      CONDITIONA.signalAll();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }finally {
                      lock.unlock();
                  }
              }
          };
  
          threadA.start();
          threadB.start();
          threadC.start();
          //ABC
  
  //        Thread[] a = new Thread[5];
  //        Thread[] b = new Thread[5];
  //        Thread[] c = new Thread[5];
  //        for (int i=0;i<5;i++){
  //            a[i] = new Thread(threadA);
  //            b[i] = new Thread(threadB);
  //            c[i] = new Thread(threadC);
  //            a[i].start();
  //            b[i].start();
  //            c[i].start();
  //        }
      }
  }
  ```
- <h3>方法三：（java多线程编程核心技术P292页）</h3>
  ```java
  /**
   * @Author: sunnyandgood
   * @Date: 2019/4/21 19:14
   */
  public class Mythread extends Thread {
      private Object lock;
      private String showChar;
      private int showNumPosition;
      private int printCount = 0;//统计打印了几个字母
      volatile private static int addNumber = 1;
  
      public Mythread(Object lock,String showChar,int showNumPosition){
          super();
          this.lock = lock;
          this.showChar = showChar;
          this.showNumPosition = showNumPosition;
      }
  
      @Override
      public void run() {
          try {
              synchronized (lock){
                  while (true){
                      if (addNumber % 4 == showNumPosition){
                          //System.out.println("ThreadName=" + Thread.currentThread().getName() +
                           //       " runCount=" + addNumber + " " + showChar);
                          System.out.print(showChar);
                          lock.notifyAll();
                          addNumber++;
                          printCount++;
                          if (printCount == 1){
                              break;
                          }
                      }else {
                          lock.wait();
                      }
                  }
              }
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }
  
      public static void main(String[] args) {
          Object lock = new Object();
          Mythread a = new Mythread(lock,"A",1);
          Mythread b = new Mythread(lock,"B",2);
          Mythread c = new Mythread(lock,"C",3);
          Mythread d = new Mythread(lock,"D",0);
          a.start();
          b.start();
          c.start();
          d.start();
          //ABCD
      }
  }
  ```
