package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.Address;

/**
 * 地址模块
 */
public interface AddressMapper {

    /**
     * 添加地址
     * @param address  地址
     * @return
     */
    Integer addAddress(Address address);

    /**
     * 获取用户收获地址数量
     * @param uid  用户id
     * @return 地址数量
     */
    Integer getAddressNums(Integer uid);
}
