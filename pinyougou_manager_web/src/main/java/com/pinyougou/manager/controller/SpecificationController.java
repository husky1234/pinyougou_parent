package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.group.Specification;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Reference
    SpecificationService specificationService;
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll(){
        return specificationService.findAll();
    }
    @RequestMapping("/findbykey")
    public List<TbSpecification> findByKey(@RequestBody(required = false) TbSpecification tbSpecification) throws UnsupportedEncodingException {
        System.out.println(tbSpecification.getSpecName());
        return specificationService.findByKey(tbSpecification.getSpecName());
    }

}
