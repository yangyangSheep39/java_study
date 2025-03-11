
# JVM剖析与GC调优

##  字节码文件

1. 前端编译器：javac（全量编译器）
    1. eclipse有自己的增量编译器ECJ（Eclipse Compiler For Java），tomcat也是使用的ECJ编译器
    2. 默认情况下 IDEA 默认是javac编译器，可以自己设置为AspectJ （ACJ）编译器
2. 后端编译器-执行引擎：解释器 + JIT 编译器（1.3及之后的hotspots）
3. Java是一种半编译半解释型语言：主要是Java生成了字节码文件以后，
   1. jdk1.3之前是解释型语言
   2. 1.3之后是半编译半解释型语言
![class文件的编译器（执行引擎）.png](note_image/class%E6%96%87%E4%BB%B6%E7%9A%84%E7%BC%96%E8%AF%91%E5%99%A8%EF%BC%88%E6%89%A7%E8%A1%8C%E5%BC%95%E6%93%8E%EF%BC%89.png)

### javac编译器的编译步骤

> javac编译器在将Java源码编译为一个有效的字节码文件过程中经历了4个步骤，分别是词法解析、语法
解析、语义解析以及生成字节码。
![javac编译器的编译步骤.png](note_image/javac%E7%BC%96%E8%AF%91%E5%99%A8%E7%9A%84%E7%BC%96%E8%AF%91%E6%AD%A5%E9%AA%A4.png)

## 哪些类有对应的成员