# 一、谈谈对Java平台的理解？

开平台特性，write once，run anywhere。一次编写，随处运行。
跨平台运行的实现是依赖JVM实现的，JVM封装了不同操作系统层面的API，使得编译后的字节码不依赖操作系统，实现跨平台特性。就像是适配器模式，字节码可以依赖JVM在不同的平台上运行。可以运行在JVM上的语言还有Scala、Groovy等

Java通过垃圾收集器回收分配内存，大部分情况下，程序员不需要自己操心内存的分配和回收。

Java提供了集合、IO/NIO、并发、安全等基础类库。具有封装、继承、多态的特性。

Java是解释执行，这句话是不正确的，JDK8默认是编译和解释混合的一种模式，即先使用解释器解释执行，在运行时将热点代码编译为机器码并进行保存，下次执行直接读取。我们开发的源代码通过javac编译为字节码，然后，在运行时，通过JVM内嵌的解释器转换为最终的机器码，还有一种AOT编译器，直接将字节码编译为机器码，避免了JIT预热等方面的的开销。

解释器：逐条读入，逐条解释执行，下次执行相同代码还要翻译一次。启动快，执行速度相对较慢。指定-Xint参数。
JIT即时编译：在运行时，通过预热，将热点代码编译成机器码并存在缓存中。
AOT：在编译器，静态的，将字节码直接编译成机器码。

Java类加载过程：Java文件--javac命令 编译器-->class文件---->loading到内存---->Verification 验证---->Preparation 准备（静态变量赋默认值）---->Resolution 解析（常量池中符号引用解析为实际引用）---->Initialing（初始化，静态变量赋初始值）

# 二、Exception和Error区别？

![img](D:\WorkSpace\Java\极客时间-Java核心技术面试精讲笔记\Java核心技术面试精讲笔记.assets\accba531a365e6ae39614ebfa3273900.png)

Exception和Error都继承Throwable类，在Java中只有Throwable类型的实例才可以被抛出（throw）和捕获（catch），它是异常处理机制的基本组成类型。

Exception和Error体现了Java平台设计者对异常情况的分类。Exception是程序正常运行中，可以预料的意外情况，肯能并且应该被捕获，进行相应处理。

Error是指正常情况下，不大可能出现的情况，绝大多数Error都会导致程序处于不正常的、不可回复状态。如常见的OutOfMemoryError等类，都是Error的子类。

Exception又分为检查型异常（checked Exception）和未检查型异常（unchecked Exception），检查型异常是编译器检查的一部分，源代码中必须显示的进行捕获处理（编译器会给出提示）。未检查型异常就是运行时异常，通常是可以通过编码避免的逻辑错误，不会在编译器强制要求捕获异常，例如NullPointException、ArrayIndexOutOfBoundsException。

## NoClassDefFoundError 和 ClassNotFoundException 有什么区别？

NoClassDefFoundError是Error类型，当JVM加载一个类的时候，如果这个类在编译时是可用的，但在运行时找不到这个类的定义的时候，JVM就会抛出一个NoClassDefFoundError错误，比如在new一个类实例时，如果在运行时类找不到，则会抛出该错误。

一般情况下，当我们使用Class.forName()或则CLassLoad.loadClass()以及使用ClassLoad.findSystemClass()在运行时加载类的时候，如果类没有被找到，那么JVM就会抛出ClassNotFoundException。

try...catch..finally 中finally语句块中的代码始终会执行（不管是否发送异常），如果finally块中有return语句，则方法最终结果会返回finally块中的结果。

# 三、谈谈final、finally、 finalize有什么不同？

final修饰类、方法、变量有不同的意义。修饰类，则该类不可被继承扩展；final的方法是不可以重写的；final变量是不可以修改的。

finally则是Java保证重点代码一定被执行的一种机制。我们可以用try-finally或者try-catch-finally来进行类似关闭JDBC连接、保证unlock锁等动作。

finalize是基础类java.lang.Object的一个方法，它的设计目的是保证对象在被垃圾收集前完成特定资源的回收。finalize机制现在已经不推荐使用，在JDK9开始被标记为deprecated.

```
try {
  // do something
  System.exit(1);
} finally{
  System.out.println(“Print from finally”);
}
注意：finally不会被执行
```

## 为什么 String 会设计成不可变？

原因包括设计考虑、效率优化问题、安全性三个方面考虑。

设计考虑，字符串常量池的需要，字符串是被保存在常量池中的，如果字符串允许被改变，那么将会导致各种逻辑错误，比如改变一个对象会影响到另一个独立的对象。

允许String对象缓存HashCode。字符串不变性保证了Hash码的唯一性，可以放心进行缓存，这也是一种性能优化，意味着不必每次重新计算新的hash码。

安全性，String被许多Java类库用来当作参数，例如网络连接地址URL、文件路径Path、反射机制所需的String参数等，假若String不是固定不变的，将会引起各种安全隐患。例如在调用连接操作的时候，String对象的URL被修改，导致连接错误。

# 四、强引用、软引用、弱引用、幻象引用有什么区别？使用场景？

不同引用类型，主要体现的是对像不同的可达性（reachable）状态和对垃圾收集的影响。

## 强引用（StrongReference）

强引用是程序代码中普遍存在的，我们一般创建对象的过程都是强引用。

```
Object obj = new Object();
String str = "123";
```

如果某个对象有强引用与之关联，Java虚拟机必定不会回收这个对象，即使在内存不足的情况下，JVM宁愿抛出OutOfMemory错误也不会回收这种对象。
Java集合强引用弱化的方式不能采取置null，只有采用clear()方法。

```
    Object strongReference = new Object();
    strongReference = null;
      ArrayList<Integer> arrays = new ArrayList<>(10);
        arrays.add(1);
        arrays.clear();//不使用时 clear 清除数组
        clear方法是遍历数据将每个元素置为null
```

## 软引用（SoftReference）

软引用是用来描述一些有用但并不是必须的对象，JVM内存空间充足的时候将数据存储在内存中，如果空间不足就将其回收掉。

```
//123这个string对象关联一个强引用str和一个软引用softReference
String str = new String("123");
SoftReference<String> softReference = new SoftReference<String>(str);
```

软引用可以和一个引用队列联合使用。如果软引用所引用对象被垃圾回收，Java虚拟机就会把这个软引用加入到与之关联的引用队列中。

```
String str = new String("123");
ReferenceQueue<String> queue = new ReferenceQueue();
SoftReference softReference = new SoftReference(str, queue);

str = null;//去掉强引用
System.gc();// 通知GC

System.out.println(softReference.get()); // 123
Reference<? extends String> reference = referenceQueue.poll();
System.out.println(reference); //null
```

### 场景：

软引用的回收机制使他非常适合用于缓存，当内存不足时，缓存中的内存是可以被释放的。

- 图片缓存。图片缓存框架中，内存缓存中的图片是以这种引用保存
- 网页缓存。

## 弱引用（WeakReference）

弱引用用来描述非必须对象，当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象，在Java中用java.lang.ref.WeakReference类表示。

弱引用与软引用的区别在于:弱引用的对象拥有更短的生命周期，它只能生存到下一次垃圾收集发生之前。当垃圾回收器扫描到只具有弱引用的对象时，无论内存空间是否充足，都会回收它。

```
String str = new String("123");
WeakReference weakReference = new WeakReference(str);
str = null;//去掉强引用
```

弱引用可以和一个引用队列关联，如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。

```
String str = new String("123");
ReferenceQueue queue = new ReferenceQueue();
WeakReference<String> weakReference = new WeakReference<String>(str, queue);
str = null;// 去掉强引用
```

### 场景：

当GC发生时，弱引用对象总是被回收，因此弱引用也可以用于缓存。

- ThreadLocalMap防止内存泄漏。
- 监控对象是否将要被回收。

## 虚引用（PhantomReference）

虚引用并不会决定对象生命周期。如果一个对象仅持有虚引用，那么它就和没有引用任何引用一样，在任何时候都可能被垃圾回收。

虚引用主要被用来跟踪垃圾回收的活动，虚引用和软引用、弱引用的区别在于：虚引用必须和引用队列关联使用。当垃圾收集器回收一个对象的时候，如果发现它还有虚引用，就会在回收对象的内存之前，将虚引用加入到与之关联的引用队列中。

```
String str = new String();
ReferenceQueue queue = new ReferenceQueue();
// 创建一个虚引用必须与一个引用队列关联
PhantomReference phantomReference = new PhantomReference(str, queue);

```

程序可以通过判断引用队列中是否已经加入虚引用，来了解被引用对象是要进行垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用对象的内存被回收之前采取必要行动。

### 场景：

主要用来跟踪对象被垃圾回收器回收的活动。

- 它允许你知道具体何时引用对象从内存中移除；

- 虚引用可以避免很多析构时的问题：finalize 方法可以通过创建强引用指向快被销毁的对象来让这些对象重新复活。然而，一个重写了 finalize 方法的对象如果想要被回收掉，需要经历两个单独的垃圾收集周期。在第一个周期中，某个对象被标记为可回收，进而才能进行析构。

## 对象可达性状态流转分析：

![img](https://static001.geekbang.org/resource/image/36/b0/36d3c7b158eda9421ef32463cb4d4fb0.png)

### 强可达（Strongly Reachable）：

就是当一个对象可以有一个或者多个线程可以不通过各种引用访问到的情况。例如，我们创建一个对象，那么创建它的线程对它就是强可达。

### 软可达（Softly Reachable）：

就是当我们只能通过软引用才能访问到对象的状态

### 弱可达（Weakly Reacheable）：

就是只能通过软引用才能访问到对象的状态。这是十分临近finalize状态的时机，当弱引用被清除的时候，就符合finalize的条件了。

### 幻象可达（Phantom Reachable）：

没有强、软、弱引用关联，并且finalize过了，只有幻象引用指向这个对象的时候。

### 不可达（UnReachable）

意味着对象可以被清除了。

对于软引用、弱引用之类，垃圾收集器可能会存在二次确认的问题，以保证处于弱引用状态的对象，没有改变为强引用。

##  引用队列（ReferenceQueue）使用

谈到各种引用的编程，就必然要提到引用队列。我们在创建各种引用并关联到相应对象时，可以选择是否需要关联引用队列，JVM 会在特定时机将引用 enqueue 到队列里，我们可以从队列里获取引用（remove  方法在这里实际是有获取的意思）进行相关后续逻辑。尤其是幻象引用，get 方法只返回  null，如果再不指定引用队列，基本就没有意义了。看看下面的示例代码。利用引用队列，我们可以在对象处于相应状态时（对于幻象引用，就是前面说的被  finalize 了，处于幻象可达状态），执行后期处理逻辑。

```

Object counter = new Object();
ReferenceQueue refQueue = new ReferenceQueue<>();
PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
counter = null;
System.gc();
try {
    // Remove是一个阻塞方法，可以指定timeout，或者选择一直阻塞
    Reference<Object> ref = refQueue.remove(1000L);
    if (ref != null) {
        // do something
    }
} catch (InterruptedException e) {
    // Handle it
}
```

# 四、String、StringBuffer、StringBuild区别

## String

String是不可变的类，被声明为final类，声明的变量地址是不可修改的（因为内存一旦创建，内存地址肯定是不可更改的），但由于它的不可变性，类似拼接、裁剪等操作，都会产生新的字符串，由于字符串操作的普遍性，所以相关操作的效率往往对应用性能有明显影响。JDK8String底层存储使用的是char数组，JDK9使用的是byte数组。

```
public class StringTest{
	public void main(String[] args){
		// 创建一个对象。在常量池创建对象1，引用a指向常量对象
		String a = "11"；
		
		// 创建了2个对象。一个是在堆空间new出来的对象2，一个是在常量池中创建的对象，引用b执向堆上的对象
		String b = new String("11");
		
		// 创建1个对象。在堆上创建对象11，而常量对象"11"已经在常量池中存在，引用c执向堆上对象
		String c = new String("11");
		
		// 创建一个常量对象123，因为Java虚拟机会在编译常量表达式时进行优化（在编译期间将"1"+"2"+"3"提前计算为"123"，在实际运行中没有拼接）
		String d = "1"+"2"+"3";
		
		// 创建2个对象。a对象已经创建过了，常量对象"22"在常量池中找不到，所以会在常量池创建对象"22"，以及常量对象"1122"
		String e = a + "22";

		// 通过反编译，非静态的拼接逻辑在JDK8中会自动被Javac转换为StringBUild操作。而Java9利用InvokeDynamic，将字符串拼接的优化与javac生成的字节解耦
		String count = "1";
		String str1 = count + "aa"+"bb";
		String str2 = "1"+ "aa"+"bb";
		System.out.println(str1 == str2);// false
	}

} 
```

## StringBuffer

针对String拼接产生太多中间对象，提出StringBuffer类，可以使用append方法，把字符串添加到已有序列的末尾。StringBUffer时线程安全的，使用关键字synchronized加锁，也随之带来了额外的性能开销。

## StringBuild

Java1.5新增的，在能力上和StringBuffer没有本质区别，但是去掉了线程安全的部分，有效减少了开销，是大部分情况下字符串拼接的首选。

# 五、谈谈Java反射机制，动态代理是基于什么原理？

## Java反射机制



## 静态代理



## 动态代理



### JDK动态代理





### CGlib动态代理