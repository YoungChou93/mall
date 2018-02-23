package com.mall.entity;

/**
 * Created by Administrator on 2018/2/19.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/19
 */
public class GoodsView {
    private Integer id;

    private String title;

    private String imagepath;

    private String imageurl;

    private Double price;

    private String abstracts;

    private String content;

    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GoodsView{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imagepath='" + imagepath + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", price=" + price +
                ", abstracts='" + abstracts + '\'' +
                ", content='" + content + '\'' +
                ", number=" + number +
                '}';
    }
}
