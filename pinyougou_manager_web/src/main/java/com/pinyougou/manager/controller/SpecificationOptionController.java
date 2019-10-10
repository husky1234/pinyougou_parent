package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.group.Specification;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.sellergoods.service.SpecificationOptionService;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("/specificationOption")
public class SpecificationOptionController {
    @Reference
	SpecificationOptionService specificationOptionService;
	@RequestMapping("/addoptions")
	public Result addoptions(@RequestBody Specification specification){
		return specificationOptionService.addoptions(specification);
	}
	@RequestMapping("/getOptions")
	public Specification getOptions(Long id){
		return specificationOptionService.getOptions(id);
	}
	@RequestMapping("/dele")
	public Result deleteInfo(Long[] list){
	    return specificationOptionService.dele(list);
    }

}
