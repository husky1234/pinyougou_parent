package com.pinyougou.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.pojo.TbItem;
import com.pinyougou.search.service.ItemSearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemSearchImp implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;
    @Override
    public Map<String, Object> search(Map map) {
       Map<String,Object> maps  = new HashMap<>();
        Query simpleQuery = new SimpleQuery("*:*");
        Criteria criteria;
        if (StringUtils.isBlank(map.get("keywords")+"")){
            criteria = new Criteria("item_keywords");
        }else {
            criteria = new Criteria("item_keywords").is(map.get("keywords"));
        }
        simpleQuery.addCriteria(criteria);
        simpleQuery.setOffset(0);
        simpleQuery.setRows(40);
        ScoredPage<TbItem> scoredPage = solrTemplate.queryForPage(simpleQuery, TbItem.class);
        List<TbItem> content = scoredPage.getContent();
        maps.put("rows",content);
        return maps;
    }
}
