package com.mall.service.impl;

import com.mall.dao.GoodsDao;
import com.mall.entity.Goods;
import com.mall.entity.User;
import com.mall.service.GoodsServcie;
import com.mall.util.DaoDataAccessException;
import com.mall.util.ServiceRuntimeException;
import net.sf.json.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
public class GoodsServiceImpl implements GoodsServcie {

    private GoodsDao goodsDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public JSONObject addGoods(Goods goods, MultipartFile pic,String path,User user) {
        JSONObject result=new JSONObject();
        try {
            if (null != pic && pic.getOriginalFilename().length()>0) {
                SimpleDateFormat myformat = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = myformat.format(new Date()); // id前缀
                String oldFileNme=pic.getOriginalFilename();
                String newFileName = date + oldFileNme.substring(oldFileNme.lastIndexOf("."));
                String relativePath=user.getUsername()+System.getProperty("file.separator")+"picture"+System.getProperty("file.separator") + newFileName;
                File newFile = new File(path +relativePath);
                pic.transferTo(newFile);
                goods.setImagepath(path +user.getUsername()+"/picture/" + newFileName);
            }
            goods.setUid(user);
            goodsDao.insertSelective(goods);
            result.put("success",true);
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常");

        }
        return result;
    }
}
