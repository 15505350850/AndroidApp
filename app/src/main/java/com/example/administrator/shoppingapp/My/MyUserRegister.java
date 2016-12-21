package com.example.administrator.shoppingapp.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.shoppingapp.R;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/11/19.
 */
public class MyUserRegister extends AppCompatActivity{
    private MyUserInfoDB myUserInfoDB;
    private Button bt_my_user_register;
    private EditText et_my_user_register_username,et_my_user_register_psw1,et_my_user_register_psw2,et_my_user_register_email,et_my_user_register_tel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_user_register);
        bt_my_user_register = (Button) findViewById(R.id.bt_my_user_register);
        et_my_user_register_username = (EditText) findViewById(R.id.et_my_user_register_username);
        et_my_user_register_psw1 = (EditText) findViewById(R.id.et_my_user_register_psw1);
        et_my_user_register_psw2 = (EditText) findViewById(R.id.et_my_user_register_psw2);
        et_my_user_register_email = (EditText) findViewById(R.id.et_my_user_register_email);
        et_my_user_register_tel = (EditText) findViewById(R.id.et_my_user_register_tel);

        bt_my_user_register.setOnClickListener(new View.OnClickListener() {//点击注册按钮时的事件，执行注册操作
            @Override
            public void onClick(View v) {
                /*
                验证各字段格式是否符合要求
                 */
                if (et_my_user_register_username.getText().toString().length()==0){//验证用户名
                    Toast.makeText(MyUserRegister.this,"用户名不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!regUsername(et_my_user_register_username.getText().toString())){
                    Toast.makeText(MyUserRegister.this,"用户名已存在，请重新输入！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!regPsw(et_my_user_register_psw1.getText().toString(),et_my_user_register_psw2.getText().toString())){
                    Toast.makeText(MyUserRegister.this,"密码格式错误，请重新输入！",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!regEmail(et_my_user_register_email.getText().toString())){
                    Toast.makeText(MyUserRegister.this,"邮箱格式错误，请重新输入！",Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (!regTel(et_my_user_register_tel.getText().toString())){
//                    Toast.makeText(MyUserRegister.this,"手机号格式错误，请重新输入！",Toast.LENGTH_SHORT).show();
//                    return;
//                }

                myUserInfoDB = new MyUserInfoDB(MyUserRegister.this);
                myUserInfoDB.insertUserInfo(et_my_user_register_username.getText().toString(),et_my_user_register_psw1.getText().toString(),et_my_user_register_email.getText().toString(),et_my_user_register_tel.getText().toString());
                Toast.makeText(MyUserRegister.this,"注册成功！",Toast.LENGTH_SHORT).show();
                finish();

            }
        });


    }

    /**
     * 验证用户名是否已经存在
     * @param username
     * @return
     */
    public boolean regUsername(String username){
        myUserInfoDB = new MyUserInfoDB(MyUserRegister.this);
        int a = myUserInfoDB.queryUserByUsername(username);
        if (a==0){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 验证密码是否相同
     * @param psw1
     * @param psw2
     * @return
     */
    public static boolean regPsw(String psw1,String psw2){
        if (psw1.length()==0||psw2.length()==0){
            return  false;
        }
        if (psw1.equals(psw2)){
            return true;
        }else{
            return false;
        }
    }


    /**
     * 验证邮箱格式
     * @param email
     * @return
     */
    public static boolean regEmail(String email) {
        if (email.length()==0){
            return false;
        }
        String regex = "^[\\w-]+(\\.[\\w-]+)*\\@([\\.\\w-]+)+$";
        boolean flg = Pattern.matches(regex, email);
        System.out.println(flg);
        return flg;
    }

    /**
     * 电话号码格式验证
     * @param tel
     * @return
     */
    public  static boolean regTel(String tel){
        if (tel.length()==0){
            return false;
        }
        String regex = "^[1][358][0-9]{9}$`";
        boolean flg = Pattern.matches(regex, tel);
        System.out.println(flg);
        return flg;
    }
}
