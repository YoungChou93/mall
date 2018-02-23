package com.mall.controller;

import com.mall.entity.Goods;
import com.mall.entity.User;
import com.mall.service.GoodsServcie;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/2/19.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/19
 */

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsServcie goodsServcie;

    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest request,HttpServletResponse response, Goods goods, @RequestParam MultipartFile pic){
        String path=request.getSession().getServletContext().getRealPath("/");
        User user =(User)request.getSession().getAttribute("user");
        JSONObject jsonObject=goodsServcie.addGoods(goods,pic,path,user);
        return jsonObject.toString();
    }

    @RequestMapping("/list")
    public String list(HttpServletResponse response, Goods goods,MultipartFile pic){

        return null;
    }
}
