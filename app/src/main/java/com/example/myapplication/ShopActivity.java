package com.example.myapplication;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import java.util.ArrayList;


public class ShopActivity extends AppCompatActivity {

    private RecyclerView itemList;
    private ShopListAdapter adapter;
    User user;
    TextView t3;
    int type;
    ImageView iv;
    static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        init();

        handler = new Handler() { //Looper는 메세지큐에 메세지가 있으면 그 메세지를 꺼내서 핸들러에게 //전달하며 핸덜리의 아래 콜백 메소드를 호출하게 된다. @Override public void handleMessage(Message msg) { //전달 받은 Message 오브젝트를 가지고 관련된 작업을 수행 할 수 있다. //예를 들어 textView의 텍스트를 업데이트 한다든가... //이번예제 에서는 msg의 arg1에 1을 넣었음으로 //msg.arg1을 통하여 숫자 1을 얻을 수 있다. } }; //현재스레드와 통신할 스레드 B를 실행한다. //이제 b스레드가 작업을 수행하면서 스레드 A 로 메제지를 보내는 방법은 //아래 코드를 보면 알 수 있다. Thread b = new Thread(new B()); b.start();
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:     // 메시지로 넘겨받은 파라미터, 이 값으로 어떤 처리를 할지 결정
                        t3 = findViewById(R.id.shop_tv3);
                        t3.setText("x "+user.getCoin());
                        break;
                }
            }
    };
    }


    public void init() {
        itemList = findViewById(R.id.shopitem_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        itemList.setLayoutManager(layoutManager);
        user = User.getInstance();
        t3 = findViewById(R.id.shop_tv3);
        iv = findViewById(R.id.shop_iv);

        t3.setText("x "+user.getCoin());

        type = user.getType();
        switch (type){
            case 0:
                Glide.with(ShopActivity.this).load(R.drawable.fox).into(new DrawableImageViewTarget(iv));
                break;
            case 1:
                Glide.with(ShopActivity.this).load(R.drawable.mouse).into(new DrawableImageViewTarget(iv));
                break;
            case 2:
                Glide.with(ShopActivity.this).load(R.drawable.rabbit).into(new DrawableImageViewTarget(iv));
                break;
        }

        ArrayList<ShopItem> itemArrayList = new ArrayList<>();
        itemArrayList.add(new ShopItem(R.drawable.shop_item_1,1, 1));
        itemArrayList.add(new ShopItem(R.drawable.shop_item_2,2, 2));
        itemArrayList.add(new ShopItem(R.drawable.shop_item_3,3, 3));
        itemArrayList.add(new ShopItem(R.drawable.shop_item_4,4, 4));
        itemArrayList.add(new ShopItem(R.drawable.shop_item_5,5, 5));

        adapter = new ShopListAdapter(this, itemArrayList);
        itemList.setAdapter(adapter);

        ShopListDeco deco = new ShopListDeco();
        itemList.addItemDecoration(deco);

    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(ShopActivity.this, str, Toast.LENGTH_SHORT).show();
            t3.setText("x "+user.getCoin());
        }
    };

}
