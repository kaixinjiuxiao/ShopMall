package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.District;
import com.wealth.shopmall.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
//@RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
public class DistrictMapperTest {

    @Autowired
    DistrictMapper mapper;

    @Test
    public  void getCity(){
        List<District> districts = mapper.getDistrictByParent("110100");
        for (District district : districts) {
            System.out.println(district.toString());
        }
    }
}
