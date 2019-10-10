package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/shop")
public class SellerController {

	@Reference
	private SellerService sellerService;
	@RequestMapping("/allCheckInfo")
	public List<TbSeller> getAllInfo(){
		return sellerService.getAllCheckInfo();
	}
	@RequestMapping("/agree")
   public Result agree(String id){
		return sellerService.agree(id);
	}

	
}
