package com.mall.entity;

/**
 * Created by Administrator on 2018/2/14.
 * Author : ZhouYang
 * Email : zhouyang_young@163.com
 * Date : 2018/2/14
 */
public class GoodsPart {

    private Integer id;

    private String title;

    private String imageurl;

    private Long price;

    private String abstracts;

    private String content;

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

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
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

    public GoodsPart() {
    }

    public GoodsPart(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GoodsPart{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", price=" + price +
                ", abstracts='" + abstracts + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
