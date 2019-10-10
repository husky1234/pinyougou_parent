package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Reference
	private TypeTemplateService typeTemplateService;

	@RequestMapping("/findAll")
	public List<TbTypeTemplate> findAll(){
		return typeTemplateService.findAll();
	}
    @RequestMapping("findByKey")
	public List<TbTypeTemplate> findByKey(String key) throws UnsupportedEncodingException {
		System.out.println(key);
		String str = new String(key.getBytes("iso-8859-1"),"utf-8");
		System.out.println(str);
		return typeTemplateService.findByKey(str);
	}
	@RequestMapping("/selectBrandList")
	public List<Map> getBrandList(){
		return typeTemplateService.selectBrandList();
	}
	@RequestMapping("/selectSpecList")
	public List<Map> getSpecList(){
		return typeTemplateService.selectSpecLsit();
	}
	@RequestMapping("/saveInfo")
	public Result saveInfo(@RequestBody TbTypeTemplate tbTypeTemplate){
		return typeTemplateService.save(tbTypeTemplate);
	}
	@RequestMapping("/deleTemplate")
	public Result deleteTemplate(Long[] ids){
		return typeTemplateService.delete(ids);
	}
}
