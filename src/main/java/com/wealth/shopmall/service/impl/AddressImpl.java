package com.wealth.shopmall.service.impl;

import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.mapper.AddressMapper;
import com.wealth.shopmall.service.IAddressService;
import com.wealth.shopmall.service.IDistrictService;
import com.wealth.shopmall.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class AddressImpl implements IAddressService {

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Autowired
    private AddressMapper mapper;

    //在添加用户的收获地址的业务层依赖于IDistrictService的业务层接口
    @Autowired
    private IDistrictService districtService;

    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        int total = mapper.getAddressNums(uid);
        if (total >= maxCount) {
            throw new AddressCountLimitException("收货地址已达上限");
        }

        //对省市区进行补全
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

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

    @Override
    public List<Address> getAllAddress(Integer uid) {
        List<Address> list = mapper.getAllAddress(uid);

        List<Address> newList = new ArrayList<>();
        for (int i = 0; i <list.size() ; i++) {
            Address  address = new Address();
            address.setAid(list.get(i).getAid());
            address.setAddress(list.get(i).getAddress());
            address.setPhone(list.get(i).getPhone());
            address.setName(list.get(i).getName());
            address.setTag(list.get(i).getTag());
            newList.add(address);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = mapper.getAddressById(aid);
        if(address==null){
            throw  new AddressNotFoundException("收货地址不存在");
        }
        if(!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法数据");
        }
        int total = mapper.getAddressNums(uid);
        int rows=mapper.setNoneDefault(uid);
        if(rows!=total){
            throw new UpdateException("数据更新异常");
        }
        rows = mapper.setDefault(aid,username,new Date());
        if(rows!=1){
            throw new UpdateException("数据更新异常");
        }
    }
}
