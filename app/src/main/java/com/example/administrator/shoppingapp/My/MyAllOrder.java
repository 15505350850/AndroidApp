package com.example.administrator.shoppingapp.My;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.shoppingapp.R;

/**
 * Created by Administrator on 2016/11/17.
 */
public class MyAllOrder extends AppCompatActivity {
    private TextView tv_my_all_order_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_all_order);
        tv_my_all_order_back = (TextView) findViewById(R.id.tv_my_all_order_back);
        tv_my_all_order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }
}
