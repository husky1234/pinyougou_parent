package com.pinyougou.sellergoods.service;

import com.pinyougou.group.Specification;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.TbSpecification;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;


/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface SpecificationService {

	List<TbSpecification> findByKey( String name) throws UnsupportedEncodingException;

    List<TbSpecification> findAll();
}
