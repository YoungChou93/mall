package com.mall.dao.impl;

import com.mall.dao.UserDao;
import com.mall.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/13.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/13
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionTemplate sessionTemplate;

    public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public User selectByPrimaryKey(String id) {
        return sessionTemplate.selectOne( "com.mall.dao.UserDao.selectByPrimaryKey", id);
    }

    @Override
    public User selectByMap(Map<String, Object> map) {
        return sessionTemplate.selectOne( "com.mall.dao.UserDao.selectByMap", map);
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return sessionTemplate.delete( "com.mall.dao.UserDao.deleteByPrimaryKey", id);
    }

    @Override
    public int deleteByMap(Map<String, Object> map) {
        return sessionTemplate.delete( "com.mall.dao.UserDao.deleteByMap", map);
    }

    @Override
    public int insertSelective(User record) {
        return sessionTemplate.insert( "com.mall.dao.UserDao.insertSelective", record);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return sessionTemplate.update( "com.mall.dao.UserDao.updateByPrimaryKeySelective", record);
    }
}
