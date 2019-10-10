package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSeller;

import java.util.List;

public interface SellerService {
	//注册信息接口
	Result register(TbSeller tbSeller);
   //获取全部的商家信息包括已经通过审核的，没有通过审核的所有信息
	List<TbSeller> getAllCheckInfo();

    Result agree(String id);

    TbSeller getInfoById(String username);
}
