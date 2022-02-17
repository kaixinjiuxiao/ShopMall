package com.wealth.shopmall.controller;

import com.wealth.shopmall.Utils.HttpResult;
import com.wealth.shopmall.entity.Address;
import com.wealth.shopmall.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
}
