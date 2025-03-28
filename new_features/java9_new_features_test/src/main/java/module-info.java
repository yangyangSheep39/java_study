/**
 * @Author yangyangsheep
 * @Description 模块化测试-导入
 * @CreateTime 2025/3/28 16:12
 */
module java9_new_features_test {
    exports com.fangyang.java9.test;
    requires java9_new_features;
    requires org.junit.jupiter.api; // 如果使用 JUnit 进行测试
    opens com.fangyang.java9.test to org.junit.jupiter.api; // 允许 JUnit 反射访问
}
