package com.mall.dao.impl;

import com.mall.dao.GoodsDao;
import com.mall.entity.Goods;
import com.mall.entity.GoodsView;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public class GoodsDaoImpl implements GoodsDao {

    private SqlSessionTemplate sessionTemplate;

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public Goods selectByPrimaryKey(Integer id) {
        return sessionTemplate.selectOne( "com.mall.dao.GoodsDao.selectByPrimaryKey", id);
    }

    @Override
    public Goods selectByMap(Map<String, Object> map) {
        return sessionTemplate.selectOne( "com.mall.dao.GoodsDao.selectByMap", map);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sessionTemplate.delete( "com.mall.dao.GoodsDao.deleteByPrimaryKey", id);
    }

    @Override
    public int deleteByMap(Map<String, Object> map) {
        return sessionTemplate.delete( "com.mall.dao.GoodsDao.deleteByMap", map);
    }

    @Override
    public int insertSelective(Goods record) {
        return sessionTemplate.insert( "com.mall.dao.GoodsDao.insertSelective", record);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return sessionTemplate.update( "com.mall.dao.GoodsDao.updateByPrimaryKeySelective", record);
    }

    @Override
    public List<Goods> find(Map<String, Object> map) {
        return sessionTemplate.selectList( "com.mall.dao.GoodsDao.find", map);

    }

    @Override
    public List<GoodsView> findByUser(Map<String, Object> map) {
        return sessionTemplate.selectList( "com.mall.dao.GoodsDao.findByUser", map);
    }
}
