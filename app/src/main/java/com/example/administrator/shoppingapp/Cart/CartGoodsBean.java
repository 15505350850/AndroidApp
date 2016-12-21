package com.example.administrator.shoppingapp.Cart;

/**
 * Created by Administrator on 2016/11/18.
 */
public class CartGoodsBean {
    private String name;
    private int price;
    private int buynum;

    private String username;

    @Override
    public String toString() {
        return "CartGoodsBean{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", buynum=" + buynum +
                ", username='" + username + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
