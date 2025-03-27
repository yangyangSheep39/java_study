import java.util.Optional;

/**
 * @Author yangyangsheep
 * @Description test
 * @CreateTime 2025/3/26 16:30
 */
public class Test {

    @org.junit.Test
    public void test6() {
        Optional.ofNullable(null).ifPresentOrElse(System.out::println, () -> {
            try {
                throw new Exception("空指针异常");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
