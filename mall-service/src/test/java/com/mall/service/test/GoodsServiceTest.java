package com.mall.service.test;

import com.mall.service.GoodsServcie;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Zhouyang on 2018/3/3.
 */
public class GoodsServiceTest {
    private ApplicationContext ctx = null;
    private GoodsServcie goodsServcie = null;

    @Before
    public void before() {
        ctx = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
        goodsServcie = ctx.getBean(GoodsServcie.class);
    }

    @Test
    public void test(){
        System.out.println(goodsServcie.list(null).toString());
    }
}
