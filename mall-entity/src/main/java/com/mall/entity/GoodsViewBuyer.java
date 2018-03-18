package com.mall.entity;

/**
 * Created by Administrator on 2018/2/19.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/19
 */
public class GoodsViewBuyer extends Goods{

    private Double buyprice;

    public Double getBuyprice() {
        return buyprice;
    }

    public void setBuyprice(Double buyprice) {
        this.buyprice = buyprice;
    }

    @Override
    public String toString() {
        return super.toString()+"GoodsViewBuyer{" +
                "buyprice=" + buyprice +
                '}';
    }
}
