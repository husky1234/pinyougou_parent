package com.pinyougou.solr.test;

import com.pinyougou.solr.util.SolrUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by WF on 2019/10/12 10:13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestSolrUtil {
    @Autowired
    private SolrUtil solrUtil;
    //1.测试导入到索引库
    @Test
    public void test01(){
        solrUtil.importInfo();
    }
}
