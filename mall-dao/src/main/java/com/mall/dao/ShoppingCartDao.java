package com.mall.dao;

import com.mall.entity.ShoppingCart;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public interface ShoppingCartDao {

    ShoppingCart selectByPrimaryKey(String id);

    ShoppingCart selectByMap(Map<String, Object> map);

    int deleteByPrimaryKey(String id);

    int deleteByMap(Map<String, Object> map);

    int insertSelective(ShoppingCart record);

    int updateByPrimaryKeySelective(ShoppingCart record);
}
