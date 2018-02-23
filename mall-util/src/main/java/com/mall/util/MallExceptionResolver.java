package com.mall.util;

import net.sf.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2018/2/19.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/19
 */
public class MallExceptionResolver  extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (true == isAjaxRequest(request)) { //ajax请求方式
            if (ex instanceof DataAccessException) {
                try {
                    JSONObject result = new JSONObject();
                    result.put("data", null);
                    result.put("errormsg", "数据库异常");
                    String msg = ex.getMessage();
                    if (null == msg || "".equals(msg)) {
                        msg = "请联系管理员，发生数据库异常";
                    }
                    result.put("errorinfo", msg);
                    ResponseUtil.write(response, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ex instanceof ServiceRuntimeException) {
                try {
                    JSONObject result = new JSONObject();
                    result.put("data", null);
                    result.put("errormsg", "服务层异常");
                    String msg = ex.getMessage();
                    if (null == msg || "".equals(msg)) {
                        msg = "请联系管理员，发生服务层异常";
                    }
                    result.put("errorinfo", msg);
                    ResponseUtil.write(response, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    JSONObject result = new JSONObject();
                    result.put("data", null);
                    result.put("errormsg", "系统异常");
                    String msg = ex.getMessage();
                    if (null == msg || "".equals(msg)) {
                        msg = "请联系管理员，发生系统异常";
                    }
                    result.put("errorinfo", msg);
                    ResponseUtil.write(response, result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        } else {//jsp方式提示错误信息
            request.setAttribute("errormsg", "操作失败，未知错误");
            return new ModelAndView("/error");
        }
    }
    private boolean isAjaxRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        if( null != header && ("XMLHttpRequest".equals(header)==true)){
            return true;
        }else{
            return false;
        }
    }

}
