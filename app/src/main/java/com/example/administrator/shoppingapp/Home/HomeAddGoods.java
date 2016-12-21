package com.example.administrator.shoppingapp.Home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.administrator.shoppingapp.My.MyLoginInfoDB;
import com.example.administrator.shoppingapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/21.
 */
public class HomeAddGoods extends AppCompatActivity {
    private String[] type1 = new String[]{"男装", "女装"};
    private String[] dtype = new String[]{"T恤", "衬衣", "羽绒服", "长裤"};
    private String[][] type2 = new String[][]{{"T恤", "衬衣", "羽绒服", "长裤"}, {"毛呢大衣", "羽绒服", "羊毛衫", "牛仔裤"}};
    private Spinner firsttype,sp_sales;
    private Spinner secondtype;
    //private Context context;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter2;
    private EditText et_add_goods_name,et_add_goods_simple,et_add_goods_price,et_add_goods_num,et_add_goods_time,et_add_goods_text;
    private Button bt_add_goods_add,bt_add_gettime;

    private HomeAddGoodsDB homeAddGoodsDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_add_goods);
        //context = this;
        firsttype = (Spinner) findViewById(R.id.province);
        secondtype = (Spinner) findViewById(R.id.city);
        sp_sales = (Spinner) findViewById(R.id.sp_sales);
        et_add_goods_name = (EditText) findViewById(R.id.et_add_goods_name);
        et_add_goods_simple = (EditText) findViewById(R.id.et_add_goods_simple);
        et_add_goods_price = (EditText) findViewById(R.id.et_add_goods_price);
        et_add_goods_num = (EditText) findViewById(R.id.et_add_goods_num);
        et_add_goods_time = (EditText) findViewById(R.id.et_add_goods_time);
        et_add_goods_text = (EditText) findViewById(R.id.et_add_goods_text);
        bt_add_goods_add = (Button) findViewById(R.id.bt_add_goods_add);
        bt_add_gettime = (Button) findViewById(R.id.bt_add_gettime);


        adapter = new ArrayAdapter<String>(HomeAddGoods.this, android.R.layout.simple_spinner_item, type1);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        firsttype.setAdapter(adapter);
        firsttype.setOnItemSelectedListener(selectListener);
        adapter2 = new ArrayAdapter<String>(HomeAddGoods.this, android.R.layout.simple_spinner_item, dtype);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        secondtype.setAdapter(adapter2);

        bt_add_gettime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
                et_add_goods_time.setText(df.format(new Date()));
                //System.out.println();// new Date()为获取当前系统时间
            }
        });



        bt_add_goods_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsBean goodsBean = new GoodsBean();
                goodsBean.setName(et_add_goods_name.getText().toString());
                goodsBean.setSimple(et_add_goods_simple.getText().toString());
                goodsBean.setPrice(Integer.parseInt(et_add_goods_price.getText().toString()));
                goodsBean.setType1(firsttype.getSelectedItem().toString());
                goodsBean.setType2(secondtype.getSelectedItem().toString());
                goodsBean.setSales(sp_sales.getSelectedItem().toString());
                goodsBean.setNum(Integer.parseInt(et_add_goods_num.getText().toString()));
                goodsBean.setTime(et_add_goods_time.getText().toString());
                goodsBean.setGoodstext(et_add_goods_text.getText().toString());
                System.out.println(goodsBean.toString());
                homeAddGoodsDB = new HomeAddGoodsDB(HomeAddGoods.this);
                homeAddGoodsDB.insertGoods(goodsBean);
                Toast.makeText(HomeAddGoods.this, "添加成功！", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });




    }

    private AdapterView.OnItemSelectedListener selectListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView parent, View v, int position, long id) {
            int pos = firsttype.getSelectedItemPosition();
            adapter2 = new ArrayAdapter<String>(HomeAddGoods.this, android.R.layout.simple_dropdown_item_1line, type2[pos]);
            secondtype.setAdapter(adapter2);
        }

        public void onNothingSelected(AdapterView arg0) {

        }

    };
}
