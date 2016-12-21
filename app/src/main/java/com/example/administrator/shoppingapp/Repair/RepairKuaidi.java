package com.example.administrator.shoppingapp.Repair;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.shoppingapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/17.
 */
public class RepairKuaidi extends AppCompatActivity {
    TextView htmltext,delete_postid;
    TextView select_express_type,postid;
    Button query_express;
    TextView kuaidigongsi,companytype,nu,dangqianzhuangtai,updatetime,lsjl;
    ListView list_result;
    DBPostid dbPostid;
    String url = "http://www.kuaidi100.com/query?";
    private String[] type = new String[]{"申通快递","圆通快递","百世汇通","顺丰快递","中通快递","韵达快递","EMS快递","天天快递",
            "德邦物流","快捷速递","全峰快递","优速物流","邮政国内小包","宅急送",""};
    String[] type_e = new String[]{"shentong","yuantong","huitongkuaidi","shunfeng","zhongtong","yunda","ems","tiantian",
            "debangwuliu","kuaijiesudi","quanfengkuaidi","yousuwuliu","youzhengguonei","zhaijisong"};
    String[] zhuangtai = new String[]{"正在运输","正在揽件","疑难件","已签收","退签",
            "正在派件，请保持电话通畅！","快件被退回","未知"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repair_kuaidi);

        select_express_type = (TextView) findViewById(R.id.select_express_type);
        postid = (TextView) findViewById(R.id.postid);
        query_express = (Button) findViewById(R.id.query_express);
        kuaidigongsi = (TextView) findViewById(R.id.kuaidigongsi);
        companytype = (TextView) findViewById(R.id.companytype);
        nu = (TextView) findViewById(R.id.nu);
        dangqianzhuangtai = (TextView) findViewById(R.id.dangqianzhuangtai);
        updatetime = (TextView) findViewById(R.id.updatetime);
        list_result = (ListView) findViewById(R.id.list_result);
        lsjl = (TextView) findViewById(R.id.lsjl);
        delete_postid = (TextView) findViewById(R.id.delete_postid);

        /*
        选择快递公司控件
         */
        select_express_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(RepairKuaidi.this).setTitle("请选择快递公司:").setItems(type, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        select_express_type.setText(type[which]);
                    }
                })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).create().show();
            }
        });


        //点击【查询】按钮事件
        query_express.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取单号和快递公司。如果为空则提示必须输入
                String getpostid = postid.getText().toString();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(postid.getWindowToken(),0);
                String getExpresstype = select_express_type.getText().toString();
                if (getExpresstype.equals("请选择快递公司")){
                    Toast.makeText(RepairKuaidi.this, "请选择快递公司！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getpostid.length()==0){
                    Toast.makeText(RepairKuaidi.this, "请输入正确的运单号码！", Toast.LENGTH_SHORT).show();
                    return;
                }
                System.out.println(getpostid+"@@@@@"+getExpresstype);
                /*
                快递公司 中文代码转换为英文代码
                 */
                String daima = "";
                for (int i=0;i<type.length;i++){
                    if (type[i].equals(getExpresstype)){
                        daima = type_e[i];
                    }
                }
                //修改URL数据,获取请求数据
                //url = url+"type=" + daima + "&postid=" +getpostid;
                System.out.println("0099887766::"+url);
                getData(url+"type=" + daima + "&postid=" +getpostid);
            }
        });

        /*
        加载历史查询列表
         */
        /*
        自定义adapter类
         */
        lsjl.setText("历史查询记录：(长按可删除记录)");
        dbPostid = new DBPostid(RepairKuaidi.this);
        ArrayList<HList> itemArrayList = new ArrayList<>();
        itemArrayList = dbPostid.SelectList();
        HistoricalAdapter adapter = new HistoricalAdapter(itemArrayList,RepairKuaidi.this);
        list_result.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //----------------------------------------------------------------------------------------
        list_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView h_postid = (TextView) view.findViewById(R.id.h_postid);
                TextView h_express = (TextView) view.findViewById(R.id.h_express);
                String e = h_express.getText().toString();
                postid.setText(h_postid.getText().toString());
                select_express_type.setText(h_express.getText().toString());
            }
        });


        //点击删除快递单号事件
        delete_postid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postid.setText("");
                kuaidigongsi.setText("");
                companytype.setText("");
                nu.setText("");
                dangqianzhuangtai.setText("");
                updatetime.setText("");

                select_express_type.setText("请选择快递公司");
                lsjl.setText("历史查询记录：(长按可删除记录)");
                dbPostid = new DBPostid(RepairKuaidi.this);
                ArrayList<HList> itemArrayList = new ArrayList<>();
                itemArrayList = dbPostid.SelectList();
                HistoricalAdapter adapter = new HistoricalAdapter(itemArrayList,RepairKuaidi.this);
                list_result.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //删除完成重新设置 历史记录列表

                list_result.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TextView h_postid = (TextView) view.findViewById(R.id.h_postid);
                        TextView h_express = (TextView) view.findViewById(R.id.h_express);
                        String e = h_express.getText().toString();
                        postid.setText(h_postid.getText().toString());
                        select_express_type.setText(h_express.getText().toString());

                    }
                });


            }
        });


    }


    public void getData(String url){//请求数据的方法
        RequestQueue queue = Volley.newRequestQueue(this);
        final String[] post_result = {""};
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                //请求成功后
                Log.d("请求的数据：",jsonObject.toString());
                try {
                    String status = jsonObject.getString("status");
                    //System.out.println("status=="+status);
                    if (status.equals("403")) {
                        Toast.makeText(RepairKuaidi.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else if (status.equals("201")) {
                        Toast.makeText(RepairKuaidi.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else if (status.equals("400")) {
                        Toast.makeText(RepairKuaidi.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                    } else if (status.equals("200")) {//数据正常
                        kuaidigongsi.setText("快递公司：");
                        lsjl.setText("查询结果：");
                        /*
                        获取快递单号
                         */
                        nu.setText("快递单号："+jsonObject.getString("nu"));
                        /*
                        获取快递公司中文名称
                         */
                        for (int j = 0;j<type_e.length;j++){
                            if (type_e[j].equals(jsonObject.getString("com"))){
                                companytype.setText(type[j]);
                            }
                        }
                        /*
                        获取快递当前状态
                         */
                        int a=Integer.valueOf(jsonObject.getString("state")).intValue();
                        dangqianzhuangtai.setText("当前状态：");
                        updatetime.setText(zhuangtai[a]);
                        /*
                        获取快递数据并设置到listview中去
                         */
                        JSONArray listdata = jsonObject.getJSONArray("data");
                        //System.out.println(listdata.getString("context"));
                        List<Map<String, Object>> listTItems = new ArrayList<Map<String, Object>>();
                        for (int i = 0; i < listdata.length(); i++) {
                            Map<String, Object> listItem = new HashMap<String, Object>();
                            JSONObject ob = (JSONObject) listdata.get(i);//得到json对象
                            listItem.put("time", ob.getString("time"));
                            listItem.put("context", ob.getString("context"));
                            listTItems.add(listItem);
                        }
                        SimpleAdapter simpleAdapter = new SimpleAdapter(RepairKuaidi.this, listTItems, R.layout.repair_kuaidi_listdata,
                                new String[]{"time", "context"}, new int[]{R.id.time, R.id.context}
                        );
                        lsjl.setText("");
                        list_result.setAdapter(simpleAdapter);
                        /*
                        将查询记录写入到数据库中
                         */
                        dbPostid = new DBPostid(RepairKuaidi.this);
                        int postid_flage = dbPostid.QueryUsername(jsonObject.getString("nu"));
                        if (postid_flage == 0) {
                            select_express_type.getText().toString();
                            dbPostid.insert(jsonObject.getString("nu"), select_express_type.getText().toString());
                        } else {

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("error:", volleyError.getMessage().toString());
                Toast.makeText(RepairKuaidi.this, "网络连接异常！", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void inflateList(Cursor cursor) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                RepairKuaidi.this, R.layout.repair_kuaidi_listitem, cursor, new String[]{"postid", "express"}
                , new int[]{R.id.h_postid, R.id.h_express}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        list_result.setAdapter(adapter);
    }
}
