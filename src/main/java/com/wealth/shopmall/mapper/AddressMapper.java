package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

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

    /**
     * 获取用户所有的收获地址
     * @param uid  用户UID
     * @return
     */
    List<Address> getAllAddress(Integer uid);

    /**
     * 获取地址信息
     * @param aid 地址id
     * @return
     */
    Address getAddressById(Integer aid);

    /**
     * 修改收货地址为非默认
     * @param uid
     * @return
     */
    Integer setNoneDefault(Integer uid);


    /**
     * 设置默认地址状态
     * @param aid  地址id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return
     */
    Integer setDefault(@Param("aid") Integer aid,
                       @Param("modifiedUser") String modifiedUser,
                       @Param("modifiedTime")Date modifiedTime);
}
