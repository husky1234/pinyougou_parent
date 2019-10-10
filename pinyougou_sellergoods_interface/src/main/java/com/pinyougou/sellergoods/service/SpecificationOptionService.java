package com.pinyougou.sellergoods.service;

import com.pinyougou.group.Specification;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSpecificationOption;

import java.util.List;


public interface SpecificationOptionService {
	Result addoptions(Specification specification);

    Specification getOptions(Long id);

    Result dele(Long[] array);
}
