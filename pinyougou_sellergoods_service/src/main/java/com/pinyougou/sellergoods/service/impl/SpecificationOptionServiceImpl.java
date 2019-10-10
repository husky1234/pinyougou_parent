package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.group.Specification;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.*;
import com.pinyougou.pojo.TbSpecificationOptionExample.Criteria;
import com.pinyougou.sellergoods.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Service
public class SpecificationOptionServiceImpl implements SpecificationOptionService {

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;
    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Override
    public Result addoptions(Specification specification) {
        /*
         * 这个是从后面反推上来的顺序是先添加在修改然后发现修改的保存与
         * 添加是一个链接这样的话需要先判断再执行相应的操作
         * 由于没有这个值是没有把id传过来所以先要获取相应的id才能操作
         * 注下面的四行代码原来的位置是在执行完添加操作后才执行的现在放上来是为了满足
         * 修改操作因为修改要根据id来操作
         *
         * */
//
        //如果前面的步骤没有错误就执行下面的代码来来进行添加选项，首先获得相应的id才能添加不然导致数据分类不正确
        TbSpecificationExample tbSpecificationExample = new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = tbSpecificationExample.createCriteria();
        criteria.andSpecNameEqualTo(specification.getSpec().getSpecName());
        List<TbSpecification> list = specificationMapper.selectByExample(tbSpecificationExample);
        //如果list中有值代表执行的是修改操作
        if(list.size()>0){
           Long id = list.get(0).getId();
            TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria1 = tbSpecificationOptionExample.createCriteria();
            criteria1.andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(tbSpecificationOptionExample);
        }else {
            try {
                specificationMapper.insert(specification.getSpec());
            } catch (Exception e) {
                e.printStackTrace();
                if (list.size()>0) {
                    return new Result(false, "修改失败！");
                } else {
                    return new Result(false, "添加失败！");
                }
            }
        }
         //这个操作的目的是：如果是添加操作上面的id就没有所要添加后才有所以要再查询一次
         list = specificationMapper.selectByExample(tbSpecificationExample);
        //遍历添加操作
        List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
        for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
            tbSpecificationOption.setSpecId(list.get(0).getId());
            try {
                specificationOptionMapper.insert(tbSpecificationOption);
            } catch (Exception e) {
                e.printStackTrace();
				if (list.size()>0) {
					return new Result(false, "修改失败！");
				} else {
					return new Result(false, "添加失败！");
				}
            }
        }
		if (list.size()>0) {
			return new Result(true, "修改成功！");
		} else {
			return new Result(true, "添加成功！");
		}
    }

    @Override
    public Specification getOptions(Long id) {
        //设置查询条件
        TbSpecificationOptionExample tbSpecificationOptionExample = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = tbSpecificationOptionExample.createCriteria();
        criteria.andSpecIdEqualTo(id);
        //得到选项的集合
        List<TbSpecificationOption> tbSpecificationOptions = specificationOptionMapper.selectByExample(tbSpecificationOptionExample);
        Specification specification = new Specification();
        specification.setSpecificationOptionList(tbSpecificationOptions);
        //返回对应的值这里没有查询规格表在前端直接获得无须查表
        //好像如果不查整个spec属性为null，导致无法直接设置从页面获取的值
        //尝试直接将spec.specName设为null代表有这么一个属性
        //经过验证分析正确如果不经过下面的代码会出现无法设置相应值的问题
        TbSpecification specification1 = new TbSpecification();
        specification1.setSpecName(null);
        //这个值可以不设置应为前端页面不需要这个值
        specification1.setId(0L);
        specification.setSpec(specification1);
        return specification;
    }

    @Override
    public Result dele(Long[] array) {
        for (Long aLong : array) {
            try {
                specificationMapper.deleteByPrimaryKey(aLong);
                TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
                Criteria criteria = optionExample.createCriteria();
                criteria.andSpecIdEqualTo(aLong);
                specificationOptionMapper.deleteByExample(optionExample);
            } catch (Exception e) {
                e.printStackTrace();
                return new Result(false,"删除失败！");
            }
        }
        return new Result(true,"删除成功！");
    }
}
