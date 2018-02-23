package com.mall.dao;

import com.mall.entity.Goods;
import com.mall.entity.GoodsView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public interface GoodsDao {
    Goods selectByPrimaryKey(String id);

    Goods selectByMap(Map<String, Object> map);

    int deleteByPrimaryKey(String id);

    int deleteByMap(Map<String, Object> map);

    int insertSelective(Goods record);

    int updateByPrimaryKeySelective(Goods record);

    List<Goods> find(Map<String, Object> map);

    List<GoodsView> findByUser(Map<String, Object> map);
}
