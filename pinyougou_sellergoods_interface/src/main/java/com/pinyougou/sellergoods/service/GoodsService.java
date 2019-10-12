package com.pinyougou.sellergoods.service;


import com.pinyougou.group.Goods;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface GoodsService {


	List<Goods> getAll();

    List<TbItem> getAllItems();

    Result addInfo(Goods goods);

    List getLevel(long id);

    TbTypeTemplate getTemplateInfo(long id);

    List getspecs(long[] ids);

    List addCategoryList();

    List getLevelAllInfo();
}
