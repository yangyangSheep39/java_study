/**
 * @Author yangyangsheep
 * @Description 模块化测试-导出
 * @CreateTime 2025/3/28 16:09
 */
module java9_new_features {
    requires lombok;
    requires org.junit.jupiter.api;
    requires java.net.http;
    exports com.fangyang.java9.modularity;//确保导出包
}

