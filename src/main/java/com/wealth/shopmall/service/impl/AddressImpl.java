package com.wealth.shopmall.service.impl;

import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.mapper.AddressMapper;
import com.wealth.shopmall.service.IAddressService;
import com.wealth.shopmall.service.ex.AddressCountLimitException;
import com.wealth.shopmall.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class AddressImpl implements IAddressService {

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Autowired
    private AddressMapper mapper;


    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        int total = mapper.getAddressNums(uid);
        if (total >= maxCount) {
            throw new AddressCountLimitException("收货地址已达上限");
        }
        //补全数据
        address.setUid(uid);
        address.setIsDefault(total == 0 ? 1 : 0);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        //调用收货地址的方法
       int rows= mapper.addAddress(address);
       if(rows!=1){
           throw new InsertException("数据库操作失败");
       }
    }
}
