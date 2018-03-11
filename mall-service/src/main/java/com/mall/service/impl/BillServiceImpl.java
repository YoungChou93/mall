package com.mall.service.impl;

import com.mall.dao.BillDao;
import com.mall.dao.GoodsDao;
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
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhouyang on 2018/3/11.
 */
public class BillServiceImpl implements BillService{

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
            e.printStackTrace();
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
    public JSONObject list(User user) {
        JSONObject result = new JSONObject();
        try {
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
            e.printStackTrace();
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
