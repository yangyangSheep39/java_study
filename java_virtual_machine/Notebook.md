<!-- TOC -->

* [JVM剖析与GC调优](#jvm剖析与gc调优)
    * [字节码文件](#字节码文件)
        * [javac编译器的编译步骤](#javac编译器的编译步骤)
    * [哪些类有对应的成员](#哪些类有对应的成员)

<!-- TOC -->

# JVM剖析与GC调优

## 字节码文件

1. 前端编译器：javac（全量编译器）
    1. eclipse有自己的增量编译器ECJ（Eclipse Compiler For Java），tomcat也是使用的ECJ编译器
    2. 默认情况下 IDEA 默认是javac编译器，可以自己设置为AspectJ （ACJ）编译器
2. 后端编译器-执行引擎：解释器 + JIT 编译器（1.3及之后的hotspots）
3. Java是一种半编译半解释型语言：主要是Java生成了字节码文件以后，
    1. jdk1.3之前是解释型语言
    2. 1.3之后是半编译半解释型语言
       ![class文件的编译器（执行引擎）.png](note_image/class%E6%96%87%E4%BB%B6%E7%9A%84%E7%BC%96%E8%AF%91%E5%99%A8%EF%BC%88%E6%89%A7%E8%A1%8C%E5%BC%95%E6%93%8E%EF%BC%89.png)

4. AOT（静态提前编译器，Ahead Of Time Compiler） 
> jdk9引入了AOT编译器（静态提前编译器，Ahead Of Time Compiler） 
> Java 9 引入了实抽性 AdT 编译工具jaotc。它借助了 Graal 编译器，将所输入的 Java 类文件转换力机器码，并存放 至生成的动态共享库之中。 所谓 AOT 编译，是与即时编译相对立的一个概念。我们知道，即时编译指的是在程序的运行过程中，将字节码转换为可在 硬件上直接运行的机器码，并部署至托管环境中的过程。而 AOT 编译指的则是，在程序运行之前，便将字节码转换为机器码的过程。 .java ->.class -> .so
最大好处：Java虚拟机加载已经预编译成二进制库，可以直接执行。不必等待即时编译器的预热，减少Java应用给人带来 “第一次运行慢”的不良体验。
- 缺点：
- 破坏了java“一次编译，到处运行”，必须为每个不同硬件、OS编译对应的发行包。
- 降低了Java链接过程的动态性，加载的代码在编译期就必须全部已知。

### javac编译器的编译步骤

> javac编译器在将Java源码编译为一个有效的字节码文件过程中经历了4个步骤，分别是词法解析、语法
> 解析、语义解析以及生成字节码。
![javac编译器的编译步骤.png](note_image/javac%E7%BC%96%E8%AF%91%E5%99%A8%E7%9A%84%E7%BC%96%E8%AF%91%E6%AD%A5%E9%AA%A4.png)

### 编译全流程

> 橙色的是前端编译器（javac）
> 绿色的是后端编译器（解释器）
> 绿色的是后端编译器（JIT）
![编译全流程.png](note_image/%E7%BC%96%E8%AF%91%E5%85%A8%E6%B5%81%E7%A8%8B.png)

## 哪些类有对应的成员
1. class 外部类，成员（成员内部类，静态内部类）、局部内部类、匿名内部类
2. interface 接口
3. []：数组(元素的类型和维度是一样的，则是同一个class类)
4. enum：枚举
5. annotation：注解 @interface
6. primitive type：基本数据类型
7. void：void类型

## 什么是字节码指令（byte code）
> Java虚拟机的指令由一个字节长度的、代表者某种特定操作含义的操作码（opcode）
> 以及跟随其后的李至多个代表此操作所简参数的操作数（operand）所构成。
> 虛拟机中许多指令并不包含操作数，与有一个操作码。
> 比如：
- ![什么是字节码指令.png](note_image/%E4%BB%80%E4%B9%88%E6%98%AF%E5%AD%97%E8%8A%82%E7%A0%81%E6%8C%87%E4%BB%A4.png)
> 字节码解析
```java
    @Test
public void test3() {
    int i = 10;
    i = i++;
    System.out.println(i);
}
//以下为字节码解析
bipush 10 //将10压入操作数栈-底部入栈（i赋值为10）局：- 栈：10
istore_1 //将操作数栈底的值取出来存入局部变量表的角标为1的位置【0位置是this】局：10 栈：-
iload_1 //将局部变量表的第一个位置的值压入操作数栈（10入栈第二个 位置，索引为1）局：11 栈：10
iinc 1 by 1 //将局部变量表索引为1的位置自增长1 局：11 栈：10
istore_1 //将操作数栈底的值取出来存入局部变量表的角标为1的位置【0位置是this】局：11->10 栈：10
getstatic #2 <java/lang/System.out : Ljava/io/PrintStream;>
iload_1
invokevirtual #3 <java/io/PrintStream.println : (I)V>
return
```
> 包装类对象的缓存
![包装类对象的缓存问题.png](note_image/%E5%8C%85%E8%A3%85%E7%B1%BB%E5%AF%B9%E8%B1%A1%E7%9A%84%E7%BC%93%E5%AD%98%E9%97%AE%E9%A2%98.png)

## Class文件结构概述
- Class文件的结构并不是一成不变的，随着Java虚拟机的不断发展，总是不可避免地会对Class文件结构做出一些调整，但是其基本结构和框架是非常稳定的。 
Class文件的总体结构如下：
- 魔数
- Class文件版本
- 常量池
- 访问标识（或标志）
- 类索引，父类索引，接口索引集合
- 字段表集合
- 方法表集合
- 属性表集合

### 1.class文件的魔数是什么？
- Magic Number（魔数）：class文件的标志
- 每个 Class 文件开头的4个字节的无符号整数称魔数（Magic Number）,它的唯一作用是确定这个文件是否一个能被虚拟机接受的有效合法的Class文件
即：魔数是Class文件的标识符。
- 魔数值固定为OxCAFEBABE。不会改变。
- 如果一个C1ass文件不以OxCAFEBABE开头，虚拟机在进行文件校验的时候就会直接抛出以下错误：
Error:A JNI error has occurred,please check your installation
and try again
Exception in thread "main" java.lang.ClassFormatError：
Incompatible magic value 1885430635 in class file StringTest
- 使用魔数而不是扩展名来进行识别主要是基于安全方面的考虑，因为文件扩展名可
以随意地改动。

### 2.如何确保高版本的JM可执行低版本的class文件？
- 不同版本的Java编译器编译的Class文件对应的版本是不一样的。目前，高版本
的Java虚拟机可以执行由低版本编译器生成的Class文件，但是低版本的Java虚拟
机不能执行由高版本编译器生成的Class文件。否则JVM会抛出
java.lang.UnsupportedClassVersionError异常。 （向下兼容〉
- 在实际应用中，由于开发环境和生产环境的不同，可能会导致该问题的发生。因此，需要我们在开发时，特别注意开发编译的JDK版本和生产环境中的JDK版本是否一致。 
- #### class文件版本号
- 紧接着魔数的 4 个字节存储的是 Class 文件的版本号。同样也是4个字节。第5个
和第6个字节所代表的含义就是编译的副版本号minor_version，而第7个和第8个
字节就是编译的主版本号major_version。
- 它们共同构成了class文件的格式版本号。譬如某个 Class 文件的主版本号为
M，副版本号为 m，那么这个Class 文件的格式版本号就确定为 M.m。
- 版本号和Java编译器的对应关系如下表：![jdk版本号与字节码标识对照.png](note_image/jdk%E7%89%88%E6%9C%AC%E5%8F%B7%E4%B8%8E%E5%AD%97%E8%8A%82%E7%A0%81%E6%A0%87%E8%AF%86%E5%AF%B9%E7%85%A7.png)
- Java 的版本号是从45开始的，JDK 1.1之后的每个JDK大版本发布主版本号向上

### 3.constant_pool_count（常量池计数器）
- 由于常量池的数量不固定，时长时短，所以需要放置两个字节来表示常量池容量计
数值。
- 常量池容量计数值（u2类型）：从1开始，表示常量池中有多少项常量。即
constant_poo1_count=1表示常量池中有8个常量项。
- Demo的值为：![常量池demo值.png](note_image/%E5%B8%B8%E9%87%8F%E6%B1%A0demo%E5%80%BC.png)
其值为0x0016，也就是22。
需要注意的是，这实际上只有21项常量。索引为范围是1-21。为什么呢？
通常我们写代码时都是从0开始的，但是这里的常量池却是从1开始，因为它把第8项
常量空出来了。这是为了满足后面某些指向常量池的索引值的数据在特定情况下需要
表达“不引用任何一个常量池项目”的含义，这种情况可用索引值0来表示。
```java
int［］ arr = new int［10］；
arr［e］；
```
