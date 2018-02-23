package com.mall.service;

import com.mall.entity.User;
import net.sf.json.JSONObject;
import com.mall.entity.Goods;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
public interface GoodsServcie {

    public JSONObject addGoods(Goods goods, MultipartFile pic,String path,User user);

    public List<> list(User user);
}
