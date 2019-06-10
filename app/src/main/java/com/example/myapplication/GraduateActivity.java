package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class GraduateActivity extends AppCompatActivity {
    ImageView iv;
    User user;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graduate);
        init();
    }

    private void init(){
        user = User.getInstance();
        iv = findViewById(R.id.imageView);

        type = user.getType();
        switch (type){
            case 0:
                Glide.with(GraduateActivity.this).load(R.drawable.fox_grad).into(new DrawableImageViewTarget(iv));
                break;
            case 1:
                Glide.with(GraduateActivity.this).load(R.drawable.mouse).into(new DrawableImageViewTarget(iv));
                break;
            case 2:
                Glide.with(GraduateActivity.this).load(R.drawable.rabbit).into(new DrawableImageViewTarget(iv));
                break;
        }
    }
}
