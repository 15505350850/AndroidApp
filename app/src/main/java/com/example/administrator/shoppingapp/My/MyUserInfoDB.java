package com.example.administrator.shoppingapp.My;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/11/19.
 */
public class MyUserInfoDB extends SQLiteOpenHelper{
    private static final String TABLE_NAME = "userinfo";
    private SQLiteDatabase db;
    private static final String DB_NAME = "userinfo.db";
    private static final int DATABASE_VERSION = 1;

    public MyUserInfoDB(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement," +
                "username text,psw text,email text,tel text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入用户信息，用于用户注册操作
     * @param username
     * @param psw
     * @param email
     * @param tel
     */
    public void insertUserInfo(String username,String psw,String email,String tel){
        db.execSQL("insert into "+TABLE_NAME+" values(null,'"+username+"','"+psw+"','"+email+"','"+tel+"')");
        System.out.println("插入用户信息成功！！");
    }

    /**
     * 根据用户名密码查询用户，用于用户登录操作
     * @param username
     * @param psw
     * @return
     */
    public int queryUser(String username,String psw){
        int a=0;
        String sql="select * from "+TABLE_NAME+" where username=? and psw=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username,psw});
        if(cursor.moveToFirst()==true){
            cursor.close();
            a=1;
        }
        System.out.println(a);
        return a;
    }

    /**
     *修改用户信息操作
     * @param username
     * @param psw
     * @param email
     * @param tel
     * @param oldusername
     */
    public void updateUserInfo(String username,String psw,String email,String tel,String oldusername){


    }

    /**
     * 根据用户名查询数据，判断用户名是否已经存在
     * @param username
     * @return
     */
    public int queryUserByUsername(String username){
        int a=0;
        String sql="select * from "+TABLE_NAME+" where username=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username});
        if(cursor.moveToFirst()==true){
            cursor.close();
            a=1;
        }
        System.out.println(a);
        return a;

    }



}
