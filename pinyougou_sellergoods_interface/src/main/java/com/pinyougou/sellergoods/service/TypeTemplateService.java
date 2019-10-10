package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {

	List<TbTypeTemplate> findAll();

	List<TbTypeTemplate> findByKey(String name);

	List<Map> selectBrandList();

	List<Map> selectSpecLsit();

    Result save(TbTypeTemplate tbTypeTemplate);

	Result delete(Long[] ids);
}
