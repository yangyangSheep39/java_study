import com.fangyang.java9.modularity.Person;

/**
 * @Author yangyangsheep
 * @Description 模块化调用测试
 * @CreateTime 2025/3/28 15:43
 */
public class ModularityTest {

    public static void main(String[] args) {
        //在同一个模块当中是可以使用的
        Person person = new Person("John Doe", 30);
        System.out.println("Name: " + person.getName() + ", Age: " + person.getAge());
    }
}
