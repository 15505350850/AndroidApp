package com.example.administrator.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/11/16.
 */
public class WelcomeFirstActivity extends AppCompatActivity {
    private ImageView logo,iv_text;
    Button into_app ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_first);

        logo = (ImageView) findViewById(R.id.logo);
        iv_text = (ImageView) findViewById(R.id.iv_text);
        into_app = (Button) findViewById(R.id.into_app);

        /*Animation animation = AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.logo_in);
        logo.startAnimation(animation);

        Animation animation1 = AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.text_in);
        iv_text.startAnimation(animation1);

        Animation animation2 = AnimationUtils.loadAnimation(WelcomeActivity.this,R.anim.button_in);
        into_app.startAnimation(animation2);*/

        //logo.setAnimationStyle(R.style.animation);


        into_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeFirstActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
