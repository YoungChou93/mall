package com.mall.controller;


import com.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpSession session, @RequestParam(value = "username", required = true)String username,
                              @RequestParam(value = "password", required = true)String password)
            throws Exception {
        ModelAndView mav = new ModelAndView();

        Map<String,Object> result=userService.login(username,password);
        if(null!=result.get("errorMsg")){
            mav.addObject("errorMsg", result.get("errorMsg"));
            mav.setViewName("/login");
        }else{
            session.setAttribute("user",result.get("user"));
            mav.setViewName("redirect:/main.action");
        }

        return mav;
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView loginView(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        return mav;
    }

    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session)
            throws Exception {
        ModelAndView mav = new ModelAndView();
        userService.logout(session);
        mav.setViewName("redirect:/login.action");
        return mav;
    }

    @RequestMapping("/main")
    public ModelAndView main()
            throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/main");
        return mav;
    }
}
