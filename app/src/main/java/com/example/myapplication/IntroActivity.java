package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntroActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button btn1;
    EditText e1, e2;
    User m_user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        init();
//        setSound();
        addListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
        mediaPlayer=null;
        }
    }
    private void addListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, PickActivity.class);
                m_user.setU_name(e1.getText().toString());
                m_user.setA_name(e2.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void setSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.bgm);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }

    private void init() {
            btn1 = findViewById(R.id.intro_btn);
            e1 = findViewById(R.id.intro_ed1);
            e2 = findViewById(R.id.intro_ed2);
    }

}

