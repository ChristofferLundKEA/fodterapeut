package com.example.fodterapeut;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FodterapeutApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(FodterapeutApplication.class, args);
        System.out.println(run.getBean("dataSource"));
    }

}
