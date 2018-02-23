package com.mall.dao;

import com.mall.entity.User;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/13.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/13
 */
public interface UserDao{

    User selectByPrimaryKey(String id);

    User selectByMap(Map<String, Object> map);

    int deleteByPrimaryKey(String id);

    int deleteByMap(Map<String, Object> map);

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);

}
