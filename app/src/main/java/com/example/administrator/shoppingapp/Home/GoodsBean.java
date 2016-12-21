package com.example.administrator.shoppingapp.Home;

/**
 * 商品信息Bean
 * Created by Administrator on 2016/11/22.
 */
public class GoodsBean {
    private int _id;
    private String name;//商品名
    private String simple;//商品简介
    private int price;//价格
    private String type1;//类型1
    private String type2;//类型2
    private String sales;//促销方式
    private int num;//库存
    private String time;//上架时间
    private String goodstext;//商品详情


    @Override
    public String toString() {
        return "GoodsBean{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", simple='" + simple + '\'' +
                ", price=" + price +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", sales='" + sales + '\'' +
                ", num=" + num +
                ", time='" + time + '\'' +
                ", goodstext='" + goodstext + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGoodstext() {
        return goodstext;
    }

    public void setGoodstext(String goodstext) {
        this.goodstext = goodstext;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }
}
