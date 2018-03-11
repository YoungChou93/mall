package com.mall.service.impl;

import com.mall.dao.ShoppingCartDao;
import com.mall.entity.Goods;
import com.mall.entity.ShoppingCart;
import com.mall.entity.User;
import com.mall.service.ShoppingCartService;
import com.mall.util.DaoDataAccessException;
import com.mall.util.ServiceRuntimeException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.dao.DataAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhouyang on 2018/3/10.
 */
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartDao shoppingCartDao;

    public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public JSONObject add(Integer gid, User user, Integer number) {
        JSONObject result = new JSONObject();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("gid", gid);
            map.put("uid", user.getId());
            ShoppingCart shoppingCart = shoppingCartDao.selectByMap(map);
            if (shoppingCart != null) {
                shoppingCart.setNumber(shoppingCart.getNumber() + number);
                shoppingCartDao.updateByPrimaryKeySelective(shoppingCart);
            } else {
                ShoppingCart sc = new ShoppingCart();
                sc.setGid(new Goods(gid));
                sc.setUid(user);
                sc.setNumber(number);
                shoppingCartDao.insertSelective(sc);
            }
            result.put("success", true);
        } catch (DataAccessException e) {
            e.printStackTrace();
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常");
        }
        return result;
    }

    @Override
    public JSONObject list(User user) {
        JSONObject result = new JSONObject();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("uid", user.getId());
            List<ShoppingCart> shoppingCartList = shoppingCartDao.find(map);
            JSONArray jsonArray = JSONArray.fromObject(shoppingCartList);
            result.put("success", true);
            result.put("data", jsonArray);
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常");
        }
        return result;
    }
}
