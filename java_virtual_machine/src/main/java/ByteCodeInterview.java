import org.junit.Test;

/**
 * @Author yangyangsheep
 * @Description 字节码指令查看
 * @CreateTime 2025/3/12 11:06
 */
public class ByteCodeInterview {

    //面试题：i++和++i有什么区别？
    @Test
    public void test1() {
        int i = 10;
        i++;
        System.out.println(i);
    }

    @Test
    public void test2() {
        int i = 10;
        ++i;
        System.out.println(i);
    }

    @Test
    public void test3() {
        int i = 10;
        i = i++;
        System.out.println(i);
    }

    @Test
    public void test4() {
        int i = 10;
        i = ++i;
        System.out.println(i);
    }

    @Test
    public void test5() {
        int i = 2;
        i *= i++; // i = i * i++
        System.out.println(i);
    }

    @Test
    public void test6() {
        int i = 10;
        i = i + (i++) + (++i);
        System.out.println(i);
    }

    //包装类对象的缓存问题
    @Test
    public void test7() {
        //Integer有一个IntegerCache的-128~127的缓存数组
        Integer i1 = 10;
        Integer i2 = 10;
        System.out.println(i1 == i2); //true

        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i3 == i4); //false

        Boolean b1 = true;
        Boolean b2 = true;
        System.out.println(b1 == b2);
    }

    //String的对比
    @Test
    public void test8() {
        String s1 = new String("hello") + new String("world");
        String s2 = "helloworld";
        System.out.println(s1 == s2); //false

        String s3 = new String("hello1") + new String("world1");
        //jdk6和8有差异，6的字符串常量池在方法区（永久代），7及以后的字符串常量池在堆中
        s3.intern();
        String s4 = "hello1world1";
        System.out.println(s3 == s4); //true
    }
}
