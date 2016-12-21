package com.example.administrator.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/16.
 */
public class WelcomeActivity extends AppCompatActivity {
    private int count = 1;//倒计时的时间设置
    SharedPreferences preferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,//去除欢迎页标题栏(顶部状态栏)，实现全屏特效.必须写到setContentView()前面
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome);

        preferences = getSharedPreferences("peizhixinxi",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (preferences.getInt("first",0)==1){//如果不是第一次启动
            handler.sendEmptyMessageDelayed(0, 1000);
        }else{
            editor.putInt("first",1);
            editor.commit();
            System.out.println(preferences.getInt("first",0)+"");
            Intent intent = new Intent(WelcomeActivity.this,WelcomeFirstActivity.class);
            startActivity(intent);
            finish();
        }


    }


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                getCount();
                handler.sendEmptyMessageDelayed(0, 1000);
            }

        };

    };
    private int getCount() {
        count--;
        if (count == 0) {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle data = new Bundle();
            data.putSerializable("from","welcome");
            intent.putExtras(data);
            startActivity(intent);
            finish();
        }
        return count;
    }
}
