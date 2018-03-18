package com.mall.service.impl;

import com.mall.dao.UserDao;
import com.mall.entity.User;
import com.mall.service.UserService;
import com.mall.util.DaoDataAccessException;
import com.mall.util.MD5Util;
import com.mall.util.ServiceRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger("com.mall");

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Map<String, Object> login(String username, String password) {
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        try {
            if(null==username || password==null){
                throw new ServiceRuntimeException("账号密码不能为空");
            }
            User user = userDao.selectByMap(map);
            if (null == user) {
                result.put("errorMsg", "账号错误!");
            } else {
                if (!user.getPassword().equals(password)) {
                    result.put("errorMsg", "密码错误!");
                } else {
                    result.put("user", user);
                }
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
            logger.error("UserServiceImpl.login"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("UserServiceImpl.login"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("UserServiceImpl.login", e);
            throw new ServiceRuntimeException("未知异常");
        }
        return result;
    }

    @Override
    public void logout(HttpSession session) {
        try {
            session.removeAttribute("user");
        } catch (ServiceRuntimeException e) {
            logger.error("UserServiceImpl.logout"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("UserServiceImpl.logout", e);
            throw new ServiceRuntimeException("未知异常");
        }
    }
}
