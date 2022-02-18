package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;

    @RequestMapping("/add_new_address")
    public HttpResult<Void> addAddress(Address address,
                                       HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.addNewAddress(address, uid, username);
        return new HttpResult<>(OK, "添加成功");
    }

    @RequestMapping("/getAll_address")
    public HttpResult<List<Address>> getAll(HttpSession session){
        Integer uid = getUidFromSession(session);
        List<Address> list = addressService.getAllAddress(uid);
        return new HttpResult<>(OK,"success",list);
    }

    @RequestMapping("{aid}/set_default")
    public HttpResult<Void> setDefaultAddress(HttpSession session,@PathVariable("aid") Integer aid){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid,uid,username);
        return new HttpResult<>(OK,"修改成功");
    }

    @RequestMapping("/set_default2")
    public HttpResult<Void> setDefaultAddress2(HttpSession session,Integer aid){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.setDefault(aid,uid,username);
        return new HttpResult<>(OK,"修改成功");
    }
}
