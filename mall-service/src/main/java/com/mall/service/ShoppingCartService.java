package com.mall.service;

import com.mall.entity.User;
import net.sf.json.JSONObject;

/**
 * Created by Zhouyang on 2018/3/10.
 */
public interface ShoppingCartService {

    JSONObject add (Integer gid,User user,Integer number);

    JSONObject load (User user);

}
