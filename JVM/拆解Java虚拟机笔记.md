## asmtools工具的使用

- 由 class 文件生成 jasm 文件：`java -jar asmtools.jar jdis Foo.class > Foo.jasm`
- 由 jasm 文件生成 class 文件：`java -jar asmtools.jar jasm Foo.jasm`

## 一、Java代码是怎么运行的

### 1、为什么Java要在虚拟机中运行?

​	Java是一门高程序语言，他的语法非常复杂，抽象程度也很高，因此在硬件上直接运行这种复杂的程序并不现实。所以，在运行Java之前，需要对其进行一番转换。

​	Java虚拟机可以由硬件实现，但更为常见的是在各个现有平台上提供软件实现。这么做的意义在于，一旦一个程序被转换为Java字节码，那么它便可以在不同平台上的虚拟机实现里运行。这也是我们常说的”一次编译，到处运行“。

​	虚拟机的另一个好处是它带来了一个托管环境（Managed Runtime）。这个托管环境能够代替我们处理一些代码中冗杂而且容易出错的部分。如自动内存管理和垃圾回收，此外，托管环境还提供了诸如数组越界、动态类型、安全权限等等的动态检测，使我们免于书写这些无关业务逻辑的代码。

### 2、Java虚拟机具体是怎么运行Java代码的？	

​	以标准JDK中的HotSpot虚拟机为例，从虚拟机以及底层硬件两个角度进行讲解。

​	从虚拟机视角看，执行Java代码首先将他编译成class字节码加载到Java虚拟机中。加载后的Java类会被存放到方法区（Method Area）中，实际运行时，虚拟机会执行方法区内的代码。

​	Java虚拟机包含了方法区、堆、Java方法栈、本地方法栈、PC寄存器。

![img](https://static001.geekbang.org/resource/image/ab/77/ab5c3523af08e0bf2f689c1d6033ef77.png)

​	从硬件视角看，Java字节码无法直接执行。因此Java虚拟机需要把字节码翻译成机器码。

​	在HotSpot里面，有两种翻译形式：解释执行和即时编译（JIT）。HotSpot默认采用混合模式，先解释执行字节码，而后将反复执行的热点代码，以方法为单位进行即时编译。

​	解释执行：即逐条翻译字节码为可运行的机器码。
​	即时编译：以方法为单位将字节码翻译成机器码。

​	HotSpot内置了多个即时编译器：C1、C2和Graal。Graal是Java10正式引入的实验性即时编译器。

​	之所以引入多个即时编译器，是为了在编译时间和生成代码的执行效率之间进行取舍，C1又叫Client编译器，面向的是对启动性能有要求的客户端GUI程序，采用的优化手段想对简单，因此编译时间较短。C2又叫做Server编译器，面向的是对峰值性能有要求的服务器端程序，采用优化手段相对复杂，因此编译时间较长。

​	从Java7开始，HotSpot默认采用分层编译的方式：热点方法首先会被C1编译，而后热点方法中的热点被C2编译。

​	被鉴定为热点代码的方法：基于采样的热点探测和基于计数器的热点探测。

## 二、Java的基本类型

​	boolean类型在Java虚拟机中被映射为整数类型，true被映射为1，而false被映射为0。Java中的逻辑运算以及条件跳转，都是用整数相关的字节码实现的。

​	除boolea类型外，Java还有另外7个基本类型，他们拥有不同的值域，但默认值在内存中均为0，这些基本类型中，浮点类型比较特殊，基于他的运算或比较，需要考虑+0.0F、-0.0F以及NaN。

![img](https://static001.geekbang.org/resource/image/77/45/77dfb788a8ad5877e77fc28ed2d51745.png)

​	除long和double外，其他类型及引用类型在解释执行的方法栈帧中占用的大小是一致的，但他们在堆中占用的大小却不同。在将Boolean，byte、char以及shot的值存入字段或者数组单元的时候，Java虚拟机会进行掩码操作（只取最后一位的值存入boolean字段或数组中）。在读取时，Java虚拟机会将其扩展为int类型。

## 三、Java虚拟机时如何加载Java类的

​	Java语言的类型可以分为基础类和引用类型。引用类型，Java将其分为四种：类、接口、数组类和泛型参数。由于泛型参数在编译过程中会被擦除，因此，Java虚拟机实际上只有前三种。在类、接口和数组类中，数组类是由Java虚拟机直接生成的，其他两种则有对应的字节流。

### 1、类的加载过程

​	Java虚拟机将字节流转化为Java类的过程分为加载、链接以及初始化三大步。

- 加载：Loading，是指查找字节流，并据此创建类的过程。对于数组类，他没有对应的字节流，而是由Java虚拟机直接生成的。对于其他类来说，Java虚拟机需要借助类加载器来完成查找字节流的过程。

- 链接：Linking，是指将创建的类合并至Java虚拟机中，使之能够执行的过程，它分为验证、准备以及解析三个阶段。

  - 验证：Verification，确保加载的类满足Java虚拟机的约束条件。

  - 准备：Preparation，为被加载类的静态字段分配内存，初始化默认值。

  - 解析：Resolution，将这些符号引用解析为实际引用。如果符号引用执行一个未被加载的类，或者未被加载类的字段或方法，那么解析将触发这个类的加载（但未必触发这个类的链接以及初始化）

    Java虚拟机并没有要求在链接过程中完成解析，它仅规定了：如果某些字节码使用了符号引用，那么在执行这些字节码之前，要完成对这些字符引用的解析。

- 初始化：Initializing，为标记为常量值的字段赋值，以及执行<clinit>方法的过程。Java虚拟机会通过加载来确保<clinit>方法被执行一次。

  在Java代码中，如果要初始化一个静态字段，可以在声明时直接赋值，也可以在静态代码块中对其赋值。如果直接赋值的静态字段被final修饰，并且他的类型为基本类型或字符串时，那么该字段便会被Java编译器标记为常量值（ConstantValue）,其初始化直接由Java虚拟机完成。除此之外的所有直接赋值操作，以及静态代码块中的代码，都会被Java编译器放置到同一个方法中，并把它命名为<clinit>。

### 2、何时触发类的初始化？

​	JVM规范枚举了下述多种触发情况：

1. 当虚拟机启动时，初始化用户指定的类。
2. 当遇到以新建目标类实例的new指令时，初始化new指令的目标类。
3. 当遇到访问静态字段指令时，初始化静态字段所在的类。
4. 当遇到调用静态方法指令时，初始化静态方法所在的类。
5. 子类的初始化会触发父类的初始化。
6. 如果一个接口实现了default方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化。
7. 使用反射API对某个类进行反射调用时，初始化这个类。
8. 当初次调用MethodHandle实例时，初始化MethodHandle指向方法所在的类。

### 3、问题

```
public class Singleton {
  private Singleton() {}
  private static class LazyHolder {
    static final Singleton INSTANCE = new Singleton();
	static {
		System.out.println("LazyHolder.<clinit>");
	}
  }
  public static Object getInstance(boolean flag) {
	  if (flag) return new LazyHolder[2];
    return LazyHolder.INSTANCE;
  }
  
   public static void main(String[] args) {
	   getInstance(true);
	   System.out.println("----------------");
	   //getInstance(false);
   }
}

$ javac Singleton.java
// JVM参数-verbose:class加载的先后顺序
$ java -verbose:class Singleton
```

问题1：新建数组（第 11 行）会导致 LazyHolder 的加载吗？会导致它的初始化吗？

新建数组会导致LazyHolder的加载，不会初始化（没有打印静态代码块中内容）

问题 2：新建数组会导致 LazyHolder 的链接吗？

不会导致链接？？？？？？？getInstance(false);会链接和初始化

## 四、JVM是如何执行方法调用的

### 1、重载

​		重载是在同一个类中出现多个名字相同，参数类型不同的方法。

### 2、重写和隐藏

​		如果这两个方法都是静态的，那么子类中的方法隐藏了父类中的方法。隐藏即在子类中这两个方法同时存在，调用哪个方法取决于引用变量的类型。
如果这两个方法都是非私有的，也不是静态方法，那么子类中的方法重写父类中的方法。重写即覆盖，子类中只有一个方法存在，无论引用变量是子类还是父类，都会调用子类的方法。

​		对于Java语言中重写而Java虚拟机中非重写的情况，编译器会通过生成桥接方法[1]来实现Java中的重写语义。

### 3、JVM的静态绑定和动态绑定

​		Java虚拟机中的静态绑定指的是在解析时便能够识别方法的情况，而动态绑定则指的是需要在运行过程中根据调用者的动态类型来识别目标方法的情况。

Java字节码中与调用相关的指令共五种：

1. invokestatic：用于调用静态方法。
2. invokespecial：用于调用私有实例方法、构造器，以及使用super关键字调用父类的实例方法或构造器，和所实现接口的默认方法。
3. invokevirtual：用于调用非私有实例方法。
4. invokeinterface：用于调用接口方法。
5. invokedynamic：用于调用动态方法。

对于invokestatic和invokespecial，Java虚拟机能够直接识别具体的目标方法。

而对于invokevirtual以及invokeinterface，在绝大部分情况下，虚拟机需要在执行过程中，根据调用者的动态类型，来确定具体的目标方法。唯一的例外在于，如果虚拟机能够确认目标方法有且仅有一个，比如说目标方法被标记为final，那么它可以不通过动态类型，直接确定目标方法。

Java虚拟机识别方法的关键在于类名、方法名以及方法描述符。方法描述符是由方法的参数类型以及返回类型所构成。Java虚拟机与Java语言不同，它并不限制方法名与参数类型相同，但返回类型不同的方法出现在同一个类中。

Java虚拟机关于方法重写的判定同样基于方法描述符。也就是说，如果子类定义了与父类中非私有、非静态方法同名的方法，那么只有当这两个方法的参数类型以及返回类型一致，Java虚拟机才会判定为重写。

对于Java语言中重写而Java虚拟机中非重写的情况，编译器会通过桥接方法[2]来实现Java中的重写语义。











[1]https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html

[2]https://docs.oracle.com/javase/tutorial/java/generics/bridgeMethods.html