package com.example.administrator.shoppingapp.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.shoppingapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */
public class HomeGoodsList extends AppCompatActivity {
    private HomeAddGoodsDB homeAddGoodsDB;
    private ListView lv_goods_list;
    private TextView tv_goods_list_gosearch,tv_goods_list_txt;
    private EditText et_goods_list_search;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_goods_list);
        final Intent intent = getIntent();
        String word = intent.getSerializableExtra("word").toString();
        String type1 = intent.getSerializableExtra("type1").toString();
        String type2 = intent.getSerializableExtra("type2").toString();
        System.out.println("word:"+word+"@@type1:"+type1+"@@type2:"+type2);

        lv_goods_list = (ListView) findViewById(R.id.lv_goods_list);
        tv_goods_list_gosearch = (TextView) findViewById(R.id.tv_goods_list_gosearch);
        et_goods_list_search = (EditText) findViewById(R.id.et_goods_list_search);
        tv_goods_list_txt = (TextView) findViewById(R.id.tv_goods_list_txt);

        tv_goods_list_gosearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String word2 = et_goods_list_search.getText().toString();
                if (word2.length()!=0){
                    homeAddGoodsDB = new HomeAddGoodsDB(HomeGoodsList.this);
                    ArrayList<GoodsBean> list = homeAddGoodsDB.queryGoodsListByWord(word2);
                    if (list.size()==0){
                        tv_goods_list_txt.setText("未找到相关商品，请尝试搜索其他关键词！");
                        setAdapter(list);
                    }else {
                        setAdapter(list);
                        tv_goods_list_txt.setText("为您找到 "+list.size()+" 件相关商品~~");
                    }
                }else {
                    Toast.makeText(HomeGoodsList.this, "请输入关键字查询商品", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        if (word.length()!=0){//执行按关键字查询商品的方法
            homeAddGoodsDB = new HomeAddGoodsDB(HomeGoodsList.this);
            ArrayList<GoodsBean> list = homeAddGoodsDB.queryGoodsListByWord(word);
            if (list.size()==0){
                tv_goods_list_txt.setText("未找到相关商品，请尝试搜索其他关键词！");
                setAdapter(list);
            }else{
                setAdapter(list);
                tv_goods_list_txt.setText("为您找到 "+list.size()+" 件相关商品~~");
            }

        }else{//执行按商品类型查询的方法
            homeAddGoodsDB = new HomeAddGoodsDB(HomeGoodsList.this);
            ArrayList<GoodsBean> list = homeAddGoodsDB.queryGoodsListByType(type1,type2);
            if (list.size()==0){
                tv_goods_list_txt.setText("未找到相关商品，请尝试搜索其他分类！");
                setAdapter(list);
            }else{
                setAdapter(list);
            }

        }

        lv_goods_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv_goods_item_name = (TextView) view.findViewById(R.id.tv_goods_item_name);
                String name = tv_goods_item_name.getText().toString();
                Bundle data = new Bundle();
                data.putSerializable("name",name);
                Intent intent1 = new Intent(HomeGoodsList.this,HomeGoodsDetail.class);
                intent1.putExtras(data);
                startActivity(intent1);
            }
        });



    }

    private void setAdapter(ArrayList<GoodsBean> list){
        HomeGoodsAdapter homeGoodsAdapter = new HomeGoodsAdapter(list,HomeGoodsList.this);
        lv_goods_list.setAdapter(homeGoodsAdapter);
    }

}
