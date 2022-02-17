package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.entity.District;
import com.wealth.shopmall.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class DistrictController extends BaseController{

    @Autowired
    private IDistrictService service;

    @RequestMapping("/getDistrictList")
    public HttpResult<List<District>>  getDistrictList(String parent){

        List<District> districtList = service.getDistrictList(parent);
        return new HttpResult<List<District>>(OK,"success",districtList);
    }
}
