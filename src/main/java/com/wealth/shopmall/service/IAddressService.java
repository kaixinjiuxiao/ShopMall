package com.wealth.shopmall.service;


import com.wealth.shopmall.entity.Address;

import java.util.List;

/**
 * 地址
 */

public interface IAddressService {

    /**
     * 增加新的收货地址
     * @param address  地址
     * @param uid 用户id
     * @param username 用户名
     */
    void addNewAddress(Address address,
                          Integer uid,
                          String username);

    /**
     * 获取所有的收货地址
     * @param uid
     * @return
     */
    List<Address> getAllAddress(Integer uid);


    /**
     * 设置默认地址
     * @param aid  地址ID
     * @param uid  用户ID
     * @param username  用户名
     */
    void setDefault(Integer aid,
                    Integer uid,
                    String username);
}
