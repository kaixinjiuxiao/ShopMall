package com.wealth.shopmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//添加mapper扫描，启动时会自动扫描添加
@MapperScan("com.wealth.shopmall.mapper")
public class ShopMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopMallApplication.class, args);
    }

}
