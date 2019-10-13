package com.pinyougou.content.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.content.service.ContentService;
import com.pinyougou.mapper.TbContentMapper;
import com.pinyougou.pojo.PageResult;
import com.pinyougou.pojo.Result;
import com.pinyougou.pojo.TbContent;
import com.pinyougou.pojo.TbContentExample;
import com.pinyougou.pojo.TbContentExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	private TbContentMapper contentMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbContent> findAll() {
		return contentMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbContent> page=   (Page<TbContent>) contentMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public Result add(TbContent content) {
		try {
			contentMapper.insert(content);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"添加失败！");
		}
		return new Result(true,"增加成功！");
	}
	/**
	 * 修改
	 */
	@Override
	public Result update(TbContent content){
		try {
			contentMapper.updateByPrimaryKey(content);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false,"修改失败！");
		}

		return new Result(true,"修改成功!");
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbContent findOne(Long id){
		return contentMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public Result delete(Long[] ids) {
		for(Long id:ids){
			try {
				contentMapper.deleteByPrimaryKey(id);
			} catch (Exception e) {
				e.printStackTrace();
				return new Result(false,"删除失败！");
			}
		}
		return new Result(true,"删除成功！");
	}
	
	
		@Override
	public PageResult findPage(TbContent content, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbContentExample example=new TbContentExample();
		Criteria criteria = example.createCriteria();
		
		if(content!=null){			
						if(content.getTitle()!=null && content.getTitle().length()>0){
				criteria.andTitleLike("%"+content.getTitle()+"%");
			}
			if(content.getUrl()!=null && content.getUrl().length()>0){
				criteria.andUrlLike("%"+content.getUrl()+"%");
			}
			if(content.getPic()!=null && content.getPic().length()>0){
				criteria.andPicLike("%"+content.getPic()+"%");
			}
			if(content.getStatus()!=null && content.getStatus().length()>0){
				criteria.andStatusLike("%"+content.getStatus()+"%");
			}
	
		}
		
		Page<TbContent> page= (Page<TbContent>)contentMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}
	//根据分类id查询广告列表
	@Override
	public List<TbContent> findContentByCategoryId(long categoryId) {
		List<TbContent> content = (List<TbContent>) redisTemplate.boundHashOps("content").get(categoryId);
		if (content != null){
			System.out.println("从redis中取数据。。。。");
			return content;
		}
		TbContentExample tbContentExample = new TbContentExample();
		TbContentExample.Criteria criteria = tbContentExample.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> tbContents = contentMapper.selectByExample(tbContentExample);
		redisTemplate.boundHashOps("content").put(categoryId,tbContents);
		System.out.println("从数据库取数据放入到redis中");
		return tbContents;
	}

}
