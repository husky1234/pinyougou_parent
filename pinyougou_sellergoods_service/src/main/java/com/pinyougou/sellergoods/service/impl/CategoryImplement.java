package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.sellergoods.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
@Service
public class CategoryImplement implements CategoryService {
    @Autowired
    TbItemCatMapper tbItemCatMapper;
    @Override
    public List<TbItemCat> getById(Long id) {
        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(id);
        return tbItemCatMapper.selectByExample(tbItemCatExample);
    }

    @Override
    public Result addInfo(TbItemCat tbItemCat) {
        try {
            System.out.println(tbItemCat);
            tbItemCatMapper.insert(tbItemCat);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败！");
        }
        return new Result(true,"添加成功！");
    }

    @Override
    public Result deleInfo(long[] list) {
        for (long l : list) {
            try {
                tbItemCatMapper.deleteByPrimaryKey(l);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false,"删除失败！");
            }
        }
        return new Result(true,"删除成功！");
    }
}
