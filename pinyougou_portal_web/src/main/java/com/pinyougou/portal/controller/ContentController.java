package com.pinyougou.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.pojo.TbContent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by WF on 2019/10/11 10:04
 */
@RestController
@RequestMapping("content")
public class ContentController {
    @Reference
    private ContentService contentService;
    //1.根据分类id查询广告列表
    @RequestMapping("findContentByCategoryId")
    public List<TbContent> findContentByCategoryId(long categoryId){
        return contentService.findContentByCategoryId(categoryId);
    }
}
