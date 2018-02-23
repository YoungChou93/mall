package com.mall.dao;

import com.mall.entity.Bill;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public interface BillDao {

    Bill selectByPrimaryKey(String id);

    Bill selectByMap(Map<String, Object> map);

    int deleteByPrimaryKey(String id);

    int deleteByMap(Map<String, Object> map);

    int insertSelective(Bill record);

    int updateByPrimaryKeySelective(Bill record);
}
