package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.MidiSystem;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

	@Reference
	private BrandService brandService;


	@RequestMapping("/getAll")
	public List<TbBrand> findAll() {
		return brandService.findAll();
	}

	@RequestMapping("/getBypage")
	public PageResult findPage(int page, int pagesize) {
		return brandService.getBypage(page,pagesize);
	}
	@RequestMapping("/getByentity")
	public PageResult findEntity(int page, int pagesize,@RequestBody(required = false) TbBrand tbBrand){
		return brandService.getByEntity(page,pagesize,tbBrand);
	}
	@RequestMapping("/addInfo")
	public Result addInfo(@RequestBody TbBrand tbBrand){
		return brandService.add(tbBrand);
	}
    @RequestMapping("delete")
	public Result delete(Long[] ids){
		return brandService.delete(ids);
	}
}