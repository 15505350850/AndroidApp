package com.example.administrator.shoppingapp.My;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.MainActivity;
import com.example.administrator.shoppingapp.R;

/**
 * Created by Administrator on 2016/11/20.
 */
public class MySetting extends AppCompatActivity {
    private TextView tv_my_set_logout,tv_my_set_back;
    private RelativeLayout rl_my_set_myinfo,rl_my_set_repsw;
    private MyLoginInfoDB myLoginInfoDB;
    private String username;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_setting);
        tv_my_set_back = (TextView) findViewById(R.id.tv_my_set_back);
        tv_my_set_logout = (TextView) findViewById(R.id.tv_my_set_logout);

        rl_my_set_myinfo = (RelativeLayout) findViewById(R.id.rl_my_set_myinfo);
        rl_my_set_repsw = (RelativeLayout) findViewById(R.id.rl_my_set_repsw);

        /**
         * 设置界面返回按钮点击监听事件
         */
        tv_my_set_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 设置界面退出登录按钮点击监听事件
         */
        tv_my_set_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*MyFragment myFragment = new MyFragment();
                MyFragment.MyHandler myHandler = myFragment.new MyHandler();
                myHandler.sendEmptyMessage(1);*/
                Intent intent = new Intent(MySetting.this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(MySetting.this,"您已退出登录！",Toast.LENGTH_SHORT).show();
                myLoginInfoDB = new MyLoginInfoDB(MySetting.this);
                myLoginInfoDB.deleteUserLoginInfo();
                finish();
            }
        });

        /**
         * 点击修改密码
         */
        rl_my_set_repsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * 点击查看个人信息
         */
        rl_my_set_myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
