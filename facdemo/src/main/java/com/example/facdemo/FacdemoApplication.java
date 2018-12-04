package com.example.facdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.lianyun.aliyun4j.aliyun4jackcheck.*")
public class FacdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacdemoApplication.class, args);
    }
}
