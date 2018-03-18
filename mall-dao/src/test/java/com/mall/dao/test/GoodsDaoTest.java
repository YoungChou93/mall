package com.mall.dao.test;

import com.mall.dao.GoodsDao;
import com.mall.entity.Goods;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public class GoodsDaoTest {

    private ApplicationContext ctx = null;
    private GoodsDao goodsDao = null;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        goodsDao = ctx.getBean(GoodsDao.class);
    }

    @Test
    public void test(){
        /*Goods goods=new Goods();
        goods.setTitle("test");
        goods.setImageurl("http://127.0.0.1:8080");
        goods.setAbstracts("摘要");
        goods.setContent("內容");
        goods.setPrice(111.11);
        goodsDao.insertSelective(goods);*/


        System.out.println(goodsDao.getTotal());
    }
}
