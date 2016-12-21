package com.example.administrator.shoppingapp.My;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

/**
 * Created by Administrator on 2016/11/17.
 */
public class MyFragment extends Fragment {
    private RelativeLayout rl_my_pay,rl_my_send,rl_my_deliver,rl_my_evaluate,rl_my_refung;
    private TextView tv_my_all_order,tv_user_login,tv_username;
    private MyLoginInfoDB myLoginInfoDB;
    private ImageButton ib_user_head;
    private LinearLayout ll_my_setting;
    private boolean isUserLogin=false; //是否有用户登录状态
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myLayout = inflater.inflate(R.layout.main_my, container,
                false);
        tv_user_login = (TextView) myLayout.findViewById(R.id.tv_user_login);
        tv_username = (TextView) myLayout.findViewById(R.id.tv_username);
        return myLayout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        rl_my_pay = (RelativeLayout) getActivity().findViewById(R.id.rl_my_pay);
        rl_my_send = (RelativeLayout) getActivity().findViewById(R.id.rl_my_send);
        rl_my_deliver = (RelativeLayout) getActivity().findViewById(R.id.rl_my_deliver);
        rl_my_evaluate = (RelativeLayout) getActivity().findViewById(R.id.rl_my_evaluate);
        rl_my_refung = (RelativeLayout) getActivity().findViewById(R.id.rl_my_refung);
        tv_my_all_order = (TextView) getActivity().findViewById(R.id.tv_my_all_order);
        tv_user_login = (TextView) getActivity().findViewById(R.id.tv_user_login);
        tv_username = (TextView) getActivity().findViewById(R.id.tv_username);
        ib_user_head = (ImageButton) getActivity().findViewById(R.id.ib_user_head);
        ll_my_setting = (LinearLayout) getActivity().findViewById(R.id.ll_my_setting);


        ll_my_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MySetting.class);
                startActivity(intent);
            }
        });

        /*
        检测是否有登录用户
        如果有登录用户，则显示用户名，隐藏“点击登录”tv
        若没有登录用户，则显示“点击登录”TV  隐藏用户名区域
         */
        context = getActivity();
        myLoginInfoDB = new MyLoginInfoDB(getActivity());
        CheckLogin();

        tv_my_all_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogin){
                    Toast.makeText(getActivity(),"全部订单",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),MyAllOrder.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }


            }
        });
        rl_my_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogin){
                    Toast.makeText(getActivity(),"待付款",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }

            }
        });

        rl_my_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogin){
                    Toast.makeText(getActivity(),"待发货",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }

            }
        });
        rl_my_deliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogin){
                    Toast.makeText(getActivity(),"待收货",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }
            }
        });
        rl_my_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogin){
                    Toast.makeText(getActivity(),"待评价",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }
            }
        });
        rl_my_refung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserLogin){
                    Toast.makeText(getActivity(),"退款/售后",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("------------onResume()-------------");
        myLoginInfoDB = new MyLoginInfoDB(context);
        CheckLogin();
    }

    public  class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                Refresh();
                System.out.println("收到登陆页面的消息111111111");
                System.out.println("@@@@@@@@");

            }

        }
    }

    /**
     * 刷新Activity
     */
    private void Refresh() {
        System.out.println("调用Refresh");
        onResume();
    }

    public void CheckLogin(){
        String username = myLoginInfoDB.queryUserInfo();
        System.out.println(username+"@@@@@@@@@@");
        if (username==null){//当前登录用户为空
            tv_user_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }
            });
            ib_user_head.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),MyUserLogin.class);
                    startActivity(intent);
                }
            });
        }else{//如果又登录用户，则隐藏点击登录按钮，显示用户名
            isUserLogin = true;
            tv_user_login.setVisibility(View.INVISIBLE);
            tv_username.setVisibility(View.VISIBLE);
            tv_username.setText(username);
        }
    }




}
