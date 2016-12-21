package com.example.administrator.shoppingapp.Home;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/27.
 */
public class HomeHistoryDB extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "historyrecord";
    private SQLiteDatabase db;
    private static final String DB_NAME = "historyrecord.db";
    private static final int DATABASE_VERSION = 1;

    public HomeHistoryDB(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement," +
                "name text,price integer," +
                "sales text,time,username text" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入浏览历史记录操作
     * @param historyBean
     */
    public void insertHistory(HistoryBean historyBean){
        String sql = "insert into "+TABLE_NAME+" values(null,'"+historyBean.getName()+"','"+historyBean.getPrice()+"'" +
                ",'"+historyBean.getSales()+"','"+historyBean.getTime()+"','"+historyBean.getUsername()+"')";
        db.execSQL(sql);
        System.out.println("插入商品信息成功！！");
    }

    /**
     * 根据用户名查询相应的浏览历史列表
     * @param username
     */
    public ArrayList<HistoryBean> queryHistoryListByUserName(String username){
        ArrayList<HistoryBean> list = new ArrayList<HistoryBean>();
        String sql="select * from "+TABLE_NAME+" where username = ?";
        Cursor cursor=db.rawQuery(sql, new String[]{username});
        while (cursor.moveToNext()){
            HistoryBean historyBean = new HistoryBean();
            historyBean.set_id(cursor.getInt(0));
            historyBean.setName(cursor.getString(1));
            historyBean.setPrice(cursor.getInt(2));
            historyBean.setSales(cursor.getString(3));
            historyBean.setTime(cursor.getString(4));
            historyBean.setUsername(cursor.getString(5));
            list.add(historyBean);
            System.out.println(historyBean.toString());
        }
        return list;
    }

    /**
     * 根据用户名及商品名删除历史浏览记录
     * @param username
     * @param name
     */
    public void deleteHistoryByUsernameName(String username,String name){




    }






}
