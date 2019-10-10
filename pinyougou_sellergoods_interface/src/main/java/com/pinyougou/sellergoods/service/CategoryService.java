package com.pinyougou.sellergoods.service;

import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbItemCat;

import java.util.List;

public interface CategoryService {
    List<TbItemCat> getById(Long id);

    Result addInfo(TbItemCat tbItemCat);

    Result deleInfo(long[] list);
}
