package com.wealth.shopmall.mapper;

import com.wealth.shopmall.entity.District;


import java.util.List;

/**
 * 省市区接口
 */
public interface DistrictMapper {

    /**
     * 获取省市区信息
     * @param parent
     * @return
     */
    List<District> getDistrictByParent(String parent);


    /**
     * 获取城市名
     * @param code
     * @return
     */
    String findNameByCode(String code);
}
