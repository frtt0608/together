package com.ssafy.hashtag;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @EnableOAuth2Client
@SpringBootApplication
@EnableTransactionManagement
public class SsafYhashtagApplication {
    public static void main(String[] args) {
      SpringApplication.run(SsafYhashtagApplication.class, args);
    }
}
