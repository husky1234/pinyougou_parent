package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.sellergoods.service.CategoryService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Reference
    CategoryService categoryService;
    @RequestMapping("/levelInfo")
    public List<TbItemCat> levelInfo(Long level){
        return categoryService.getById(level);
    }
    @RequestMapping("/addInfo")
    public Result addInfo(@RequestBody TbItemCat tbItemCat){
        tbItemCat.setId(null);
        return categoryService.addInfo(tbItemCat);
    }
    @RequestMapping("deleInfo")
    public Result deleInfo(long[] list){
        return categoryService.deleInfo(list);
    }
}
