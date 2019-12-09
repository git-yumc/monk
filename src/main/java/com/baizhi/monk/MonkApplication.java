package com.baizhi.monk;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yumc
 */
@SpringBootApplication
@MapperScan("com.baizhi.monk.dao")
public class MonkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonkApplication.class, args);
    }

}
