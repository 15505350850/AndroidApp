package com.example.administrator.shoppingapp.Repair;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.shoppingapp.R;
import com.example.administrator.shoppingapp.Util.GetData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/17.
 */
public class RepairTianqi extends AppCompatActivity {
    private String url = "https://api.thinkpage.cn/v3/weather/now.json?key=nex1kjvld2tgduln&location=ip&language=zh-Hans&unit=c";


    private TextView tv_repair_tianqi_temperature,tv_repair_tianqi_name,tv_repair_tianqi_path,tv_repair_tianqi_text,tv_repair_tianqi_last_update,tv_repair_tianqi_back;
    private ImageView iv_repair_tianqi_code ;
    private RelativeLayout rl_repair_tianqi1,rl_repair_tianqi2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_tianqi);
        //test = (TextView) findViewById(R.id.test);
        rl_repair_tianqi1 = (RelativeLayout) findViewById(R.id.rl_repair_tianqi1);
        rl_repair_tianqi2 = (RelativeLayout) findViewById(R.id.rl_repair_tianqi2);
        tv_repair_tianqi_temperature = (TextView) findViewById(R.id.tv_repair_tianqi_temperature);
        tv_repair_tianqi_name = (TextView) findViewById(R.id.tv_repair_tianqi_name);
        tv_repair_tianqi_path = (TextView) findViewById(R.id.tv_repair_tianqi_path);
        tv_repair_tianqi_text = (TextView) findViewById(R.id.tv_repair_tianqi_text);
        tv_repair_tianqi_last_update = (TextView) findViewById(R.id.tv_repair_tianqi_last_update);
        iv_repair_tianqi_code = (ImageView) findViewById(R.id.iv_repair_tianqi_code);

        tv_repair_tianqi_back = (TextView) findViewById(R.id.tv_repair_tianqi_back);
        tv_repair_tianqi_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        rl_repair_tianqi1.getBackground().setAlpha(100);
        rl_repair_tianqi2.getBackground().setAlpha(100);

        Toast.makeText(RepairTianqi.this,"正在获取本地天气信息……",Toast.LENGTH_SHORT).show();




        RequestQueue queue = Volley.newRequestQueue(RepairTianqi.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //test.setText(jsonObject.toString());
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("results");
                    for (int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                        String last_update = jsonObject1.getString("last_update");//最后更新时间
                        JSONObject location = jsonObject1.getJSONObject("location");
                        JSONObject now = jsonObject1.getJSONObject("now");

                        String path = location.getString("path");
                        String name = location.getString("name");
                        String timezone_offset = location.getString("timezone_offset");

                        String text = now.getString("text");
                        String code = now.getString("code");
                        String temperature = now.getString("temperature");

                        tv_repair_tianqi_temperature.setText(temperature + "℃");
                        tv_repair_tianqi_name.setText(name);
                        tv_repair_tianqi_text.setText("当前天气： "+text);
                        tv_repair_tianqi_last_update.setText(last_update);
                        iv_repair_tianqi_code.setImageResource(getDrawResourceID("x"+ code));

                        //System.out.println("当前天气：   地区："+path+"  时区："+timezone_offset + " 天气："+text+"  温度："+temperature+"  代码："+code);

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(jsonObjectRequest);


    }
    public int getDrawResourceID(String resourceName) {
        /*
        根据文件名获取文件ID
         */
        Resources res=getResources();
        int picid = res.getIdentifier(resourceName,"drawable",getPackageName());
        return picid;
    }









}
