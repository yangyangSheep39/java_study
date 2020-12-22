package com.java8.lambda.practice;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Consumer;

/**
 * Lambda表达式基础语法
 *      Java8中引入了一个新的操作符 `->` ，该操作符为 `箭头操作符` 或 `Lambda操作符`
 *          箭头操作符将Lambda表达式分成两部分
 *              - 左侧：Lambda表达式的参数列表
 *              - 右侧：Lambda表达式中所需要执行的功能，即Lambda体
 * 语法格式一：无参数，无返回值
 *      () -> System.out.println("Hello Lambda");
 *
 * 语法格式二：有一个参数并且无返回值的方法
 *      (x) -> System.out.println(x);
 * 语法格式三：若只有一个参数，小括号可以省略不写
 *      x -> System.out.println(x);
 *
 * 语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
 *      Lambda表达式的右侧必须有大括号包裹住代码块，大括号后分号封住
 * 语法格式五：有两个以上的参数，有返回值，Lambda体中仅有一条语句
 *      return 和 大括号都可以省略不写
 *
 * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM的编译器可以通过上下文推断出数据类型，即”类型推断“
 *      (x, y) -> Integer.compare(x, y);
 *      (Integer x, Integer y) -> Integer.compare(x, y);
 *
 * 横批：能省则省
 * 上联：左右遇一，括号省
 * 下联：左侧推断，类型省
 */
public class LambdaBasicGrammar {

    /**
     *  语法格式一：无参数，无返回值
     */
    @Test
    public void test1() {
        //jdk1.7以前，如果说在局部内部类中应用了同级别的内部变量时，变量必须是final
        int num = 10;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello Lambda  *^____^*  " + num);
            }
        };
        r.run();

        System.out.println("============================================================");

        Runnable r1 = () -> System.out.println("Hello Lambda  *^____^*  " + num);
        r1.run();
    }

    /**
     * 语法格式二：有一个参数并且无返回值的方法
     *      (x) -> System.out.println(x);
     * 语法格式三：若只有一个参数，小括号可以省略不写
     *      x -> System.out.println(x);
     */
    @Test
    public void test2() {
        //Lambda表达式对左侧Consumer接口的accept抽象方法的实现
        Consumer<String> con = (x) -> System.out.println(x);
        Consumer<String> con1 = x -> System.out.println(x);
        con.accept("Lambda威武");
        con1.accept("Lambda威武  省略括号");
    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，并且Lambda体中有多条语句
     *      Lambda表达式的右侧必须有大括号包裹住代码块，大括号后分号封住
     * 语法格式五：有两个以上的参数，有返回值，Lambda体中仅有一条语句
     *      return 和 大括号都可以省略不写
     * 语法格式六：Lambda表达式的参数列表的数据类型可以省略不写，因为JVM的编译器可以通过上下文推断出数据类型，即”类型推断“
     */
    @Test
    public void test3() {
        //多条语句
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);


        System.out.println("============================================================");

        //一条语句
        Comparator<Integer> comparator1 = (x, y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet1 = new TreeSet<>(comparator);


        //写数据类型
        Comparator<Integer> comparator2 = (Integer x, Integer y) -> Integer.compare(x, y);
        TreeSet<Integer> treeSet2 = new TreeSet<>(comparator);

    }

    /**
     * 类型推断示例
     */
    public void test4() {
        //这里会进行类型推断
        String[] strs = {"aaa", "bbb", "ccc"};
        //尖括号中不需要指定类型，通过类型推断推断出数据类型
        //jdk1.7中不能推断出，1.8之后可以通过目标上下文推断
        List<String> list = new ArrayList<>();
        show(new HashMap<>());
        //这里不能进行类型推断
       /* String[] strs2;
        strs2 = {"aaa", "bbb", "ccc"};*/
    }

    public void show(Map<String, Integer> map) {
    }


}
