package com.mall.controller;

import com.mall.entity.Goods;
import com.mall.entity.GoodsView;
import com.mall.entity.User;
import com.mall.service.GoodsServcie;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        JSONObject jsonObject=goodsServcie.add(goods,pic,path,user);
        return jsonObject.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request,HttpServletResponse response, Goods goods, @RequestParam MultipartFile pic){
        String path=request.getSession().getServletContext().getRealPath("/");
        User user =(User)request.getSession().getAttribute("user");
        JSONObject jsonObject=goodsServcie.update(goods,pic,path,user);
        return jsonObject.toString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id", required = true)Integer id,HttpServletRequest request,HttpServletResponse response){
        User user =(User)request.getSession().getAttribute("user");
        JSONObject jsonObject=goodsServcie.delete(id,user);
        return jsonObject.toString();
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response){
        User user =(User)request.getSession().getAttribute("user");
        JSONArray jsonArray =goodsServcie.list(user);
        return jsonArray.toString();
    }

    @RequestMapping(value="/nobuy")
    @ResponseBody
    public String nobuy(HttpServletRequest request, HttpServletResponse response){
        User user =(User)request.getSession().getAttribute("user");
        JSONArray jsonArray =goodsServcie.listNoBuy(user);
        return jsonArray.toString();
    }

    @RequestMapping(value="/getdetail")
    @ResponseBody
    public ModelAndView getDetail(@RequestParam(value = "id", required = true)Integer id, HttpServletRequest request, HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        Goods goods=goodsServcie.loadById(id);
        mav.addObject(goods);
        mav.setViewName("/goodsdetail");
        return mav;
    }

    @RequestMapping(value="/modify")
    @ResponseBody
    public ModelAndView modify(@RequestParam(value = "id", required = true)Integer id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        User user =(User)request.getSession().getAttribute("user");
        if (user==null || user.getType()!=0){
            mav.setViewName("redirect:/user/main.action");
        }else {
            Goods goods = goodsServcie.loadById(id);
            mav.addObject(goods);
            mav.setViewName("/addgoods");
        }
        return mav;
    }
}
