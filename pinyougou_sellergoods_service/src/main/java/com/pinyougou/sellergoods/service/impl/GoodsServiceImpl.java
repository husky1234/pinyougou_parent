package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.group.Goods;
import com.pinyougou.group.Specification;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private TbGoodsMapper goodsMapper;
	@Autowired
	private TbGoodsDescMapper goodsDescMapper;
	@Autowired
	private TbItemMapper tbItemMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	@Autowired
	private TbTypeTemplateMapper tbTypeTemplateMapper;
	@Autowired
	private TbSpecificationMapper tbSpecificationMapper;
	@Autowired
	private TbSpecificationOptionMapper tbSpecificationOptionMapper;


	@Override
	public List<Goods> getAll() {
		List<Goods> list = new ArrayList<>();
		List<TbGoods> tbGoods = goodsMapper.selectByExample(null);
		for (TbGoods tbGood : tbGoods) {
			TbGoodsDesc tbGoodsDesc = goodsDescMapper.selectByPrimaryKey(tbGood.getId());
			TbItemExample tbItemExample = new TbItemExample();
			TbItemExample.Criteria criteria = tbItemExample.createCriteria();
			criteria.andGoodsIdEqualTo(tbGood.getId());
			List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
			Goods goods = new Goods();
			goods.setGoods(tbGood);
			goods.setGoodsDesc(tbGoodsDesc);
			goods.setItems(tbItems);
			list.add(goods);
		}
		return list;
	}

	@Override
	public List<TbItem> getAllItems() {
		return tbItemMapper.selectByExample(null);
	}

	@Override
	public Result addInfo(Goods goods) {
		try {
			goodsMapper.insert(goods.getGoods());
			TbGoodsExample tbGoodsExample = new TbGoodsExample();
			TbGoodsExample.Criteria criteria = tbGoodsExample.createCriteria();
			tbGoodsExample.setOrderByClause("id desc");
			criteria.andGoodsNameEqualTo(goods.getGoods().getGoodsName());
			List<TbGoods> tbGoods = goodsMapper.selectByExample(tbGoodsExample);
			TbGoodsDesc goodsDesc = goods.getGoodsDesc();
			goodsDesc.setGoodsId(tbGoods.get(0).getId());
			goodsDescMapper.insert(goodsDesc);
			List<TbItem> items = goods.getItems();
			for (TbItem item : items) {
				tbItemMapper.insert(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"添加失败！");
		}
		return new Result(true,"添加成功！");
	}

	@Override
	public List getLevel(long id) {
		TbItemCatExample tbItemCatExample = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
		criteria.andParentIdEqualTo(id);
		return tbItemCatMapper.selectByExample(tbItemCatExample);
	}

	@Override
	public TbTypeTemplate getTemplateInfo(long id) {
		return tbTypeTemplateMapper.selectByPrimaryKey(id);
	}

	@Override
	public List getspecs(long[] ids) {
		//数据传回格式，试着这么传，不行前端再转化格式或者后端修改
		List<Specification> list = new ArrayList<>();
		for (long id : ids) {
			Specification specification = new Specification();
			//根据传过来的id来获取相应的规格名称
			TbSpecification tbSpecification = tbSpecificationMapper.selectByPrimaryKey(id);
			specification.setSpec(tbSpecification);
			//根据id找到相应的选项名称用map合并起来传送到前端页面
			TbSpecificationOptionExample optionExample = new TbSpecificationOptionExample();
			TbSpecificationOptionExample.Criteria criteria = optionExample.createCriteria();
			criteria.andSpecIdEqualTo(tbSpecification.getId());
			List<TbSpecificationOption> tbSpecificationOptions = tbSpecificationOptionMapper.selectByExample(optionExample);
			specification.setSpecificationOptionList(tbSpecificationOptions);
			list.add(specification);
		}
		return list;
	}
}
