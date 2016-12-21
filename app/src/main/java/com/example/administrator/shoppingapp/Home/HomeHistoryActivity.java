package com.example.administrator.shoppingapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.shoppingapp.My.MyLoginInfoDB;
import com.example.administrator.shoppingapp.My.MyUserLogin;
import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/27.
 * 当前用户浏览历史记录
 * 负责显示当前登录用户的浏览历史
 */
public class HomeHistoryActivity extends AppCompatActivity {
    private MyLoginInfoDB myLoginInfoDB;
    private String LoginUsername;
    private HomeHistoryDB homeHistoryDB;
    private ListView lv_home_history_list;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_history_list);
        lv_home_history_list = (ListView) findViewById(R.id.lv_home_history_list);


        /**
         * 判断当前是否有登录用户：
         * 1、如果有登录用户，则获取用户名，显示该用户的浏览记录
         * 2、若没有登录用户，则提示用户未登录，跳转到登录页面
         */
        myLoginInfoDB = new MyLoginInfoDB(HomeHistoryActivity.this);
        //String username = myLoginInfoDB.queryUserInfo();
        if (checkLogin()){//如果已登录。显示用户浏览记录
            homeHistoryDB = new HomeHistoryDB(HomeHistoryActivity.this);
            System.out.println("1111111111111");
            ArrayList<HistoryBean> list = homeHistoryDB.queryHistoryListByUserName(LoginUsername);
            HomeHistoryAdapter homeHistoryAdapter = new HomeHistoryAdapter(list,HomeHistoryActivity.this);
            lv_home_history_list.setAdapter(homeHistoryAdapter);
        }else{
            /**
             * 未登录跳转到登录页面，并携带登录信息
             * from  =  history
             */
            Intent intent = new Intent(HomeHistoryActivity.this, MyUserLogin.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("from","history");






        }





    }


    /**
     * 检查当前是否有登录用户
     * @return
     */
    public boolean checkLogin(){
        System.out.println("@@@@@@");
        myLoginInfoDB = new MyLoginInfoDB(HomeHistoryActivity.this);
        String username = myLoginInfoDB.queryUserInfo();
        if (username != null) {
            LoginUsername = username;
            return true;
        }else{
            return false;
        }
    }

}
