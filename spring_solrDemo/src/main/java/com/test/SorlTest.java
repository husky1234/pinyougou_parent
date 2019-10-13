package com.test;

import com.pinyougou.pojo.TbItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-solr.xml")
public class SorlTest {
    @Autowired
    private SolrTemplate solrTemplate;
    @Test
    public  void testAdd(){
        TbItem tbItem = new TbItem();
        tbItem.setId(2L);
        tbItem.setTitle("三星 Note手机");
        tbItem.setBrand("三星");
        tbItem.setPrice(new BigDecimal("8999"));
        tbItem.setSeller("三星专卖南山店");
        tbItem.setImage("http://www.baidu.com/images/a.jpg");
        tbItem.setGoodsId(1L);
        tbItem.setCategory("手机类");
        solrTemplate.saveBean(tbItem);
        solrTemplate.commit();
    }
    @Test
    public void testFindOne(){
        TbItem tbItem = getTbItem(1L);
        System.out.println(tbItem);
    }

    private TbItem getTbItem(Long id) {
        Criteria criteria = new Criteria("id");
        criteria.is(id);
        Query query = new SimpleQuery(criteria);
        return solrTemplate.queryForObject(query, TbItem.class);
    }

    @Test
    public void testUpdate(){
        TbItem tbItem = getTbItem(1L);
        tbItem.setTitle("三星-Lead");
        solrTemplate.saveBean(tbItem);
        solrTemplate.commit();
    }
    @Test
    public void testItems() {
        List<TbItem> items = new ArrayList<TbItem>();
        for (int i = 1; i <= 100; i++) {
            TbItem item = new TbItem();
            item.setId(new Long(i + ""));
            item.setTitle("三星 Note 手机" + i);
            item.setBrand("三星");
            item.setPrice(new BigDecimal("29999" + i));
            item.setSeller("三星专卖南山分部" + i);
            item.setImage("http://www.baidu.com/images/a.jpg");
            item.setGoodsId(1L);
            item.setCategory("手机类");
            items.add(item);
        }
           solrTemplate.saveBeans(items);
           solrTemplate.commit();
    }
    @Test
    public void testPage(){
        Query query = new SimpleQuery("*:*");
        query.setOffset(10);
        query.setRows(10);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);
        long totalElements = tbItems.getTotalElements();
        int totalPages = tbItems.getTotalPages();
        System.out.println("总共有：" + totalPages +"页");
        System.out.println("总记录数：" + totalElements);
        List<TbItem> contents1 = tbItems.getContent();
        for (TbItem tbItem : contents1) {
            System.out.println(tbItem);
        }
    }
    @Test
    public void testCriteria(){
        Query query = new SimpleQuery();
        Criteria criteria = new Criteria("item_title").contains("2");
        query.addCriteria(criteria);
        ScoredPage<TbItem> scoredPage = solrTemplate.queryForPage(query, TbItem.class);
        int totalPages = scoredPage.getTotalPages();
        long totalElements = scoredPage.getTotalElements();
        int numberOfElements = scoredPage.getNumberOfElements();
        int size = scoredPage.getSize();
        int number = scoredPage.getNumber();
        System.out.println("totalPages" + totalPages +"==totalElements"+ totalElements);
        System.out.println("numberOfElements"+ numberOfElements);
        System.out.println("size"+ size);
        System.out.println("numb" + number);
        List<TbItem> content = scoredPage.getContent();
        for (TbItem tbItem : content) {
            System.out.println(tbItem);
        }
    }
    @Test
    public void testDeletAll(){
        Query query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
