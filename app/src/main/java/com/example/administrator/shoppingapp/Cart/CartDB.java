package com.example.administrator.shoppingapp.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.shoppingapp.Home.GoodsBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/24.
 */
public class CartDB extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "cartinfo";
    private SQLiteDatabase db;
    private static final String DB_NAME = "cartinfo.db";
    private static final int DATABASE_VERSION = 1;

    public CartDB(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement," +
                "name text,price integer," +
                "num integer,username text" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 根据用户名查询购物车列表
     * @param username
     */
    public ArrayList<CartGoodsBean> queryCartByUserName(String username){
        ArrayList<CartGoodsBean> list = new ArrayList<CartGoodsBean>();
        String sql="select * from "+TABLE_NAME+" where username = ?";
        Cursor cursor=db.rawQuery(sql, new String[]{username});
        while(cursor.moveToNext()){
            CartGoodsBean cartGoodsBean = new CartGoodsBean();
            cartGoodsBean.setName(cursor.getString(1));
            cartGoodsBean.setPrice(cursor.getInt(2));
            cartGoodsBean.setBuynum(cursor.getInt(3));
            cartGoodsBean.setUsername(cursor.getString(4));
            list.add(cartGoodsBean);
            System.out.println(cartGoodsBean.toString());
        }
        return list;
    }

    /**
     * 添加到购物车操作
     * @param cartGoodsBean
     */
    public void addCart(CartGoodsBean cartGoodsBean){
        db.execSQL("insert into "+TABLE_NAME+" values(null,'"+cartGoodsBean.getName()+"','"+cartGoodsBean.getPrice()+"','"
               +cartGoodsBean.getBuynum()+"','"+cartGoodsBean.getUsername()+"'"+
                ")");
        System.out.println("插入购物车信息成功！！");
    }


    public void updateBuyNumByGoodNameName(String name,String username,int buynum) {
        //db.execSQL("update"+TABLE_NAME+"set ");
        ContentValues values = new ContentValues();
        values.put("num",buynum);
        int a = db.update(TABLE_NAME,values,"name=? and username=?",new String[]{name,username});
        if (a>0){
            System.out.println("更新成功");
        }

    }





}
