package com.fangyang.java8.lambda.practice;


import com.fangyang.java8.entity.Employee;
import com.fangyang.java8.lambda.practice.interf.MyFunctionInteger;
import com.fangyang.java8.lambda.practice.interf.MyFunctionLong;
import com.fangyang.java8.lambda.practice.interf.MyFunctionString;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 需求演示
 */
public class LambdaBasicGrammarDemand {
    List<Employee> employees = Arrays.asList(
            new Employee("李四", 19, 5555.55),
            new Employee("王五", 14, 4444.44),
            new Employee("赵六", 25, 3333.33),
            new Employee("田七", 11, 2222.22),
            new Employee("张三", 39, 1111.11)
    );

    /**
     * 对一个数进行运算
     */
    @Test
    public void test() {
        Integer operation = operation(100, (x) -> x * x);
        System.out.println(operation);

        System.out.println("============================================================");

        System.out.println(operation(200, (y) -> y + 200));
    }


    public Integer operation(Integer num, MyFunctionInteger myFunction) {
        return myFunction.getValue(num);
    }

    /**
     * 通过Collections.sort()方法，通过定制排序比较两个Employee（先按照年龄比，年龄相同按姓名比），使用Lambda作为出安迪参数
     */
    @Test
    public void sortEmployee() {
        Collections.sort(employees, (e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getName().compareTo(e2.getName());
            } else {
                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee employee : employees) {
            System.out.println(employee);

        }
    }

    /**
     * ①声明函数式接口，接口中声明抽象方法public String getValue(String str);
     * ②声明类，类中编写方法使用接口作为参数，将一个字符串转换成大写，并作为方法的返回值
     * ③再将一个字符串的第二个和第四个索引位置进行截取字串
     */
    @Test
    public void testStrHandler() {
        String strUpperCase = strHandler("Lambda表达式威武", str -> str.toUpperCase());
        System.out.println(strUpperCase);

        System.out.println("============================================================");

        String strSub = strHandler("威武Lambda表达式", str -> str.substring(2, 8));
        System.out.println(strSub);

    }

    //接口处理字符串数据
    public String strHandler(String str, MyFunctionString myFunctionString) {
        return myFunctionString.getValue(str);
    }

    /**
     * ①声明一个带两个泛型的函数式接口，泛型类型为<T,R></> T为参数 ，R为返回值
     * ②接口中声明对应抽象方法
     * ③在类中声明方法，使用接口作为参数，计算两个long型参数的和
     * ④再计算两个long型参数的乘积
     */
    @Test
    public void testLongOperation() {
        longOperation(100L, 200L, (x, y) -> x + y);

        System.out.println("============================================================");

        longOperation(10L, 39L, (x, y) -> x * y);
    }

    //接口处理两个Long值
    public void longOperation(Long l1, Long l2, MyFunctionLong<Long, Long> myFunctionLong) {
        System.out.println(myFunctionLong.getValue(l1, l2));
    }
}
