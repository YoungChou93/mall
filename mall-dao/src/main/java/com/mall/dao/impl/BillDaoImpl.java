package com.mall.dao.impl;

import com.mall.dao.BillDao;
import com.mall.entity.Bill;
import com.mall.entity.BillView;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public class BillDaoImpl implements BillDao {

    private SqlSessionTemplate sessionTemplate;

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public Bill selectByPrimaryKey(String id) {
        return sessionTemplate.selectOne( "com.mall.dao.BillDao.selectByPrimaryKey", id);
    }

    @Override
    public Bill selectByMap(Map<String, Object> map) {
        return sessionTemplate.selectOne( "com.mall.dao.BillDao.selectByMap", map);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return sessionTemplate.delete( "com.mall.dao.BillDao.deleteByPrimaryKey", id);
    }

    @Override
    public int deleteByMap(Map<String, Object> map) {
        return sessionTemplate.delete( "com.mall.dao.BillDao.deleteByMap", map);
    }

    @Override
    public int insertSelective(Bill record) {
        return sessionTemplate.insert( "com.mall.dao.BillDao.insertSelective", record);
    }

    @Override
    public int updateByPrimaryKeySelective(Bill record) {
        return sessionTemplate.update( "com.mall.dao.BillDao.updateByPrimaryKeySelective", record);
    }

    @Override
    public List<BillView> find(Map<String, Object> map) {
        return sessionTemplate.selectList( "com.mall.dao.BillDao.find", map);
    }

    @Override
    public Double getTotal(Map<String, Object> map) {
        return sessionTemplate.selectOne( "com.mall.dao.BillDao.getTotal", map);
    }

    @Override
    public Double getNewestBuy(Map<String, Object> map) {
        return sessionTemplate.selectOne( "com.mall.dao.BillDao.getNewestBuy", map);
    }
}
