package com.mall.service;

import com.mall.entity.User;
import net.sf.json.JSONObject;

/**
 * Created by Zhouyang on 2018/3/11.
 */
public interface BillService {

    JSONObject add(String sids,String numbers,User user);

    JSONObject list(User user);
}
