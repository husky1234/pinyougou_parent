package com.pinyougou.shop.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.group.Goods;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Reference
    GoodsService service;
    @RequestMapping("/findAll")
    List<Goods> getAll(){
        return service.getAll();
    }
    @RequestMapping("/findItems")
    List<TbItem> getAllItems(){
        return service.getAllItems();
    }
    @RequestMapping("/addInfo")
    Result addInfo(@RequestBody Goods goods){
        return service.addInfo(goods);
    }
    @RequestMapping("/getLevel")
    List getLevel(long id){
        return service.getLevel(id);
    }
    @RequestMapping("/getTemplateInfo")
    TbTypeTemplate getTemplateInfo(long id){
        return service.getTemplateInfo(id);
    }
    @RequestMapping("/getspecs")
    List getspecs(long[] ids){
        return service.getspecs(ids);
    }
}
