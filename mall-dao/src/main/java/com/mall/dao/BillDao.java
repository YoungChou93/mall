package com.mall.dao;

import com.mall.entity.Bill;
import com.mall.entity.BillView;

import java.util.List;
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

    List<BillView> find(Map<String,Object> map);

    Double getTotal(Map<String,Object> map);

    Double getNewestBuy(Map<String,Object> map);
}
