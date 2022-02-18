package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.Address;
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
public class AddressMapperTest {
/*
*  private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private String isDefault;
*
* */
    @Autowired
    AddressMapper mapper;

    @Test
    public void addAddress() {
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
        address.setAddress("梅溪湖");
        mapper.addAddress(address);

    }

    @Test
    public void getTotal(){
        System.out.println("数量=="+mapper.getAddressNums(6));
    }

    @Test
    public void getAll(){
        List<Address> list = mapper.getAllAddress(6);
        for (Address address : list) {
            System.out.println("数据=="+address.toString());
        }
    }

    @Test
    public void  getAddress(){
        System.out.println("dizhi =="+mapper.getAddressById(5).toString());
    }

    @Test
    public void setDefault(){
        System.out.println(mapper.setDefault(5,"张三",new Date())==1?"成功":"失败");
    }

    @Test
    public void setNoneDefault(){
        System.out.println(mapper.setNoneDefault(6)>=1?"成功":"失败");
    }
}
