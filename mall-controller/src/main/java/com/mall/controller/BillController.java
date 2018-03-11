package com.mall.controller;

import com.mall.entity.User;
import com.mall.service.BillService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhouyang on 2018/3/11.
 */
@Controller
@RequestMapping("/bill")
public class BillController {

    @Resource
    private BillService billService;

    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "sids", required = true)String sids,
                      @RequestParam(value = "numbers", required = true)String numbers)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        return billService.add(sids,numbers,user).toString();
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public String list(HttpServletRequest request, HttpServletResponse response)throws Exception{
        User user =(User)request.getSession().getAttribute("user");
        return billService.list(user).toString();
    }
}
