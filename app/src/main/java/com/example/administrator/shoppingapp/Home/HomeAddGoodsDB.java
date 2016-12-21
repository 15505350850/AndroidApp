package com.example.administrator.shoppingapp.Home;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */
public class HomeAddGoodsDB extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "goodsinfo";
    private SQLiteDatabase db;
    private static final String DB_NAME = "goodsinfo.db";
    private static final int DATABASE_VERSION = 1;

    public HomeAddGoodsDB(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement," +
                "name text,simple text,price integer," +
                "type1 text,type2 text,sales text,num integer,time,goodstext text" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入商品信息方法，将商品信息插入到数据库当中
     * @param goodsBean
     */
    public void insertGoods(GoodsBean goodsBean){
        db.execSQL("insert into "+TABLE_NAME+" values(null,'"+goodsBean.getName()+"','"+goodsBean.getSimple()+"','"+goodsBean.getPrice()+"'," +
                "'"+goodsBean.getType1()+"','" +goodsBean.getType2()+"','" +goodsBean.getSales()+"','"+goodsBean.getNum()+"','"+goodsBean.getTime()+"','"+goodsBean.getGoodstext()+"'"+
                ")");
        System.out.println("插入商品信息成功！！");
    }

    /**
     * 根据关键字查询商品列表
     * @param word  关键字
     */
    public ArrayList<GoodsBean> queryGoodsListByWord(String word){
        String sql="select * from "+TABLE_NAME+" where name like ?";
        Cursor cursor=db.rawQuery(sql, new String[]{"%"+word+"%"});
        //Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<GoodsBean> itemArrayList = new ArrayList<GoodsBean>();
        while(cursor.moveToNext()){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.set_id(cursor.getInt(0));
            goodsBean.setName(cursor.getString(1));
            goodsBean.setSimple(cursor.getString(2));
            goodsBean.setPrice(cursor.getInt(3));
            goodsBean.setType1(cursor.getString(4));
            goodsBean.setType2(cursor.getString(5));
            goodsBean.setSales(cursor.getString(6));
            goodsBean.setNum(cursor.getInt(7));
            goodsBean.setTime(cursor.getString(8));
            goodsBean.setGoodstext(cursor.getString(9));
            System.out.println(goodsBean.toString());
            itemArrayList.add(goodsBean);
        }
        return itemArrayList;
    }


    /**
     * 根据商品类型查询列表  参数（男装，衬衣）
     * @param type1  类型1
     * @param type2  类型2
     */
    public ArrayList<GoodsBean> queryGoodsListByType(String type1,String type2){
        String sql="select * from "+TABLE_NAME+" where type1 = ? and type2 = ?";
        Cursor cursor=db.rawQuery(sql, new String[]{type1,type2});
        //Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<GoodsBean> itemArrayList = new ArrayList<GoodsBean>();
        while(cursor.moveToNext()){
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.set_id(cursor.getInt(0));
            goodsBean.setName(cursor.getString(1));
            goodsBean.setSimple(cursor.getString(2));
            goodsBean.setPrice(cursor.getInt(3));
            goodsBean.setType1(cursor.getString(4));
            goodsBean.setType2(cursor.getString(5));
            goodsBean.setSales(cursor.getString(6));
            goodsBean.setNum(cursor.getInt(7));
            goodsBean.setTime(cursor.getString(8));
            goodsBean.setGoodstext(cursor.getString(9));
            System.out.println(goodsBean.toString());
            itemArrayList.add(goodsBean);
        }
        return itemArrayList;

    }

    /**
     * 根据商品名查询商品信息
     * @param name
     * @return
     */
    public GoodsBean queryGoodsByName(String name){
        GoodsBean goodsBean = new GoodsBean();
        String sql="select * from "+TABLE_NAME+" where name = ?";
        Cursor cursor=db.rawQuery(sql, new String[]{name});
        while(cursor.moveToNext()){
            goodsBean.set_id(cursor.getInt(0));
            goodsBean.setName(cursor.getString(1));
            goodsBean.setSimple(cursor.getString(2));
            goodsBean.setPrice(cursor.getInt(3));
            goodsBean.setType1(cursor.getString(4));
            goodsBean.setType2(cursor.getString(5));
            goodsBean.setSales(cursor.getString(6));
            goodsBean.setNum(cursor.getInt(7));
            goodsBean.setTime(cursor.getString(8));
            goodsBean.setGoodstext(cursor.getString(9));
            System.out.println(goodsBean.toString());
        }
        return goodsBean;
    }







}
