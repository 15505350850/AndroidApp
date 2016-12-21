package com.example.administrator.shoppingapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.Cart.CartDB;
import com.example.administrator.shoppingapp.Cart.CartGoodsBean;
import com.example.administrator.shoppingapp.My.MyLoginInfoDB;
import com.example.administrator.shoppingapp.My.MyUserLogin;
import com.example.administrator.shoppingapp.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/23.
 */
public class HomeGoodsDetail extends AppCompatActivity {
    private TextView tv_goods_details_name, tv_goods_details_add, tv_goods_details_move, tv_goods_details_currentnum, tv_goods_details_num;
    private TextView tv_goods_details_sumprice, tv_goods_details_price, tv_goods_details_sales, tv_goods_details_simple;
    private TextView tv_goods_details_addcart, tv_goods_details_buy;
    private TextView tv_goods_details_time, tv_goods_details_type, tv_goods_details_goodstext;
    private Button bt_home_goods_details_back;
    private HomeAddGoodsDB homeAddGoodsDB;
    private MyLoginInfoDB myLoginInfoDB;
    private CartDB cartDB;
    private String LoginUsername;
    private HomeHistoryDB homeHistoryDB;


    /**
     * 当前数量
     */
    private int CurrentNum;

    /**
     * 库存
     */
    private int num;
    /**
     * 商品价格
     */
    private int price;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_goods_details);
        tv_goods_details_name = (TextView) findViewById(R.id.tv_goods_details_name);//商品名
        tv_goods_details_currentnum = (TextView) findViewById(R.id.tv_goods_details_currentnum);//购买数量--当前数量
        tv_goods_details_add = (TextView) findViewById(R.id.tv_goods_details_add);//增加购买数量
        tv_goods_details_move = (TextView) findViewById(R.id.tv_goods_details_move);//减小购买数量
        tv_goods_details_num = (TextView) findViewById(R.id.tv_goods_details_num);//库存数量
        tv_goods_details_sumprice = (TextView) findViewById(R.id.tv_goods_details_sumprice);//购买总价
        tv_goods_details_price = (TextView) findViewById(R.id.tv_goods_details_price);//商品价格
        tv_goods_details_sales = (TextView) findViewById(R.id.tv_goods_details_sales);
        tv_goods_details_simple = (TextView) findViewById(R.id.tv_goods_details_simple);
        tv_goods_details_time = (TextView) findViewById(R.id.tv_goods_details_time);
        tv_goods_details_type = (TextView) findViewById(R.id.tv_goods_details_type);
        tv_goods_details_goodstext = (TextView) findViewById(R.id.tv_goods_details_goodstext);
        bt_home_goods_details_back = (Button) findViewById(R.id.bt_home_goods_details_back);
        tv_goods_details_addcart = (TextView) findViewById(R.id.tv_goods_details_addcart);
        tv_goods_details_buy = (TextView) findViewById(R.id.tv_goods_details_buy);


        Intent intent = getIntent();
        /**
         * 商品名name  根据name值查询数据库，返回GoodsBean对象
         */
        String name = intent.getSerializableExtra("name").toString();
        tv_goods_details_name.setText(name);
        homeAddGoodsDB = new HomeAddGoodsDB(HomeGoodsDetail.this);
        final GoodsBean goodBean = homeAddGoodsDB.queryGoodsByName(name);
        tv_goods_details_price.setText(goodBean.getPrice() + "");
        tv_goods_details_sales.setText(goodBean.getSales());
        tv_goods_details_simple.setText(goodBean.getSimple());
        tv_goods_details_time.setText("上市时间：" + goodBean.getTime());
        tv_goods_details_type.setText(goodBean.getType1() + "-" + goodBean.getType2());
        tv_goods_details_num.setText(goodBean.getNum() + "");
        tv_goods_details_goodstext.setText(goodBean.getGoodstext());
        tv_goods_details_sumprice.setText(goodBean.getPrice() + "");

        CurrentNum = Integer.parseInt(tv_goods_details_currentnum.getText().toString());
        num = Integer.parseInt(tv_goods_details_num.getText().toString());
        price = Integer.parseInt(tv_goods_details_price.getText().toString());

        /**
         * 数量-减按钮点击事件
         */
        tv_goods_details_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentNum == 1) {
                    Toast.makeText(HomeGoodsDetail.this, "最小购买数量为 1", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    CurrentNum--;
                    tv_goods_details_currentnum.setText(CurrentNum + "");
                    tv_goods_details_sumprice.setText((CurrentNum * price) + "");
                }
            }
        });

        /**
         * 数量+加按钮点击事件
         */
        tv_goods_details_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentNum == num) {
                    Toast.makeText(HomeGoodsDetail.this, "超过库存限制", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    CurrentNum++;
                    tv_goods_details_currentnum.setText(CurrentNum + "");
                    tv_goods_details_sumprice.setText((CurrentNum * price) + "");
                }
            }
        });

        bt_home_goods_details_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * 讲浏览记录添加到数据库中
         * 如果未登录的话，则不执行该方法
         */
        if (checkLogin()){
            homeHistoryDB = new HomeHistoryDB(HomeGoodsDetail.this);
            HistoryBean historyBean = new HistoryBean();
            historyBean.setUsername(LoginUsername);
            historyBean.setName(goodBean.getName());
            historyBean.setPrice(goodBean.getPrice());
            historyBean.setSales(goodBean.getSales());
            historyBean.setTime(getTime());
            homeHistoryDB.insertHistory(historyBean);
        }else{
            Toast.makeText(HomeGoodsDetail.this, "未登录，不添加数据！", Toast.LENGTH_SHORT).show();

        }


        /**
         * 添加到购物车按钮
         * 点击“添加到购物车后”执行判断操作，如果有用户登录，则执行插入到该用户购物车数据库中，如果没有登录，则跳转到登录页面
         * 跳转时携带登录方式，如果是从详情页跳转到的登录页，登陆成功后直接关闭登录页就可以了
         *
         */
        tv_goods_details_addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLogin()) {
                    /**
                     * 执行商品信息插入到购物车操作
                     */
                    cartDB = new CartDB(HomeGoodsDetail.this);
                    CartGoodsBean cartGoodsBean = new CartGoodsBean();
                    cartGoodsBean.setName(goodBean.getName());
                    cartGoodsBean.setBuynum(CurrentNum);
                    cartGoodsBean.setPrice(goodBean.getPrice());
                    cartGoodsBean.setUsername(LoginUsername);
                    cartDB.addCart(cartGoodsBean);
                    Toast.makeText(HomeGoodsDetail.this, "添加到购物车成功！", Toast.LENGTH_SHORT).show();

                } else {
                    /**
                     * 跳转到登录页面，执行登录操作
                     */
                    Toast.makeText(HomeGoodsDetail.this, "请先登录", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(HomeGoodsDetail.this, MyUserLogin.class);
                    Bundle data = new Bundle();
                    data.putSerializable("from", "goodsDetail");
                    intent1.putExtras(data);
                    startActivity(intent1);
                }


            }
        });


        /**
         * 立即购买按钮
         */
        tv_goods_details_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeGoodsDetail.this, "跳转到立即购买列表", Toast.LENGTH_SHORT).show();
            }
        });


    }


    /**
     * 检查当前是否有登录用户
     * @return
     */
    public boolean checkLogin(){
        myLoginInfoDB = new MyLoginInfoDB(HomeGoodsDetail.this);
        String username = myLoginInfoDB.queryUserInfo();
        if (username != null) {
            LoginUsername = username;
            return true;
        }else{
            return false;
        }
    }


    /**
     * 获取当前时间 2016-10-12 16:45:38  格式
     */
    public String getTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

}
