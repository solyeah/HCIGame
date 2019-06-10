package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;


public class HomeActivity extends AppCompatActivity {
    TextView t1, t2, t3;
    ImageView iv;
    User user;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        Intent i = getIntent();
        user = User.getInstance();

        t1 = findViewById(R.id.home_tv1);
        t2 = findViewById(R.id.home_tv2);
        t3 = findViewById(R.id.home_tv3);
        iv = findViewById(R.id.home_iv);


        t1.setText(user.getU_name()+"의 마이홈");
        t2.setText("이름 "+user.getA_name()+"\n매력 "+user.getCharm()+"\n지능 "+user.getIntel()+"\n" +
                "건강 "+user.getHealth()+"\n피로도 "+user.getTired()+"\n");
        t3.setText("x "+user.getCoin());

        type = user.getType();
        switch (type){
            case 0:
                Glide.with(HomeActivity.this).load(R.drawable.fox).into(new DrawableImageViewTarget(iv));
                break;
            case 1:
                Glide.with(HomeActivity.this).load(R.drawable.mouse).into(new DrawableImageViewTarget(iv));
                break;
            case 2:
                Glide.with(HomeActivity.this).load(R.drawable.rabbit).into(new DrawableImageViewTarget(iv));
                break;
        }

    }
}
