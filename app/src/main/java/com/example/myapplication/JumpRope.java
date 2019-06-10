package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

public class JumpRope extends AppCompatActivity {
    TextView tv1,tv2,tv3,tv5;
    ImageView ibtn;
    Button btn1,btn2,btn3;
    int cnt,ans,delay,type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_rope);
        init();
        addListener();
    }

    private void init() {
        tv1 = (TextView)findViewById(R.id.jump_tv1);
        tv2 = (TextView)findViewById(R.id.jump_tv2);
        tv3 = (TextView)findViewById(R.id.jump_tv3);
        tv5 = (TextView)findViewById(R.id.jump_tv5);
        ibtn = (ImageView) findViewById(R.id.jump_iv);
        btn1 = (Button)findViewById(R.id.jump_btn1);
        btn2 = (Button)findViewById(R.id.jump_btn2);
        btn3 = (Button)findViewById(R.id.jump_btn3);
        Intent i = getIntent();
        User m_user = User.getInstance();
        type = m_user.getType();
        switch (type){
            case 0:
                Glide.with(JumpRope.this).load(R.drawable.fox).into(ibtn);
                break;
            case 1:
                Glide.with(JumpRope.this).load(R.drawable.mouse).into(ibtn);
                break;
            case 2:
                Glide.with(JumpRope.this).load(R.drawable.rabbit).into(ibtn);
                break;
        }
        int num1 = (int) (Math.random()*5)+1;
        int num2 = (int) (Math.random()*5)+1;
        tv1.setText(Integer.toString(num1));
        tv2.setText("+");
        tv3.setText(Integer.toString(num2));
        ans = num1+num2;
        cnt=0;

    }

    private void addListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cnt++;
                tv5.setText(Integer.toString(cnt));
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cnt>0) {
                    cnt--;
                    tv5.setText(Integer.toString(cnt));
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cnt>0){
                    delay = 2000*cnt;
                    switch(type){
                        case 0:
                            Glide.with(JumpRope.this).load(R.raw.fox_jump).into(new DrawableImageViewTarget(ibtn) {
                                @Override public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    if (resource instanceof GifDrawable) {
                                        ((GifDrawable)resource).setLoopCount(cnt);
                                    }
                                    super.onResourceReady(resource, transition);
                                }
                            });
                            break;
                        case 1:
                            Glide.with(JumpRope.this).load(R.raw.mouse_jump).into(new DrawableImageViewTarget(ibtn) {
                                @Override public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    if (resource instanceof GifDrawable) {
                                        ((GifDrawable)resource).setLoopCount(cnt);
                                    }
                                    super.onResourceReady(resource, transition);
                                }
                            });
                            break;
                        case 2:
                            Glide.with(JumpRope.this).load(R.raw.rabbit_jump).into(new DrawableImageViewTarget(ibtn) {
                                @Override public void onResourceReady(Drawable resource, @Nullable Transition<? super Drawable> transition) {
                                    if (resource instanceof GifDrawable) {
                                        ((GifDrawable)resource).setLoopCount(cnt);
                                    }
                                    super.onResourceReady(resource, transition);
                                }
                            });
                            break;
                    }

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Intent intent = new Intent();
                            if(cnt==ans){
                                intent.putExtra("result_msg",1);
//                                m_sound.getInstance().playCR();
                            }else{
                                intent.putExtra("result_msg",2);
//                                m_sound.getInstance().playWR();
                            }
                            setResult(RESULT_OK,intent);
                            finish();
                        }
                    }, delay);
                }
            }
        });

    }
}
