package com.mall.entity;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public class UserPart {

    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserPart() {
    }

    public UserPart(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserPart{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
