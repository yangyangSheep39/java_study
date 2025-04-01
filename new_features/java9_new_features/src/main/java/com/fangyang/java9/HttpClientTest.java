package com.fangyang.java9;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @Author yangyangsheep
 * @Description 新的HTTP客户端API测试
 * @CreateTime 2025/4/1 23:46
 */
public class HttpClientTest {

    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest req = HttpRequest.newBuilder(URI.create("https://www.baidu.com")).GET().build();
            HttpResponse<String> response = null;
            response = client.send(req, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.version().name());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
