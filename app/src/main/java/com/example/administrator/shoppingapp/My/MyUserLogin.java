package com.example.administrator.shoppingapp.My;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.MainActivity;
import com.example.administrator.shoppingapp.R;

/**
 * Created by Administrator on 2016/11/19.
 */
public class MyUserLogin extends AppCompatActivity {
    private MyUserInfoDB myUserInfoDB;
    private MyLoginInfoDB myLoginInfoDB;
    private TextView tv_my_user_login_back,tv_my_user_go_register;
    private EditText et_my_user_login_username,et_my_user_login_psw;
    private Button bt_my_user_login_gologin;
    //private MyFragment myFragment;
    private MainActivity mainActivity=null;
    private String FromLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_user_login);
        Intent intent = getIntent();
        FromLogin = intent.getSerializableExtra("from").toString();



        tv_my_user_login_back = (TextView) findViewById(R.id.tv_my_user_login_back);
        tv_my_user_go_register = (TextView) findViewById(R.id.tv_my_user_go_register);

        et_my_user_login_username = (EditText) findViewById(R.id.et_my_user_login_username);
        et_my_user_login_psw = (EditText) findViewById(R.id.et_my_user_login_psw);

        bt_my_user_login_gologin = (Button) findViewById(R.id.bt_my_user_login_gologin);

        bt_my_user_login_gologin.setOnClickListener(new View.OnClickListener() {    //登录按钮点击事件
            @Override
            public void onClick(View v) {
                myUserInfoDB = new MyUserInfoDB(MyUserLogin.this);
                if (!regUserPsw(et_my_user_login_username.getText().toString(),et_my_user_login_psw.getText().toString())){
                    Toast.makeText(MyUserLogin.this,"用户名或密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                int  loginok = myUserInfoDB.queryUser(et_my_user_login_username.getText().toString(),et_my_user_login_psw.getText().toString());
                if (loginok>0){
                    myLoginInfoDB = new MyLoginInfoDB(MyUserLogin.this);
                    myLoginInfoDB.insertUserLoginInfo(et_my_user_login_username.getText().toString(),et_my_user_login_psw.getText().toString(),"");
                    if (FromLogin.equals("goodsDetail")){
                        /**
                         * 如果来自商品详情界面的登录，则执行直接关闭当前activity
                         */
                    finish();
                    }
                    else{
                        Intent intent = new Intent(MyUserLogin.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(MyUserLogin.this,"登陆成功！",Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }else{
                    Toast.makeText(MyUserLogin.this,"用户名或密码错误，请重新登陆！",Toast.LENGTH_SHORT).show();
                }
            }
        });


        tv_my_user_go_register.setOnClickListener(new View.OnClickListener() {//注册按钮点击事件
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyUserLogin.this,MyUserRegister.class);
                startActivity(intent);

            }
        });

        tv_my_user_login_back.setOnClickListener(new View.OnClickListener() {//返回按钮点击事件
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public boolean regUserPsw(String username,String psw){
        if (username.length()==0||psw.length()==0){
            return false;
        }
        return true;
    }


}
