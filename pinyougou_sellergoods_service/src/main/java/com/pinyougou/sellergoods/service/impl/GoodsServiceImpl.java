package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.pinyougou.group.Goods;
import com.pinyougou.group.Specification;
import com.pinyougou.mapper.*;
import com.pinyougou.pojo.*;
import com.pinyougou.sellergoods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;


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
	@Autowired
	private  TbBrandMapper brandMapper;
	@Autowired
	private TbItemMapper itemMapper;


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
		goodsMapper.insert(goods.getGoods());
		//2.向tbGoodsDesc表（spu商品描述表）中添加数据
		//2.1)得到刚才添加的商品id并且赋值给goodsDesc表的主键
		goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
		//2.2）向tbGoodsDesc表中添加数据
		goodsDescMapper.insert(goods.getGoodsDesc());

		//3.向tb_item表中添加数据(sku商品表)...
		//3.1)从组合对象中得到items集合
		List<TbItem> items = goods.getItems();
		//3.2)遍历此商品列表
		for (TbItem item : items) {
			//设置标题
			item.setTitle(goods.getGoods().getGoodsName());
			//设置商品id
			item.setGoodsId(goods.getGoods().getId());
			//设置三级分类id
			item.setCategoryid(goods.getGoods().getCategory3Id());
			//设置category名称
            item.setCategory(tbItemCatMapper.selectByPrimaryKey(goods.getGoods().getCategory3Id()).getName());
			item.setCreateTime(new Date());
			item.setUpdateTime(new Date());
			//设置品牌名称
			TbBrand tbBrand = brandMapper.selectByPrimaryKey(goods.getGoods().getBrandId());
			item.setBrand(tbBrand.getName());
			itemMapper.insert(item);
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

	@Override
	public List addCategoryList() {
		return goodsMapper.selectByExample(null);
	}

	@Override
	public List getLevelAllInfo() {

		return tbItemCatMapper.selectByExample(null);
	}
}
