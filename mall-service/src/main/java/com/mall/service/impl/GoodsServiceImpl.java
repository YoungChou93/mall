package com.mall.service.impl;

import com.mall.dao.BillDao;
import com.mall.dao.GoodsDao;
import com.mall.dao.ShoppingCartDao;
import com.mall.entity.Goods;
import com.mall.entity.GoodsView;
import com.mall.entity.User;
import com.mall.service.GoodsServcie;
import com.mall.util.DaoDataAccessException;
import com.mall.util.ServiceRuntimeException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/18.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/18
 */
public class GoodsServiceImpl implements GoodsServcie {

    private static final Logger logger = Logger.getLogger("com.mall");

    private GoodsDao goodsDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public JSONObject add(Goods goods, MultipartFile pic, String path, User user) {
        JSONObject result = new JSONObject();
        try {
            if(null != pic && pic.getSize()>1024*1024){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "图片大小超过1MB");
                return result;
            }
            if(goodsDao.getTotal()>=1000){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "商品数量达到上限");
                return result;
            }
            if((goods.getImageurl()==null || goods.getImageurl()=="") && pic==null){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "图片为空");
                return result;
            }
            if(!goods.verify()){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "参数错误");
            }
            if (null != pic && pic.getOriginalFilename().length() > 0) {
                SimpleDateFormat myformat = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = myformat.format(new Date()); // id前缀
                String oldFileNme = pic.getOriginalFilename();
                String newFileName = date + oldFileNme.substring(oldFileNme.lastIndexOf("."));
                String relativePath = user.getUsername() + System.getProperty("file.separator") + "picture" + System.getProperty("file.separator") + newFileName;
                File newFile = new File(path + relativePath);
                pic.transferTo(newFile);
                goods.setImagepath(user.getUsername() + "/picture/" + newFileName);
            }
            goods.setUid(user);
            goodsDao.insertSelective(goods);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("newest", user.getId());
            Goods g=goodsDao.selectByMap(map);
            result.put("success", true);
            result.put("id", g.getId());
        } catch (DataAccessException e) {
            logger.error("GoodsServiceImpl.add"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("GoodsServiceImpl.add"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("GoodsServiceImpl.add", e);
            throw new ServiceRuntimeException("未知异常");
        }
        return result;
    }

    @Override
    public JSONObject load(User user) {
        JSONObject result = new JSONObject();
        JSONArray jsonArray = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            if (user != null) {
                map.put("type", user.getType());
                map.put("uid", user.getId());
                List<GoodsView> goods = goodsDao.findByUser(map);
                jsonArray = JSONArray.fromObject(goods);
            } else {
                List<Goods> goods = goodsDao.find(map);
                jsonArray = JSONArray.fromObject(goods);
            }
            result.put("success", true);
            result.put("data",jsonArray);
        } catch (DataAccessException e) {
            logger.error("GoodsServiceImpl.load"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("GoodsServiceImpl.load"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("GoodsServiceImpl.load", e);
            throw new ServiceRuntimeException("未知异常");

        }
        return result;
    }

    @Override
    public JSONObject loadNoBuy(User user) {
        JSONObject result = new JSONObject();
        JSONArray jsonArray = null;
        try {
            if( null==user){
                throw new ServiceRuntimeException("未登录");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nobuy", user.getId());
            List<Goods> goods = goodsDao.find(map);
            jsonArray = JSONArray.fromObject(goods);
            result.put("success", true);
            result.put("data",jsonArray);
        } catch (DataAccessException e) {
            logger.error("GoodsServiceImpl.loadNoBuy"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("GoodsServiceImpl.loadNoBuy"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("GoodsServiceImpl.loadNoBuy", e);
            throw new ServiceRuntimeException("未知异常");
        }
        return result;
    }

    @Override
    public Goods loadById(Integer id) {
        Goods goods = null;
        try {
            if( null==id){
                throw new ServiceRuntimeException("参数不能为空");
            }
            goods = goodsDao.selectByPrimaryKey(id);
        } catch (DataAccessException e) {
            logger.error("GoodsServiceImpl.loadById"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("GoodsServiceImpl.loadById"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("GoodsServiceImpl.loadById", e);
            throw new ServiceRuntimeException("未知异常");
        }
        return goods;
    }

    @Override
    public JSONObject update(Goods goods, MultipartFile pic, String path, User user) {
        JSONObject result = new JSONObject();
        try {
            if(null != pic && pic.getSize()>1024*1024){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "图片大小超过1MB");
                return result;
            }
            if((goods.getImageurl()==null || goods.getImageurl()=="") && pic==null){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "图片为空");
                return result;
            }
            if(!goods.verify()){
                result.put("success", false);
                result.put("errormsg", "出现错误");
                result.put("errorinfo", "参数错误");
            }
            Goods goodsold = goodsDao.selectByPrimaryKey(goods.getId());
            if (goodsold == null) {
                result.put("success", false);
                result.put("errormsg", "无该商品");
                return result;
            }

            if (!goodsold.getUid().getId().equals(user.getId())) {
                result.put("errormsg", "该商品不是该用户发布的不能修改");
                return result;
            }

            if (null != pic && pic.getOriginalFilename().length() > 0) {
                SimpleDateFormat myformat = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = myformat.format(new Date()); // id前缀
                String oldFileNme = pic.getOriginalFilename();
                String newFileName = date + oldFileNme.substring(oldFileNme.lastIndexOf("."));
                String relativePath = user.getUsername() + System.getProperty("file.separator") + "picture" + System.getProperty("file.separator") + newFileName;
                File newFile = new File(path + relativePath);
                pic.transferTo(newFile);
                goods.setImagepath(user.getUsername() + "/picture/" + newFileName);
                goods.setImageurl("");
            }
            goods.setUid(user);
            goodsDao.updateByPrimaryKeySelective(goods);
            result.put("success",true);
            result.put("id",goodsold.getId());
        } catch (DataAccessException e) {
            logger.error("GoodsServiceImpl.update"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常" + e.toString());
        } catch (ServiceRuntimeException e) {
            logger.error("GoodsServiceImpl.update"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("GoodsServiceImpl.update", e);
            throw new ServiceRuntimeException("未知异常" + e.toString());
        }
        return result;
    }

    @Override
    public JSONObject delete(Integer id, User user) {
        JSONObject result = new JSONObject();
        try {
            if(null==user || null==id){
                throw new ServiceRuntimeException("参数不能为空");
            }
            Goods goodsold = goodsDao.selectByPrimaryKey(id);
            if (goodsold == null) {
                result.put("errormsg", "无该商品");
                return result;
            }
            if (!goodsold.getUid().getId().equals(user.getId())) {
                result.put("errormsg", "该商品不是该用户发布的不能删除");
                return result;
            }
            goodsDao.deleteByPrimaryKey(id);
            result.put("success",true);
        } catch (DataAccessException e) {
            logger.error("GoodsServiceImpl.delete"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常" + e.toString());
        } catch (ServiceRuntimeException e) {
            logger.error("GoodsServiceImpl.delete"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("GoodsServiceImpl.delete", e);
            throw new ServiceRuntimeException("未知异常" + e.toString());
        }
        return result;
    }
}
