package com.zhou.redis;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.zhou.redis")
@MapperScan(basePackages = "com.zhou.redis.dao")
public class RedisLockApp {

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApp.class, args);
    }
}
