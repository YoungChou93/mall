package com.mall.dao.impl;

import com.mall.dao.ShoppingCartDao;
import com.mall.entity.ShoppingCart;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public class ShoppingCartDaoImpl implements ShoppingCartDao {

    private SqlSessionTemplate sessionTemplate;

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public ShoppingCart selectByPrimaryKey(String id) {
        return sessionTemplate.selectOne( "com.mall.dao.ShoppingCartDao.selectByPrimaryKey", id);
    }

    @Override
    public ShoppingCart selectByMap(Map<String, Object> map) {
        return sessionTemplate.selectOne( "com.mall.dao.ShoppingCartDao.selectByMap", map);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return sessionTemplate.delete( "com.mall.dao.ShoppingCartDao.deleteByPrimaryKey", id);
    }

    @Override
    public int deleteByMap(Map<String, Object> map) {
        return sessionTemplate.delete( "com.mall.dao.ShoppingCartDao.deleteByMap", map);
    }

    @Override
    public int insertSelective(ShoppingCart record) {
        return sessionTemplate.insert( "com.mall.dao.ShoppingCartDao.insertSelective", record);
    }

    @Override
    public int updateByPrimaryKeySelective(ShoppingCart record) {
        return sessionTemplate.update( "com.mall.dao.ShoppingCartDao.updateByPrimaryKeySelective", record);
    }

    @Override
    public List<ShoppingCart> find(Map<String, Object> map) {
        return sessionTemplate.selectList( "com.mall.dao.ShoppingCartDao.find", map);
    }
}
