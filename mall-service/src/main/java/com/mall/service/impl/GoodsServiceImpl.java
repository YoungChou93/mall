package com.mall.service.impl;

import com.mall.dao.GoodsDao;
import com.mall.entity.Goods;
import com.mall.entity.GoodsView;
import com.mall.entity.User;
import com.mall.service.GoodsServcie;
import com.mall.util.DaoDataAccessException;
import com.mall.util.ServiceRuntimeException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

    private GoodsDao goodsDao;

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    @Override
    public JSONObject add(Goods goods, MultipartFile pic, String path, User user) {
        JSONObject result = new JSONObject();
        try {
            if (null != pic && pic.getOriginalFilename().length() > 0) {
                SimpleDateFormat myformat = new SimpleDateFormat("yyyyMMddHHmmss");
                String date = myformat.format(new Date()); // id前缀
                String oldFileNme = pic.getOriginalFilename();
                String newFileName = date + oldFileNme.substring(oldFileNme.lastIndexOf("."));
                String relativePath = user.getUsername() + System.getProperty("file.separator") + "picture" + System.getProperty("file.separator") + newFileName;
                File newFile = new File(path + relativePath);
                pic.transferTo(newFile);
                goods.setImagepath(path + user.getUsername() + "/picture/" + newFileName);
            }
            goods.setUid(user);
            goodsDao.insertSelective(goods);
            result.put("success", true);
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

    @Override
    public JSONArray list(User user) {
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
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常");

        }
        return jsonArray;
    }

    @Override
    public JSONArray listNoBuy(User user) {
        JSONArray jsonArray = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("nobuy", user.getId());
            List<Goods> goods = goodsDao.find(map);
            jsonArray = JSONArray.fromObject(goods);
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常");
        }
        return jsonArray;
    }

    @Override
    public Goods loadById(Integer id) {
        Goods goods = null;
        try {
            goods = goodsDao.selectByPrimaryKey(id);
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常");
        }
        return goods;
    }

    @Override
    public JSONObject update(Goods goods, MultipartFile pic, String path, User user) {
        JSONObject result = new JSONObject();
        try {
            Goods goodsold = goodsDao.selectByPrimaryKey(goods.getId());
            if (goodsold == null) {
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
                goods.setImagepath(path + user.getUsername() + "/picture/" + newFileName);
                goods.setImageurl("");
            }
            goods.setUid(user);
            goodsDao.updateByPrimaryKeySelective(goods);
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常" + e.toString());
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常" + e.toString());
        }
        return result;
    }

    @Override
    public JSONObject delete(Integer id, User user) {
        JSONObject result = new JSONObject();
        result.put("errormsg",null);
        try {
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
        } catch (DataAccessException e) {
            throw new DaoDataAccessException("数据库异常" + e.toString());
        } catch (ServiceRuntimeException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceRuntimeException("未知异常" + e.toString());
        }
        return result;
    }
}
