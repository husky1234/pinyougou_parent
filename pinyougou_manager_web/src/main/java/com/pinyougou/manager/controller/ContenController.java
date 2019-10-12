package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentCategoryService;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContenController {
    @Reference
    ContentService contentService;
    @Reference
    ContentCategoryService contentCategoryService;
    @RequestMapping("/getAll")
    public List<TbContent> getAll(){
        return contentService.findAll();
    }
    @RequestMapping("/getAllContentCategory")
    public List<TbContentCategory> getAllContent(){
        return contentCategoryService.findAll();
    }
    @RequestMapping("/save")
    public Result save(@RequestBody TbContent tbContent){
        return  contentService.add(tbContent);
    }
}
