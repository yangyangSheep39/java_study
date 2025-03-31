package com.fangyang.java9;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @Author yangyangsheep
 * @Description Java9的try语句测试
 * @CreateTime 2025/3/31 23:06
 */
public class TryCatchTest {

    /**
     * try语句的一般写法
     */
    @Test
    public void testTryCommon() {
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(System.in);
            // 读取数据
            inputStreamReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    /**
     * 新特性的写法Java8：try的括号中的资源不用显式的处理资源的关闭，会自动关闭，多个资源可以使用分号隔开
     * Java8要求：资源必须在小括号当中实例化
     * Java9,可以在try()中调用已经实例化的资源对象
     */
    @Test
    public void testTryJava8() {
        //Java8
        try (InputStreamReader inputStreamReader = new InputStreamReader(System.in); OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out)) {
            // 读取数据
            inputStreamReader.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //Java9
        InputStreamReader inputStreamReader2 = new InputStreamReader(System.in);
        OutputStreamWriter outputStreamWriter2 = new OutputStreamWriter(System.out);
        try (inputStreamReader2; outputStreamWriter2) {
            // 读取数据
            inputStreamReader2.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
