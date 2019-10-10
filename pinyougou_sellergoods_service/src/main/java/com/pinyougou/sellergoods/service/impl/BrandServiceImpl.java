package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.pojo.TbBrandExample;
import com.pinyougou.pojo.TbBrandExample.Criteria;
import com.pinyougou.sellergoods.service.BrandService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    public List<TbBrand> findAll() {

        brandMapper.selectByExample(null);
        return brandMapper.selectByExample(null);
    }

    @Override
    public PageResult getBypage(int page, int pagesize) {
        PageHelper.startPage(page, pagesize);
        Page<TbBrand> page1 = (Page<TbBrand>) brandMapper.selectByExample(null);
        return new PageResult(page1.getTotal(), page1.getResult());
    }

    @Override
    public PageResult getByEntity(int page, int pagesize, TbBrand tbBrand) {
        PageHelper.startPage(page, pagesize);
        TbBrandExample tbBrandExample = null;
        if (tbBrand != null) {
            tbBrandExample = new TbBrandExample();
            TbBrandExample.Criteria criteria = tbBrandExample.createCriteria();
            if (tbBrand.getName() != null) {
                criteria.andNameLike("%" + tbBrand.getName() + "%");
            }
            if (tbBrand.getFirstChar() != null) {
                criteria.andFirstCharLike("%" + tbBrand.getFirstChar() + "%");
            }
        }
        Page<TbBrand> page1 = (Page<TbBrand>) brandMapper.selectByExample(tbBrandExample);
        return new PageResult(page1.getTotal(), page1.getResult());
    }

    @Override
    public Result add(TbBrand tbBrand) {
        if (tbBrand.getId() != null && !"".equals(tbBrand.getId())) {
            int a = brandMapper.updateByPrimaryKey(tbBrand);
            if (a > 0) {
                return new Result(true, "修改成功！");
            } else {
                return new Result(false, "修改失败！");
            }
        } else {
            int a = brandMapper.insert(tbBrand);
            if (a > 0){
                return new Result(true,"添加成功！");
            }else{
                return new Result(false,"添加失败！");
            }
        }
    }

    @Override
    public Result delete(Long[] ids) {
        for (Long id : ids) {
            try {
                brandMapper.deleteByPrimaryKey(id);
            } catch (Exception e) {
                e.printStackTrace();
                //只要有一个失败就失败！
                return new Result(false,"删除失败！");
            }
        }
        //全部成功才算成功！
         return new Result(true,"删除成功！");
    }


}
