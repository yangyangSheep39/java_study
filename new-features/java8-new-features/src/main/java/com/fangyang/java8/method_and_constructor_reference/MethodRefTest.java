package com.fangyang.java8.method_and_constructor_reference;

import com.fangyang.java8.entity.Employee;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 方法引用：若Lambda体中的内容有方法已经实现了，那么我们可以使用“方法引用”
 *      可以理解为方法引用时 Lambda 表达式的另外一种表现形式
 *
 * 主要三种语法格式：
 *      对象::实例方法名
 *      类::静态方法名
 *      类::实例方法名(非静态方法)
 * 注意：
 *   如果说在 Lambda 体中的功能或者说是方法，已经有方法完成了，我们就可以使用方法引用的方式去完成
 *        但是要保证需要实现的接口中的抽象方法中的参数列表和返回值类型
 *        需要和Lambda体中的方法参数列表和返回值保持一致
 *   如果说第一个参数是这个方法的调用者，第二个参数是你要调用的方法的参数时
 *        就可以使用  ClassName::method  的方式
 */
public class MethodRefTest {

    /**
     * 第一种语法格式
     */
    @Test
    public void test() {
        //println属于实例方法
        PrintStream ps1 = System.out;
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("con    abcdef");
        System.out.println("============================================================");
        Consumer<String> con1 = (x) -> ps1.println(x);
        con1.accept("con1    abcdef");
        System.out.println("============================================================");
        //方法引用改造
        PrintStream ps = System.out;
        Consumer<String> consumer = ps::println;
        consumer.accept("consumer    abcdef");
        System.out.println("============================================================");
        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("consumer2    abcdef");

    }

    @Test
    public void test1() {
        Employee employee = new Employee("张三", 19, 9999.99);
        Supplier<String> sup = () -> employee.getName();
        System.out.println(sup.get());
        System.out.println("============================================================");
        /*//这里不能成功编译，因为返回值的类型不同
        Supplier<String> sup1 = () -> employee.getAge();
        */
        Supplier<Integer> sup1 = employee::getAge;
        System.out.println(sup1.get());
    }

    /**
     * 第二种语法格式
     */
    @Test
    public void test2() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        com.compare(10, 11);

        Comparator<Integer> comparator = Integer::compare;
    }

    /**
     * 第三种语法格式
     */
    @Test
    public void test3() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);

        BiPredicate<String, String> biPredicate2 = String::equals;
    }
}
