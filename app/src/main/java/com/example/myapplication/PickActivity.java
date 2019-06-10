package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class PickActivity extends AppCompatActivity {
    ImageButton btn1,btn2,btn3;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);
        init();
        addListener();
    }
    private void init() {
        btn1 = findViewById(R.id.pick_btn1);
        btn2 = findViewById(R.id.pick_btn2);
        btn3 = findViewById(R.id.pick_btn3);

    }

    private void addListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickActivity.this, MainActivity.class);
                user.getInstance().setType(0);//여우
                user.getInstance().setIntel(98);//여우
                user.getInstance().setTired(98);//여우
                user.getInstance().setCharm(98);//여우
                user.getInstance().setHealth(98);//여우
                user.getInstance().setCoin(10);//여우
                startActivity(intent);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickActivity.this, MainActivity.class);
                user.getInstance().setType(1);//쥐
                user.getInstance().setIntel(30);
                user.getInstance().setCharm(40);
                user.getInstance().setHealth(50);
                startActivity(intent);
                finish();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PickActivity.this, MainActivity.class);
                user.getInstance().setType(2);//토끼
                user.getInstance().setIntel(40);
                user.getInstance().setCharm(50);
                user.getInstance().setHealth(30);
                startActivity(intent);
                finish();
            }
        });
    }
}
