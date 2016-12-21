package com.example.administrator.shoppingapp.Util;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.shoppingapp.MainActivity;
import com.example.administrator.shoppingapp.Repair.RepairTianqi;

import org.json.JSONObject;

/**
 * Created by Administrator on 2016/12/9.
 */
public class GetData {
    private Context context;
    private JSONObject rjsonObject;


    public GetData(Context context) {
        this.context = context;
    }

    public JSONObject DataGet(String url){


        return rjsonObject;
    }

    public void DataPost(){

    }


}
