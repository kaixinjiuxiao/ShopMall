package com.wealth.shopmall;

import com.wealth.shopmall.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest

class ShopMallApplicationTests {

    @Autowired
    DataSource source;


    @Test
    void contextLoads() {
    }


    @Test
    void getConnection(){
        try {
            System.out.println(source.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
