package com.mall.entity;

public class Bill {
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Goods getGid() {
        return gid;
    }

    public void setGid(Goods gid) {
        this.gid = gid;
    }

    public User getUid() {
        return uid;
    }

    public void setUid(User uid) {
        this.uid = uid;
    }

    public Bill() {
    }

    public Bill(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", gid=" + gid +
                ", uid=" + uid +
                ", number=" + number +
                '}';
    }
}