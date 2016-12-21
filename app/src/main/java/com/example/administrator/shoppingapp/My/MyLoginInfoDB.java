package com.example.administrator.shoppingapp.My;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/19.
 */
public class MyLoginInfoDB extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "userlogininfo";
    private SQLiteDatabase db;
    private static final String DB_NAME = "userlogininfo.db";
    private static final int DATABASE_VERSION = 1;

    public MyLoginInfoDB(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(username text ," +
                "userpsw text,userhead text"  +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入用户的登录信息
     * 当APP处于未登录状态时调用此方法
     * @param username
     * @param userpsw
     * @param userhead
     */
    public void insertUserLoginInfo(String username,String userpsw,String userhead){
        db.execSQL("insert into "+TABLE_NAME+" values('"+username+"','"+userpsw+"','"+userhead+"')");
        System.out.println("插入登录信息成功！！");

    }

    /**
     * 更新用户的登录信息
     * 当APP登录的用户要切换其他用户时调用此方法
     * @param username
     * @param userpsw
     * @param userhead
     * @return
     */
    public int updateUserLoginInfo(String username,String userpsw,String userhead){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("userpsw",userpsw);
        cv.put("userhead",userhead);
        int result = db.update(TABLE_NAME,cv,"",null);
        if (result>0){
            System.out.println("更新成功！");
        }
        return result;
    }

    /**
     * 删除用户登录信息
     * 当用户点击退出登录操作时调用此方法
     */
    public void deleteUserLoginInfo(){
        int a = db.delete(TABLE_NAME,null,null);
        if (a>0){
            System.out.println("删除成功！");
        }
    }

    /**
     * 查询用户登录信息状态，返回用户名
     * @return 用户名
     */
    public String queryUserInfo(){
        String username = null;
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        while (cursor.moveToNext()){
            //username = cursor.getColumnName(0);
            username = cursor.getString(0);
        }
        System.out.println("已登录用户："+username);
        return username;
    }


}
