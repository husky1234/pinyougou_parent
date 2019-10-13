package com.pinyougou.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-redis.xml")
public class Test {
    @Autowired
    private RedisTemplate redisTemplate;
     @org.junit.Test
    public void setValue(){
         redisTemplate.boundValueOps("name").set("zelin");
     }
     @org.junit.Test
     public void getValue(){
         String str = (String) redisTemplate.boundValueOps("name").get();
         System.out.println(str);
     }
     @org.junit.Test
     public void deleValue(){
         redisTemplate.delete("name");
     }
     @org.junit.Test
    public void Set(){
         redisTemplate.boundSetOps("historyname").add("刘备");
         redisTemplate.boundSetOps("historyname").add("张飞");
         redisTemplate.boundSetOps("historyname").add("关羽");
         redisTemplate.boundSetOps("historyname").add("赵云");
     }
     @org.junit.Test
    public void getSet(){
         Set historyname = redisTemplate.boundSetOps("historyname").members();
         Iterator iterator = historyname.iterator();
         while (iterator.hasNext()){
             System.out.println(iterator.next());
         }
     }
     @org.junit.Test
     public void deleSet(){
         redisTemplate.boundSetOps("historyname").remove("张飞");
         Set historyname = redisTemplate.boundSetOps("historyname").members();
         Iterator iterator = historyname.iterator();
         while(iterator.hasNext()){
             System.out.println(iterator.next());
         }
     }
     @org.junit.Test
    public void deleAll(){
         Set historyname = redisTemplate.boundSetOps("historyname").members();
         System.out.println(historyname);
     }
     @org.junit.Test
     public void listset(){
         redisTemplate.boundListOps("list").leftPush("1");
         redisTemplate.boundListOps("list").leftPushAll("2","3","4");
         redisTemplate.boundListOps("list").leftPush("5","6");
     }
     @org.junit.Test
    public void listGet(){
         List list = redisTemplate.boundListOps("list").range(0, 10);
         System.out.println(list);
     }
     public void hash(){
         redisTemplate.boundHashOps("namehash").put("1","唐僧");
     }
}
