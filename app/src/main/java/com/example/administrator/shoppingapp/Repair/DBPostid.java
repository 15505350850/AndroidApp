package com.example.administrator.shoppingapp.Repair;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/28.
 */
public class DBPostid extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "postid";
    private SQLiteDatabase db;
    private static final String DB_NAME = "postid.db";
    private static final int DATABASE_VERSION = 1;


    public DBPostid(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public DBPostid(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DBPostid(Context context) {
        super(context, TABLE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"(_id integer primary key autoincrement,postid text not null,express text not null"  +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String postid,String express){
        db.execSQL("insert into "+TABLE_NAME+" values(null,'"+postid+"','"+express+"')");
    }

    public Cursor select(){
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public ArrayList<HList> SelectList(){
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME,null);
        ArrayList<HList> itemArrayList = new ArrayList<>();
        while(cursor.moveToNext()){
            HList hList = new HList();
            hList.setH_postid(cursor.getString(1));
            hList.setH_express(cursor.getString(2));
            //System.out.println(hList.getH_postid()+"+++"+hList.getH_express());
            itemArrayList.add(hList);
            //System.out.println("sql:"+cursor.getString(1));
            //System.out.println("sql2:"+cursor.getString(2));
        }


        return itemArrayList;
    }

    public int QueryListNum(){
        int num = 0;
        Cursor cursor = db.rawQuery("select count(*)from "+TABLE_NAME,null);
        //游标移到第一条记录准备获取数据
        cursor.moveToFirst();
        // 获取数据中的LONG类型数据
        Long count = cursor.getLong(0);
        return new Long(count).intValue();
    }
    public int QueryUsername(String postid){
        int a=0;
        String sql="select * from "+TABLE_NAME+" where postid=?";
        Cursor cursor=db.rawQuery(sql, new String[]{postid});
        if(cursor.moveToFirst()==true){
            cursor.close();
            a=1;
        }
        System.out.println(a);
        return a;
    }

    public int DeletePostidByID(String postid){
        int a=0;
        a = db.delete(TABLE_NAME,"postid = ?",new String[]{postid});
        return a;
    }


}
