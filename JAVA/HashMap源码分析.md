# HashMap源码分析

数组+链表（红黑树（查找效率高））

### 一、jdk1.7当hash冲突时插入到链表头部速度快，在将位置向下移动（在扩容时会造成死循环）

```java
//默认初始容量（16）,必须是2的幂
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//最大容量  1073741824
static final int MAXIMUM_CAPACITY = 1 << 30;
//默认加载因子
static final float DEFAULT_LOAD_FACTOR = 0.75f;

//HashMap的大小
transient int size;
//HashMap的加载因子
final float loadFactor;
//阈值(capacity * load factor)
int threshold;
//此字段用于使哈希映射的集合视图上的迭代器快速失败。（参见ConcurrentModificationException）。
//一个计数器
transient int modCount;

/**
 * An empty table instance to share when the table is not inflated.
 * 当表未膨胀时要共享的空表实例。
 */
static final Entry<?,?>[] EMPTY_TABLE = {};

/**
 * The table, resized as necessary. Length MUST Always be a power of two.
 * HashMap维护的表，根据需要调整，长度必须是2的幂
 */
transient Entry<K,V>[] table = (Entry<K,V>[]) EMPTY_TABLE;

//映射容量的默认阈值，在该阈值之上，对字符串键使用可选散列。由于字符串键的散列代码计算能力较弱，可选散列可以减少冲突的发生率。
//替代哈希阈值默认
static final int ALTERNATIVE_HASHING_THRESHOLD_DEFAULT = Integer.MAX_VALUE;
```
- 1、创建

  - `HashMap()`
      ```java
      public HashMap() {//16,0.75
          this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
      }
      ```

  - `HashMap(int initialCapacity)`
      ```java
      public HashMap(int initialCapacity) {//initialCapacity,0.75
          this(initialCapacity, DEFAULT_LOAD_FACTOR);
      }
      ```

  - `HashMap(int initialCapacity, float loadFactor)`

      ```java
      public HashMap(int initialCapacity, float loadFactor) {
          if (initialCapacity < 0)
              throw new IllegalArgumentException("Illegal initial capacity: " +
                      initialCapacity);
          if (initialCapacity > MAXIMUM_CAPACITY)
              initialCapacity = MAXIMUM_CAPACITY;
          if (loadFactor <= 0 || Float.isNaN(loadFactor))
              throw new IllegalArgumentException("Illegal load factor: " +
                      loadFactor);
      
          this.loadFactor = loadFactor;
          threshold = initialCapacity;
          init();
      }
      ```

  - 创建一个HashMap

    - 1）若initialCapacity小于0，则抛出IllegalArgumentException异常
    - 2）若initialCapacity大于MAXIMUM_CAPACITY（最大容量 1 << 30），则将initialCapacity置为最大容量
    - 3）若loadFactor小于等于0（或loadFactor是NaN），则抛出IllegalArgumentException异常
    - 4）将HashMap的加载因子（loadFactor）赋值为方法参数loadFactor
    - 5）将HashMap的阈值（阈值）赋值为方法参数initialCapacity

- 2、put操作：`put(K key, V value)`

    - 1）若HashMap的table属性是空数组（数组容量为0）
      - 若threshold大于或等于MAXIMUM_CAPACITY（最大容量 1 << 30），则容量值（capacity）置为MAXIMUM_CAPACITY
      - 否则，判断threshold是否大于1；若threshold大于1，则容量值（capacity）置为`Integer.highestOneBit((threshold - 1) << 1)`；若threshold小于1(threshold大于0)，则容量值（capacity）置为1。
        - 注释：`Integer.highestOneBit((threshold - 1) << 1)`：若threshold为15，则返回16；若threshold为6，则返回8；将threshold减一再左移一位，取最高位，将其余位置0，得到的值为**2的幂**。
      - 将HashMap的阈值threshold置为`Math.min(capacity * loadFactor, MAXIMUM_CAPACITY + 1)`
      - 将HashMap的数组（table）置为`new Entry[capacity]`
    - 2）若key为空，则将该节点放在数组下标为0 的那个链表中
      - 遍历链表，若链表中存在key为null的节点，则将旧值替换为value，将旧值返回；
      - HashMap的modCount计数器加一
      - 若链表中不存在key为null的节点：
        - 判断HashMap的大小size是否大于或等于HashMap的阈值threshold ，若成立，则扩容，
        	- 计算hash值，再用hash值计算出在数组中的位置（计算公式：`hash & (table.length-1)`，用hash值与数组长度取模）（**得到的数组下标为0**）
        	- 将节点插入链表头节点位置，其余节点向后移，HashMap的大小size加一，返回null；
        - 若HashMap的大小size不大于或等于HashMap的阈值threshold ，则将节点插入链表头节点位置，其余节点向后移，返回null；
    - 3）计算hash值，再用hash值计算出在数组中的位置（计算公式：`hash & (table.length-1)`，用hash值与数组长度取模）
      - 遍历链表，若链表中存在key值相同的节点，则将旧值替换为value，将旧值返回；
      - HashMap的modCount计数器加一
      - 若链表中不存在key值相同的节点：
        - 判断HashMap的大小size是否大于或等于HashMap的阈值threshold ，若成立，则扩容，
        	- 计算hash值，再用hash值计算出在数组中的位置（计算公式：`hash & (table.length-1)`，用hash值与数组长度取模）
        	- 将节点插入链表头节点位置，其余节点向后移，HashMap的大小size加一，返回null；
        - 若HashMap的大小size不大于或等于HashMap的阈值threshold ，则将节点插入链表头节点位置，其余节点向后移，返回null；
- 3、get操作：`get(Object key)`

  - 若key为null
    - 若HashMap的大小size为0，则返回空；
    - 否则，遍历数组下标为0的链表找到key为null的节点，返回其value
    - 否则，返回空；
  - 若key不为null
    - 若HashMap的大小size为0，则返回空；
    - 否则，计算key的hash值再用hash值计算出在数组中的位置（计算公式：`hash & (table.length-1)`，用hash值与数组长度取模）
    - 遍历链表，比较hash值与key的值相同的节点，返回其value
    - 否则，返回空。
- 4、扩容操作：原数组长度的一倍(`2 * table.length`)
  - 若数组长度已经达到HashMap的最大容量MAXIMUM_CAPACITY (1 << 30)
  	- 则将HashMap的threshold置为Integer.MAX_VALUE;
  - 否则，创建一个长度为原数组1倍的新数组

- 1）为什么要二次方数？——便于用&运算，算位置

- 2）什么要右移和异或？——将高位参与到运算中来，使元素分布均匀

- key为null时放在第0个位置

### 二、jdk1.8当hash冲突时插入到链表尾部，当链表长度大于8（`TREEIFY_THRESHOLD = 8`）时，将链表转成红黑树
- put操作
    - 1）先将key做hash，若key为null则其在数组中的位置为0；若key不为0，则用key值`(h = key.hashCode()) ^ (h >>> 16)`得到在数组中的位置；（扩容：数组长度的四分之三；扩容为原数组的一倍）
    - 2）若在数组中该位置没值，则直接放进去作为根节点；若已有值，则先判断这个节点下边是链表还是红黑树；
    - 3）链表：
        - 判断这个key在链表里是否存在，若存在则将之前的值替换，再将之前的值返回；
        - 若key不存在，则判断链表长度（binCount计数器）是否达到8，若达到8则转换为红黑树；若没达到8，则将这个节点加在链表末尾；
    - 4）红黑树：
        - 判断这个key在链表里是否存在，若存在则将之前的值替换，再将之前的值返回；
        - 若key不存在，则将该节点插入到红黑树里，将value返回。





