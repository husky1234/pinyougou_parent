package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbBrand;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface BrandService {
	public List<TbBrand> findAll();
	PageResult getBypage(int page, int pagesize);

	PageResult getByEntity(int page, int pagesize, TbBrand tbBrand);

    Result add(TbBrand tbBrand);

    Result delete(Long[] ids);
}
