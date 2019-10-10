package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.group.Specification;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
		@Autowired
		TbSpecificationMapper tbSpecificationMapper;
	@Override
	public List<TbSpecification> findByKey(String name) {
		TbSpecificationExample example = new TbSpecificationExample();
		TbSpecificationExample.Criteria criteria = example.createCriteria();
		criteria.andSpecNameLike("%"+name+"%");
		List<TbSpecification> list = tbSpecificationMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<TbSpecification> findAll() {
		return tbSpecificationMapper.selectByExample(null);
	}
}
