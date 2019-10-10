package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {
    @Reference
    SellerService sellerService;
    @RequestMapping("/register")
    public Result register(@RequestBody TbSeller tbSeller){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(tbSeller.getPassword());
        tbSeller.setPassword(password);
        return sellerService.register(tbSeller);
    }
    @RequestMapping("/loginName")
    public Map getName(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map login = new HashMap();
        login.put("loginName",name);
        return login;
    }
}
