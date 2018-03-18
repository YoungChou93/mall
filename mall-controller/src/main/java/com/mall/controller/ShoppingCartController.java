package com.mall.controller;

import com.mall.entity.User;
import com.mall.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhouyang on 2018/3/10.
 */
@Controller
@RequestMapping("/cart")
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "gid", required = true)Integer gid,
                      @RequestParam(value = "number", required = true)Integer number)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        return shoppingCartService.add(gid,user,number).toString();
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        return shoppingCartService.load(user).toString();
    }
}
