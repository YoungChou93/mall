package com.mall.entity;

public class ShoppingCart {
    private Integer id;

    private Goods gid;

    private User uid;

    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Goods getGid() {
        return gid;
    }

    public void setGid(Goods gid) {
        this.gid = gid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ShoppingCart() {
    }

    public ShoppingCart(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", gid=" + gid +
                ", uid=" + uid +
                ", number=" + number +
                '}';
    }
}