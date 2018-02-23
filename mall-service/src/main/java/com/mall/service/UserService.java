package com.mall.service;

import com.mall.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
public interface UserService {

    public Map<String,Object> login(String username, String password);

    public void logout(HttpSession session);
}
