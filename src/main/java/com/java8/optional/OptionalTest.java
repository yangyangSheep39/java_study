package com.java8.optional;

import com.java8.entity.Employee;
import com.java8.entity.Godness;
import com.java8.entity.Man;
import com.java8.entity.NewMan;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * Optional容器类
 *
 * 常用方法：
 *      Optional.of(T t)：创建一个Optional实例
 *      Optional.empty()：只是创建一个空的Optional实例
 *      Optional.ofNullable(T t)：若t不为null，创建Optional实例，否则创建空实例
 *      isPresent()：判断是否包含值（）
 *      orElse(T t)：如果对象包含值，返回该值，否则返回t
 *      orElseGet(Supplier s)：如果对象包含值，返回该值，否则返回s获取的值
 *      map(Function f)：如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
 *      flatMap(Function mapper)：与map类似，要求返回值必须是Optional
 */
public class OptionalTest {

    @Test
    public void test1() {
        Optional<Employee> op = Optional.of(new Employee());
        //Get方法返回一个实体对象
        Employee employee = op.get();
        //注意事项，of() 方法不能传一个null值
        //在此处可以最快的定位到空指针异常
        Optional<Employee> optional = Optional.of(null);
        optional.get();
    }

    @Test
    public void test2() {
        Optional<Employee> optional = Optional.empty();
        System.out.println(optional.get());
    }

    @Test
    public void test3() {
        Optional<Employee> optional = Optional.ofNullable(null);
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        Employee employee = optional.orElse(new Employee("张三", 18, 5891.446, Employee.Status.FREE));
        System.out.println(employee);
        System.out.println("============================================================");
        Optional<Employee> optional1 = Optional.ofNullable(null);
        if (optional1.isPresent()) {
            System.out.println(optional.get());
        }
        Employee employee1 = optional.orElseGet(() -> new Employee("李四", 18, 5891.446, Employee.Status.BUSY));
        System.out.println(employee1);
    }

    @Test
    public void test4() {
        Optional<Employee> optional = Optional.ofNullable(new Employee("张三", 18, 5891.446, Employee.Status.FREE));

        Optional<String> s = optional.map(e -> e.getName());
        System.out.println(s.get());
        System.out.println("============================================================");
        //要求返回值必须用Optional包装一下
        Optional<String> s1 = optional.flatMap(e -> Optional.of(e.getName()));
        System.out.println(s1.get());
    }

    /**
     * 应用
     */
    @Test
    public void test5() {
        Man man = new Man();
        String godness = getGodness(man);
        System.out.println(godness);
        System.out.println("============================================================");
        Optional<NewMan> newMan = Optional.ofNullable(null);
        String godnessWithOptional = getGodnessWithOptional(newMan);
        System.out.println(godnessWithOptional);
    }

    //获取一个男人心中女神的名字
    public String getGodness(Man man) {
        if (man != null) {
            Godness godness = man.getGodness();
            if (godness != null) {
                return godness.getName();
            }
        }
        return "无女神";
    }

    public String getGodnessWithOptional(Optional<NewMan> man) {
        return man.orElse(new NewMan()).getGodness().orElse(new Godness("苍老师")).getName();
    }

}
