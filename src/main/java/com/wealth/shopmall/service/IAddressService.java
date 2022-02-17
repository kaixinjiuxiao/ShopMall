package com.wealth.shopmall.service;


import com.wealth.shopmall.entity.Address;

/**
 * 地址
 */

public interface IAddressService {

    /**
     * 增加新的收获地址
     * @param address  地址
     * @param uid 用户id
     * @param username 用户名
     */
    void addNewAddress(Address address,
                          Integer uid,
                          String username);

}
