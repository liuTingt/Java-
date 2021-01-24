## JVM基础概念

### Java从编码到执行，Java是解释和编译混合型语言

![1 - 副本](D:\WorkSpace\Java\JVM\image\1 - 副本.jpg)

JVM是跨语言的平台，有许多语言可以直接在JVM虚拟机上执行。

### JVM与class文件格式

​	JVM跟java无关。

​	JVM是一种规范，可参考oracle官网，
​	https://docs.oracle.com/en/java/javase/13/
​	https://docs.oracle.com/javase/specs/index.html

​	JVM是虚构出来的一台计算机。有自己的字节码指令集（汇编语言）、有自己的内存管理：栈、堆、方法区等。

### 常见的JVM实现

Hotspot：oracle官方，我们做实验用的JVM，执行命令Java -version
Jrockit：BEA，曾经号称世界上最快的JVM，后被Oracke收购，合并于hotspot
J9：IBM
Microsoft VM
TaobaoVM：hotspot深度定制版
LiquidVM：直接针对硬件
azul zing：最新垃圾回收的业界标杆，官网www.azul.com

JDK JRE JVM

![5](D:\WorkSpace\Java\JVM\image\5.jpg)

## class文件结构

- 二进制字节流（Java虚拟机解释为二进制字节流）

- 数据类型：u1 u2 u4 u8和_info(表类型)

  - u1表示1个字节 u2表示2个字节 u3表示3个字节 u4表示4个字节
  - _info的来源是hotspot源码中的写法

- 查看16进制格式的ClassFile

  - sublime/ notepad
  - IDEA插件-BinEd

- 有很多可以观察ByteCOde的方法

  - javap：javap -v 文件名
  - JBE - 可以直接修改
  - JClassLib - IDEA插件之一

- ClassFile构成

  ![9](D:\WorkSpace\Java\JVM\image\9.jpg)



## 类加载-初始化

### class文件怎么从硬盘上到内存中，并且开始执行

![1](D:\WorkSpace\Java\JVM\image\1.jpg)

Class进入内存有三步：

- 第一步Loading(加载)
- 第二步Linking:第二步包含三小步，verification、preparation、resolution
- 第三步Initializing(初始化)

Loading:

​	把一个class文件load内存装到内存里去，他本来是class文件上的一个一个的二进制，一个一个的字节，装完之后就是接下来的Linking。

Linking的过程分为三小步：

- Verification：校验装进来的class文件是不是符合class文件的标准，假如装进来的不是这个CA FE BA BE，在这个步骤就被拒掉了。
- Preparation：把class文件静态变量赋默认值，不是赋初始值，比如static i = 8，在这个步骤是先把把i赋值为0.
- Resolution：是把class文件常量池里面用到的符号引用给转换为直接内存地址，直接可以访问到的内容。

Initializing：把静态变量赋值为初始值。	

### 类加载器

![2](D:\WorkSpace\Java\JVM\image\2.jpg)

JVM她本身有一个类加载器的层次，这个类加载器本身就是一个普通的class，JVM有一个类加载器的层次分别来加载不同的class，JVM所有的class都是被类加载器加载到内存的，那么这个类加载可以叫做ClassLoader。

**第一个类加载器层次**

Bootstrap：它是加载lib里JDK最核心的内容，比如rt.jar、charset.jar等核心类，所以说什么时候我们调用getClassLoader()拿到这个器的结果是一个空值的时候代表已经到达了最顶层的加载器。Bootstrap是C++实现的，在Java中没有一个类与之对应，所以输出为null

**第二个类加载器层次**

Extension：这个是Extension加载器罗战雷，加载扩展包里的各种各样的文件，这些扩展包在JDK安装目录jre/lib/ext下的jar

**第三个类加载层次**

Application:用于加载classpath指定的内容。

**第四个类加载层次**

CustomerClassLoader：自定义加载器，加载自己定义的加载器。

### 类加载过程

![image-20210121103333539](C:\Users\lt\AppData\Roaming\Typora\typora-user-images\image-20210121103333539.png)

### Class对象到底什么样

​		一个class文件平时躺在硬盘上，这个内容被load内存之后，内存会创建两块内容，第一块内容是存储String.class二进制，第二块内容是于此生成了一个class类的对象，这一块对象指向了第一块内容。

​		反射过程：使用反射，我们通过class对象去拿它的方法，甚至可以调用方法，他一定是哪些方法的信息存在这个对象里，然后真让这个方法执行的时候，一定是去找那个class文件里面的二进制码，翻译成Java指令一步一步执行。

### 双亲委派

- 父加载器

  父加载器不是 类加载器的加载器，也不是加载器的父类加载器，父加载器，是class对象里面的一个parent对象。(类加载器的加载器是null)

- 双亲委派是从子到父，然后从父到子方向的双亲委派过程。
- 思考：为什么要双亲委派
  
  - java.lang.String类由自定义类加载器加载行不行？

### 类加载器范围

来自Launcher源码

- BootstrapClassLoader加载路径：sun.boot.class.path
- ExtensionClassLoader加载路径：java.ext.dirs
- AppClassLoader加载路径：java.class.path

```
com.ltt.jvm.classloader.T003_ClassLoaderScope.java例子
```

### 自定义类加载器

- 继承ClassLoader
- 重写模板方法findClass
  - 调用defineClass
- 自定义类加载器加载自加密的class
  - 防止反编译
  - 防止篡改

```
com.ltt.jvm.classloader.T004_LoadClassByHand
com.ltt.jvm.classloader.T005_MSBClassLoader
```

### Java编译器

​	Java是解释型和编译型混合语言。

- 解释型
  - bytecode intepreter
- JIT
  - Just In-Time compile
- 混合模式（默认模式）
  - 混合使用解释器 + 热点代码编译
  - 起始阶段采用解释执行
  - 热点代码检测
    - 多次被调用的方法（方法计数器：检测方法执行频率）
    - 多次被调用的循环（循环计数器：检测循环执行频率）
    - 进行编译
- -Xmixed 默认为混合模式：开始解释执行，启动速度较块，对热点代码实行检测和编译
- -Xint 使用解释模式，启动很快，执行稍慢
- -Xcomp 使用编译模式，执行很快，启动很慢

### 懒加载LazyLoading

- 严格将应该交LazyInitializing
- JVM规范并没有规定何时加载
- 但是严格规定了什么时候初始化
  - new、getStatic（访问实例）、putStatic、invokestatic（访问静态方法），访问final变量除外。
  - java.lang.reflect对类进行反射调用时
  - 初始化子类的时候，父类首先初始化
  - 虚拟机启动时，被执行的主类必须初始化
  - 动态语言支持java.lang.invoke.MethodHandle解析的结果为REF_getstatic REF_putstatic  REF_invokestatic的方法句柄时，该类必须初始化。

```
com.ltt.jvm.classloader.T007_LayzeLoading
```

## 面试

### 为什么使用双亲委派？

最主要是安全，假如任何的类都可以自定义load到内存，那如果把java.lang.String类交给自定义classloader，load到内存，如果把这一部分打成jar包，给客户端使用，那么就能把客户端输入的String字符串，我都可以拿到，包括账号密码。其次可以节省资源，如果父加载器已经加载了就不用再次加载。

