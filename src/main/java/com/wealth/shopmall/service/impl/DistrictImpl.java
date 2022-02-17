package com.wealth.shopmall.service.impl;

import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.entity.District;
import com.wealth.shopmall.mapper.AddressMapper;
import com.wealth.shopmall.mapper.DistrictMapper;
import com.wealth.shopmall.service.IAddressService;
import com.wealth.shopmall.service.IDistrictService;
import com.wealth.shopmall.service.ex.AddressCountLimitException;
import com.wealth.shopmall.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class DistrictImpl implements IDistrictService {


    @Autowired
    private DistrictMapper mapper;


    @Override
    public List<District> getDistrictList(String parent) {
        List<District> list = mapper.getDistrictByParent(parent);
        //为了避免无效数据传输，可以将无效数据设置为null
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }
}
