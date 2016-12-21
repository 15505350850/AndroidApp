package com.example.administrator.shoppingapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.Cart.CartFragment;
import com.example.administrator.shoppingapp.Home.HomeFragment;
import com.example.administrator.shoppingapp.My.MyFragment;
import com.example.administrator.shoppingapp.Repair.RepairFragment;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Button bt_main_home,bt_main_repair,bt_main_cart,bt_main_my;
    private TextView tv_main_home_text,tv_main_repair_text,tv_main_cart_text,tv_main_my_text;
    private RelativeLayout rl_main_home,rl_main_repair,rl_main_cart,rl_main_my;
    private CartFragment cartFragment;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private RepairFragment repairFragment;
    private FragmentManager fragmentManager;
    private String From = "";
    private long mExitTime;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 1000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if (intent!=null) {
            From = intent.getSerializableExtra("from").toString();
        }
        /**
         * 如果来自刷新购物车界面的刷新Activity请求，则进入购物车界面
         */
        if (From.equals("cart")){
            // 初始化布局元素
            initViews();
            fragmentManager = getFragmentManager();
            // 第一次启动时选中第0个tab
            setTabSelection(2);

        }else if (From.equals("userlogin")){
            // 初始化布局元素
            initViews();
            fragmentManager = getFragmentManager();
            // 第一次启动时选中第0个tab
            setTabSelection(3);
        }else{
            // 初始化布局元素
            initViews();
            fragmentManager = getFragmentManager();
            // 第一次启动时选中第0个tab
            setTabSelection(0);
        }
    }


    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */
    private void initViews() {
        rl_main_home = (RelativeLayout) findViewById(R.id.rl_main_home);
        rl_main_repair = (RelativeLayout) findViewById(R.id.rl_main_repair);
        rl_main_cart = (RelativeLayout) findViewById(R.id.rl_main_cart);
        rl_main_my = (RelativeLayout) findViewById(R.id.rl_main_my);
        tv_main_home_text = (TextView) findViewById(R.id.tv_main_home_text);
        tv_main_repair_text = (TextView) findViewById(R.id.tv_main_repair_text);
        tv_main_cart_text = (TextView) findViewById(R.id.tv_main_cart_text);
        tv_main_my_text = (TextView) findViewById(R.id.tv_main_my_text);
        bt_main_home = (Button) findViewById(R.id.bt_main_home);
        bt_main_repair = (Button) findViewById(R.id.bt_main_repair);
        bt_main_cart = (Button) findViewById(R.id.bt_main_cart);
        bt_main_my = (Button) findViewById(R.id.bt_main_my);

        rl_main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
            }
        });
        rl_main_repair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
            }
        });
        rl_main_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
            }
        });
        rl_main_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当点击了设置tab时，选中第4个tab
                setTabSelection(3);
            }
        });
    }
    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    public void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了主页tab时，改变控件的图片和文字颜色
                Bitmap bitmap=readBitMap(MainActivity.this,R.drawable.home_fill);
                bt_main_home.setBackgroundDrawable(new BitmapDrawable(bitmap));

                //bt_main_home.setBackgroundResource(R.drawable.home_fill);
                tv_main_home_text.setTextColor(Color.parseColor("#FF4200"));
                //if (homeFragment == null) {
                    // 如果HomeFragment为空，则创建一个并添加到界面上
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.content, homeFragment);
                //} else {
                    // 如果HomeFragment不为空，则直接将它显示出来
                    //transaction.show(homeFragment);
                //}
                break;
            case 1:
                // 当点击了工具tab时，改变控件的图片和文字颜色
                Bitmap bitmap2=readBitMap(MainActivity.this,R.drawable.repair_fill);
                bt_main_repair.setBackgroundDrawable(new BitmapDrawable(bitmap2));
                //bt_main_repair.setBackgroundResource(R.drawable.repair_fill);
                tv_main_repair_text.setTextColor(Color.parseColor("#FF4200"));
                //if (repairFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    repairFragment = new RepairFragment();
                    transaction.add(R.id.content, repairFragment);
                //} else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    //transaction.show(repairFragment);
                //}
                break;
            case 2:
                Bitmap bitmap3=readBitMap(MainActivity.this,R.drawable.cart_fill);
                bt_main_cart.setBackgroundDrawable(new BitmapDrawable(bitmap3));
                tv_main_cart_text.setTextColor(Color.parseColor("#FF4200"));
                //cartFragment = new CartFragment();
                //transaction.replace(R.id.content,cartFragment);
                //if (cartFragment == null) {
                    cartFragment = new CartFragment();
                    transaction.add(R.id.content, cartFragment);
                //} else {
                    //transaction.replace(R.id.content,cartFragment);
                //}
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
                Bitmap bitmap4=readBitMap(MainActivity.this,R.drawable.my_fill);
                bt_main_my.setBackgroundDrawable(new BitmapDrawable(bitmap4));
                //bt_main_my.setBackgroundResource(R.drawable.my_fill);
                tv_main_my_text.setTextColor(Color.parseColor("#FF4200"));
                //if (myFragment == null) {
                    // 如果SettingFragment为空，则创建一个并添加到界面上
                    myFragment = new MyFragment();
                    transaction.add(R.id.content, myFragment);
                //} else {
                    // 如果SettingFragment不为空，则直接将它显示出来
                    //transaction.show(myFragment);
                //}
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        bt_main_home.setBackgroundResource(R.drawable.home);
        tv_main_home_text.setTextColor(Color.parseColor("#2c2c2c"));
        bt_main_repair.setBackgroundResource(R.drawable.repair);
        tv_main_repair_text.setTextColor(Color.parseColor("#2c2c2c"));
        bt_main_cart.setBackgroundResource(R.drawable.cart);
        tv_main_cart_text.setTextColor(Color.parseColor("#2c2c2c"));
        bt_main_my.setBackgroundResource(R.drawable.my);
        tv_main_my_text.setTextColor(Color.parseColor("#2c2c2c"));

    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     *            用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        //transaction.remove(homeFragment);
        if (homeFragment != null) {
            transaction.remove(homeFragment);
        }
        if (repairFragment != null) {
            transaction.remove(repairFragment);
            //transaction.hide(repairFragment);
        }
        if (cartFragment != null) {

            transaction.remove(cartFragment);
        }
        if (myFragment != null) {
            transaction.remove(myFragment);
        }
        //transaction.commit();
    }


    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
// 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }


    public  class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1){
                System.out.println("收到购物车页面的消息111111111");
                System.out.println("@@@@@@@@");
                Bundle data = new Bundle();
                onCreate(data);
                setTabSelection(2);
            }


        }
    }


}
