package com.example.administrator.shoppingapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

import org.w3c.dom.Text;

/**
 * Created by Administrator on 2016/11/17.
 */
public class HomeSearchGoods extends AppCompatActivity {
    private TextView goods_list_back;
    private EditText et_search_text;
    private  RelativeLayout rl_search_nan_changku,rl_search_nan_txu,rl_search_nan_yurongfu,rl_search_nan_chenyi,
            rl_search_nv_dayi,rl_search_nv_yurongfu,rl_search_nv_yangmaoshan,rl_search_nv_niuzaiku;
    private Button bt_search_gosearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_search_goods);

        goods_list_back = (TextView) findViewById(R.id.goods_list_back);

        et_search_text = (EditText) findViewById(R.id.et_search_text);

        rl_search_nan_changku = (RelativeLayout) findViewById(R.id.rl_search_nan_changku);
        rl_search_nan_txu = (RelativeLayout) findViewById(R.id.rl_search_nan_txu);
        rl_search_nan_yurongfu = (RelativeLayout) findViewById(R.id.rl_search_nan_yurongfu);
        rl_search_nan_chenyi = (RelativeLayout) findViewById(R.id.rl_search_nan_chenyi);

        rl_search_nv_dayi = (RelativeLayout) findViewById(R.id.rl_search_nv_dayi);
        rl_search_nv_yurongfu = (RelativeLayout) findViewById(R.id.rl_search_nv_yurongfu);
        rl_search_nv_yangmaoshan = (RelativeLayout) findViewById(R.id.rl_search_nv_yangmaoshan);
        rl_search_nv_niuzaiku = (RelativeLayout) findViewById(R.id.rl_search_nv_niuzaiku);

        bt_search_gosearch = (Button) findViewById(R.id.bt_search_gosearch);

        /**
         * 查询按钮
         */
        bt_search_gosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word = et_search_text.getText().toString();
                if (word.length()==0){
                    Toast.makeText(HomeSearchGoods.this, "请输入商品关键字搜索！", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{//携带关键字跳转到宝贝列表界面
                    toIntent(word,"","");
                }
            }
        });

        /**
         * 男装-长裤   搜索
         */
        rl_search_nan_changku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","男装","长裤");
            }
        });

        /**
         * 男装-T恤  搜索
         */
        rl_search_nan_txu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","男装","T恤");
            }
        });

        /**
         * 男装-羽绒服  搜索
         */
        rl_search_nan_yurongfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","男装","羽绒服");
            }
        });

        /**
         * 男装-衬衣  搜索
         */
        rl_search_nan_chenyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","男装","衬衣");
            }
        });

        /**
         * 女装-毛呢大衣  搜索
         */
        rl_search_nv_dayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","女装","毛呢大衣");
            }
        });

        /**
         * 女装-羽绒服  搜索
         */
        rl_search_nv_yurongfu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","女装","羽绒服");
            }
        });

        /**
         * 女装-羊毛衫  搜索
         */
        rl_search_nv_yangmaoshan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","女装","羊毛衫");
            }
        });

        /**
         * 女装-牛仔裤  搜索
         */
        rl_search_nv_niuzaiku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toIntent("","女装","牛仔裤");
            }
        });



        /**
         * 界面返回按钮
         */
        goods_list_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    /**
     * Intent跳转方法
     * @param word  关键字
     * @param type1 类型1
     * @param type2 类型2
     */
    public void toIntent(String word,String type1,String type2){
        Intent intent = new Intent(HomeSearchGoods.this,HomeGoodsList.class);
        //携带三个参数开启新的Intent
        System.out.println(word.length());
        Bundle data = new Bundle();
        data.putSerializable("word",word);
        data.putSerializable("type1",type1);
        data.putSerializable("type2",type2);
        intent.putExtras(data);
        startActivity(intent);

    }


}
