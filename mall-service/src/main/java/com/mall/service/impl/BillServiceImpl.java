package com.mall.service.impl;

import com.mall.dao.BillDao;
import com.mall.dao.ShoppingCartDao;
import com.mall.entity.Bill;
import com.mall.entity.BillView;
import com.mall.entity.ShoppingCart;
import com.mall.entity.User;
import com.mall.service.BillService;
import com.mall.util.DaoDataAccessException;
import com.mall.util.DateJsonValueProcessor;
import com.mall.util.ServiceRuntimeException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhouyang on 2018/3/11.
 */
public class BillServiceImpl implements BillService{

    private static final Logger logger = Logger.getLogger("com.mall");

    private BillDao billDao;

    private ShoppingCartDao shoppingCartDao;

    public void setBillDao(BillDao billDao) {
        this.billDao = billDao;
    }

    public void setShoppingCartDao(ShoppingCartDao shoppingCartDao) {
        this.shoppingCartDao = shoppingCartDao;
    }

    @Override
    public JSONObject add(String sids,String numbers,User user) {
        JSONObject result = new JSONObject();
        try {
            if(null==sids || ""==sids || null==numbers || ""==numbers || null==user){
                throw new ServiceRuntimeException("参数不能为空");
            }
            String[] sidArray=new String[1];
            String[] numberArray=new String[1];
            if(sids.contains(",")) {
                sidArray = sids.split(",");
                numberArray = numbers.split(",");
            }else{
                sidArray[0]=sids;
                numberArray[0]=numbers;
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uid",user.getId());
            Integer sid=null;
            ShoppingCart shoppingCart=null;
            for(int i=0;i<sidArray.length;i++){
                sid=Integer.valueOf(sidArray[i]);
                map.put("id",sid);
                shoppingCart=shoppingCartDao.selectByMap(map);
                Bill bill=new Bill();
                bill.setGid(shoppingCart.getGid());
                bill.setUid(shoppingCart.getUid());
                bill.setNumber(Integer.valueOf(numberArray[i]));
                bill.setBuytime(new Date());
                bill.setPrice(shoppingCart.getGid().getPrice());
                billDao.insertSelective(bill);
            }
            Map<String,Object> deleteMap = new HashMap<String,Object>();
            deleteMap.put("uid",user.getId());
            shoppingCartDao.deleteByMap(map);
            result.put("success",true);
        } catch (DataAccessException e) {
            logger.error("BillServiceImpl.add"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("BillServiceImpl.add"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("BillServiceImpl.add", e);
            throw new ServiceRuntimeException("未知异常");
        }
        return result;
    }

    @Override
    public JSONObject load(User user) {
        JSONObject result = new JSONObject();
        try {
            if(null==user){
                throw new ServiceRuntimeException("未登录");
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uid",user.getId());
            List<BillView> billViewList=billDao.find(map);
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(java.util.Date.class,
                    new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
            JSONArray jsonArray=JSONArray.fromObject(billViewList,jsonConfig);
            Double total=billDao.getTotal(map);
            result.put("success",true);
            result.put("total",total);
            result.put("data",jsonArray);
        } catch (DataAccessException e) {
            logger.error("BillServiceImpl.load"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("BillServiceImpl.load"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("BillServiceImpl.load", e);
            throw new ServiceRuntimeException("未知异常");

        }
        return result;
    }

    @Override
    public Double getNewestBuyPrice(Integer gid, User user) {
        Double price=null;
        try {
            if(null==user || null==gid){
                throw new ServiceRuntimeException("参数不能为空");
            }
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("uid",user.getId());
            map.put("gid",gid);
            price=billDao.getNewestBuy(map);
        } catch (DataAccessException e) {
            logger.error("BillServiceImpl.getNewestBuyPrice"+"数据库异常", e);
            throw new DaoDataAccessException("数据库异常");
        } catch (ServiceRuntimeException e) {
            logger.error("BillServiceImpl.getNewestBuyPrice"+"运行时异常", e);
            throw e;
        } catch (Exception e) {
            logger.error("BillServiceImpl.getNewestBuyPrice", e);
            throw new ServiceRuntimeException("未知异常");

        }
        return price;
    }
}
