package com.mall.service;

import com.mall.entity.GoodsView;
import com.mall.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.mall.entity.Goods;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
public interface GoodsServcie {

     JSONObject add(Goods goods, MultipartFile pic,String path,User user);

     JSONObject update(Goods goods, MultipartFile pic,String path,User user);

     JSONObject delete(Integer id,User user);

     JSONObject load(User user);

    //买家没有购买的商品
     JSONObject loadNoBuy(User user);
    //通过id查询商品
     Goods  loadById(Integer id);
}
