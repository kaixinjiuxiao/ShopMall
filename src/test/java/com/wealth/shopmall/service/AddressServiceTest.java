package com.wealth.shopmall.service;

import com.wealth.shopmall.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//@RunWith(SpringRunner.class)注解是一个测试启动器，可以加载Springboot测试注解
@RunWith(SpringRunner.class)
public class AddressServiceTest {

    @Autowired
    IAddressService service;

   @Test
    public void addaddress(){
       Address address = new Address();
       address.setUid(6);
       address.setName("manager");
       address.setProvinceName("湖南省");
       address.setProvinceCode("1001");
       address.setCityName("长沙市");
       address.setCityCode("1001");
       address.setAreaName("岳麓区");
       address.setAreaCode("001");
       address.setPhone("15000000000");
       address.setZip("413200");
       address.setAddress("天祥水井湾");
       service.addNewAddress(address,6,"manager");
   }
}
