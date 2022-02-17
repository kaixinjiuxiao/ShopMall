package com.wealth.shopmall.service;

import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
//@RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
public class DistrictServiceTest {

    @Autowired
    IDistrictService service;

   @Test
    public void addaddress(){
      List<District> list = service.getDistrictList("86");
      for (District district : list) {
         System.out.println(district.toString());
      }
   }
}
