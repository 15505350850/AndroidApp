package com.example.administrator.shoppingapp.Home;

/**
 * Created by Administrator on 2016/11/27.
 */
public class HistoryBean {
    private int _id;

    private String name;
    private int price;
    private String sales;

    private String time;
    private String username;

    @Override
    public String toString() {
        return "HistoryBean{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", time='" + time + '\'' +
                ", username='" + username + '\'' +
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
