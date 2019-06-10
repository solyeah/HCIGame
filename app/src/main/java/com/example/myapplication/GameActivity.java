package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {
    User m_user;
    final int REQUEST_JUMP =1001;
    final int REQUEST_COUNT =1002;
    final int REQUEST_QUIZ = 1003;

    Button btn1, btn2, btn3,btn4;
    int health, intel, charm, tired, coin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        addListener();
    }

    private void addListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_user.getTired()<100){
                    m_user.setTired(m_user.getTired()+5);
                    Intent intent = new Intent(GameActivity.this, JumpRope.class);
                    startActivityForResult(intent,REQUEST_JUMP);
                }
                else{
                    Toast.makeText(GameActivity.this, "너무 피곤해요... 문구점에서 맛있는걸 사먹어요!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_user.getTired()<100) {
                    m_user.setTired(m_user.getTired() + 5);
                    Intent intent = new Intent(GameActivity.this, CountGameActivity.class);
                    startActivityForResult(intent, REQUEST_COUNT);
                }
                else{
                    Toast.makeText(GameActivity.this, "너무 피곤해요... 문구점에서 맛있는걸 사먹어요!!!", Toast.LENGTH_LONG).show();

                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_user.getTired()<100) {
                    m_user.setTired(m_user.getTired()+5);
                    Intent intent = new Intent(GameActivity.this, EnglishQuiz.class);
                    startActivityForResult(intent, REQUEST_QUIZ);
                }else{
                    Toast.makeText(GameActivity.this, "너무 피곤해요... 문구점에서 맛있는걸 사먹어요!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(intel>=100&&health>=100) {
                    if (charm >= 100) {
                        // Toast.makeText(GameActivity.this, "축하합니다! 졸업했습니다!!!!", Toast.LENGTH_LONG).show();
                        // GraduateActivity로 이동
                        Intent intent = new Intent(GameActivity.this, GraduateActivity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(GameActivity.this, "조금 더 공부해야해요~", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void init() {
        m_user = User.getInstance();

        intel = m_user.getIntel();
        health = m_user.getHealth();
        charm = m_user.getCharm();
        tired = m_user.getTired();
        coin = m_user.getCoin();

        btn1 = findViewById(R.id.game_btn1);
        btn2 = findViewById(R.id.game_btn2);
        btn3 = findViewById(R.id.game_btn3);
        btn4 = findViewById(R.id.game_btn4);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_JUMP) {
            int resultMsg = data.getIntExtra("result_msg",0);
            if(resultMsg==1){
                Toast.makeText(GameActivity.this, "맞췄습니다!!! 체력이 올라갑니다!", Toast.LENGTH_SHORT).show();
                m_user.setTired(m_user.getHealth()+5);
                m_user.setCoin(m_user.getCoin()+5);
                m_user.setHealth(m_user.getHealth()+5);
            }
            else{
                Toast.makeText(GameActivity.this, "틀렸습니다...", Toast.LENGTH_SHORT).show();
            }

        }
        if (requestCode == REQUEST_QUIZ) {
            int score = data.getIntExtra("score", 0);
            m_user.setCharm(m_user.getCharm()+score);
            m_user.setCoin(m_user.getCoin()+score);
        }
        if (requestCode == REQUEST_COUNT) {
            int resultMsg = data.getIntExtra("result_msg",2);
            if(resultMsg<=1){
                Toast.makeText(GameActivity.this, "맞췄습니다!!! 지능이 올라갑니다!", Toast.LENGTH_SHORT).show();
                m_user.setIntel(m_user.getIntel()+5);
                m_user.setCoin(m_user.getCoin()+1);
            }else{
                Toast.makeText(GameActivity.this, "틀렸습니다...", Toast.LENGTH_SHORT).show();

            }

        }

    }

}
