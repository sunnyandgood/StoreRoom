# HashMap源码分析

数组+链表（红黑树（查找效率高））

### 一、jdk1.7当hash冲突时插入到链表头部速度快，在将位置向下移动（在扩容时若并发操作的话会造成死循环）

```java
//默认初始容量（16）,必须是2的幂
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//最大容量  1073741824
static final int MAXIMUM_CAPACITY = 1 << 30;
//默认加载因子
static final float DEFAULT_LOAD_FACTOR = 0.75f;

//HashMap的大小（键值对的数量）
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

    - 1）若HashMap的table属性是空数组（数组长度为0）
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
  - 否则，创建一个长度为原数组1倍的新数组：`Entry[] newTable = new Entry[newCapacity]`
    - 遍历HashMap的table数组
      - 遍历数组上边的链表
      - 判断是否需要重新计算hash值，若需要则重新计算；若不需要则用之前的hash值；用hash值计算出在数组中的位置（计算公式：`hash & (table.length-1)`，用hash值与数组长度取模）
      - 将节点插入到新数组对应链表的头节点，并将其余节点向下移动，让新插入的节点位于数组索引位置上。
    - 将新数组赋值给HashMap的table属性
    - 将`Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY + 1)`赋值给HashMap的threshold属性

- 1）为什么要二次方数？——便于用&运算，计算位置

- 2）什么要右移和异或？——将高位参与到运算中来，使元素分布均匀

- key为null时放在第0个位置

### 二、jdk1.8当hash冲突时插入到链表尾部，当链表长度大于8（`TREEIFY_THRESHOLD = 8`）时，将链表转成红黑树
- **put操作时将节点插入到链表尾部，加上红黑树，有效的避免了在并发扩容时的死循环**。
```java
//默认初始容量（16）,必须是2的幂
static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
//最大容量 1073741824
static final int MAXIMUM_CAPACITY = 1 << 30;
//默认加载因子
static final float DEFAULT_LOAD_FACTOR = 0.75f;

//HashMap的大小（键值对的数量）
transient int size;
//HashMap的加载因子
final float loadFactor;
//阈值(capacity * load factor)
//如果尚未分配表数组，则此字段保留初始数组容量，或零表示默认的初始容量。
int threshold;
//此字段用于使哈希映射的集合视图上的迭代器快速失败。（参见ConcurrentModificationException）。
//一个计数器
transient int modCount;

//将链表转换为红黑树的阈值，当将元素添加到具有至少如此多节点的容器时，
//容器将转换为红黑树。该值必须大于2，并且至少应为8（默认值是8）
static final int TREEIFY_THRESHOLD = 8;
//将红黑树转换为链表的阈值，当元素移除到具有至少如此多节点的容器时，
//容器将转换为链表。该值必须小于TREEIFY_THRESHOLD，并且至少应为6（默认值是6）
static final int UNTREEIFY_THRESHOLD = 6;
//红黑树的最小容量，至少应该是TREEIFY_THRESHOLD的4倍以避免 调整 和 链表与树之间转换 之间的冲突
static final int MIN_TREEIFY_CAPACITY = 64;

//表，在第一次使用时初始化，并根据需要调整大小。分配时，长度总是2的幂。
//（在某些操作中，我们还允许长度为零，以允许当前不需要的引导机制。）
transient Node<K,V>[] table;
//保留缓存的entryset（）。注意，AbstractMap字段用于keyset（）和values（）。
transient Set<Map.Entry<K,V>> entrySet;
```
- 1、创建
  ```java
  public HashMap() {
      this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
  }
  
  public HashMap(int initialCapacity) {
      this(initialCapacity, DEFAULT_LOAD_FACTOR);
  }
  
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
      this.threshold = tableSizeFor(initialCapacity);
  }
  ```
  - 创建一个HashMap
    - 1）若initialCapacity小于0，则抛出IllegalArgumentException异常
    - 2）若initialCapacity大于MAXIMUM_CAPACITY（最大容量 1 << 30），则将initialCapacity置为最大容量
    - 3）若loadFactor小于等于0（或loadFactor是NaN），则抛出IllegalArgumentException异常
    - 4）将HashMap的加载因子（loadFactor）赋值为方法参数loadFactor
    - 5）将方法参数initialCapacity转换为大于它且最接近它的**2的幂**的数
    - 6）将HashMap的阈值（阈值）赋值为经initialCapacity转化的**2的幂**的数
- 2、put操作
    - 1）先将key做hash，`return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)`
    - 2）若HashMap的table属性是空数组（数组容量为0）
      - 则进行初始化（扩容）
    - 3）用key的hash值做运算`(table.length - 1) & hash`得到在数组中的位置；
      - 注：若key为null则其在数组中的位置为0
    - 4）若在数组中该位置没值，则直接放进去作为根节点；若已有值，则判断这个key在链表里是否存在，若存在则将之前的值替换，再将之前的值返回；
    - 5）否则判断这个节点下边是链表还是红黑树；
    - 6）红黑树：
        - 若key不存在，则将该节点插入到红黑树里。
    - 7）链表：
        - 若key不存在，则判断链表长度（binCount计数器）是否达到8，若达到8则转换为红黑树；若没达到8，则将这个节点加在链表末尾；
- 3、get操作：
- 4、扩容操作：

### 三、HashMap拓展（HashMap不是线程安全的）

- 1、对整个HashMap加锁——>HashTable
- 2、jdk1.7将HashMap分段加锁——>ConcurrentHashMap


