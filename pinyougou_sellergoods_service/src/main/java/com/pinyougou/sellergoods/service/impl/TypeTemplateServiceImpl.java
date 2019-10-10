package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbTypeTemplateMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.pojo.TbTypeTemplateExample.Criteria;
import com.pinyougou.sellergoods.service.TypeTemplateService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {

	@Autowired
	private TbTypeTemplateMapper typeTemplateMapper;
	@Autowired
	private TbBrandMapper tbBrandMapper;
	@Autowired
	private TbSpecificationMapper tbSpecificationMapper;


	@Override
	public List<TbTypeTemplate> findAll() {
		return typeTemplateMapper.selectByExample(null);
	}

	@Override
	public List<TbTypeTemplate> findByKey(String name) {
		TbTypeTemplateExample tbTypeTemplateExample = new TbTypeTemplateExample();
		TbTypeTemplateExample.Criteria criteria = tbTypeTemplateExample.createCriteria();
		criteria.andNameLike("%"+name+"%");
		return typeTemplateMapper.selectByExample(tbTypeTemplateExample);
	}

	@Override
	public List<Map> selectBrandList() {
		return tbBrandMapper.selectBrandList();
	}

	@Override
	public List<Map> selectSpecLsit() {
		return tbSpecificationMapper.findSpecList();
	}

	@Override
	public Result save(TbTypeTemplate tbTypeTemplate) {

		if (tbTypeTemplate.getId()!=null){
			try {
				typeTemplateMapper.deleteByPrimaryKey(tbTypeTemplate.getId());
				typeTemplateMapper.insert(tbTypeTemplate);
				return new Result(true,"修改成功！");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false,"修改失败！");
			}
		}else {
			try {
				typeTemplateMapper.insert(tbTypeTemplate);
				return new Result(true,"添加成功！");
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false,"添加失败！");
			}
		}
	}

	@Override
	public Result delete(Long[] ids) {
		for (Long id : ids) {
			try {
				typeTemplateMapper.deleteByPrimaryKey(id);
			} catch (Exception e) {
				e.printStackTrace();
				return  new Result(false,"删除失败");
			}
		}
		return new Result(true,"删除成功!");
	}
}
