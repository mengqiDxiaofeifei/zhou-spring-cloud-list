package com.zhou.zookeeper;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "com.zhou.zookeeper")
@MapperScan(basePackages = "com.zhou.zookeeper.dao")
public class ZookeeperLockApp {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperLockApp.class, args);
    }
}
