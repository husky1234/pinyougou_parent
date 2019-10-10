package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSellerMapper;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.pojo.TbSellerExample;
import com.pinyougou.pojo.TbSellerExample.Criteria;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private TbSellerMapper sellerMapper;


	@Override
	public Result register(TbSeller tbSeller) {
		try {
			sellerMapper.insert(tbSeller);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"注册失败请重新尝试！");
		}
		return new Result(true,"注册成功！");
	}

	@Override
	public List<TbSeller> getAllCheckInfo() {
		return sellerMapper.selectByExample(null);
	}

	@Override
	public Result agree(String id) {
		try {
			 sellerMapper.updateStatus(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"修改失败！");
		}
		return new Result(true,"修改成功！");
	}

	@Override
	public TbSeller getInfoById(String username) {
		TbSellerExample tbSellerExample = new TbSellerExample();
		TbSellerExample.Criteria criteria = tbSellerExample.createCriteria();
		criteria.andSellerIdEqualTo(username);
		return sellerMapper.selectByExample(tbSellerExample).get(0);
	}
}
