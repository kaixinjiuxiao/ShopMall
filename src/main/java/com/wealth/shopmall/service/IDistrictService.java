package com.wealth.shopmall.service;


import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.entity.District;

import java.util.List;

/**
 * 省市区
 */

public interface IDistrictService {

    /**
     * 获取省市区地址信息
     * @param parent  父地区code
     * @return
     */
    List<District> getDistrictList(String parent);

}
