package com.mall.controller;

import com.mall.entity.Goods;
import com.mall.entity.GoodsView;
import com.mall.entity.User;
import com.mall.service.BillService;
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

    @Resource
    private BillService billService;

    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest request,HttpServletResponse response, Goods goods, @RequestParam MultipartFile pic)throws Exception{
        String path=request.getSession().getServletContext().getRealPath("/");
        User user =(User)request.getSession().getAttribute("user");
        JSONObject jsonObject=goodsServcie.add(goods,pic,path,user);
        return jsonObject.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(HttpServletRequest request,HttpServletResponse response, Goods goods, @RequestParam MultipartFile pic)throws Exception{
        String path=request.getSession().getServletContext().getRealPath("/");
        User user =(User)request.getSession().getAttribute("user");
        JSONObject jsonObject=goodsServcie.update(goods,pic,path,user);
        return jsonObject.toString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "id", required = true)Integer id,HttpServletRequest request,HttpServletResponse response)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        JSONObject jsonObject=goodsServcie.delete(id,user);
        return jsonObject.toString();
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        return goodsServcie.load(user).toString();
    }

    @RequestMapping(value="/nobuy")
    @ResponseBody
    public String nobuy(HttpServletRequest request, HttpServletResponse response)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        return goodsServcie.loadNoBuy(user).toString();
    }

    @RequestMapping(value="/getdetail")
    @ResponseBody
    public ModelAndView getDetail(@RequestParam(value = "id", required = true)Integer id, HttpServletRequest request, HttpServletResponse response)throws Exception{
        ModelAndView mav = new ModelAndView();
        User user =(User)request.getSession().getAttribute("user");
        Goods goods=goodsServcie.loadById(id);
        mav.addObject(goods);
        if(user!=null && user.getType()==1){
            Double price=billService.getNewestBuyPrice(goods.getId(),user);
            mav.addObject("price",price);
        }
        mav.setViewName("/goodsdetail");
        return mav;
    }

    @RequestMapping(value="/modify")
    @ResponseBody
    public ModelAndView modify(@RequestParam(value = "id", required = true)Integer id, HttpServletRequest request)throws Exception{
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
