package com.pinyougou.shop.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImp implements UserDetailsService {
    private SellerService sellerService;
    public void setSellerService(SellerService sellerService){
        this.sellerService = sellerService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TbSeller tbSeller = sellerService.getInfoById(username);
        if (tbSeller != null){
            if (tbSeller.getStatus().equals("1")){
                String password = tbSeller.getPassword();
                System.out.println(password);
                List<GrantedAuthority> authorityList = new ArrayList<>();
                authorityList.add(new SimpleGrantedAuthority("ROLE_SELLER"));
                return new User(username,password,authorityList);
            }
        }
        return null;
    }
}
