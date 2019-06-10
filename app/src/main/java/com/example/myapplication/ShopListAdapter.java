package com.example.myapplication;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder> {

    private ArrayList<ShopItem> itemList;
    private final Context context;
    private Handler handler;
//    private View.OnClickListener onClickItem;
//    private View.OnClickListener onClickItem;


    public ShopListAdapter(Context _context, ArrayList<ShopItem> itemList) {
        this.context = _context;
        this.itemList = itemList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // context 와 parent.getContext() 는 같다.
        View view = LayoutInflater.from(context)
                .inflate(R.layout.shop_item, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.textView.setText("가격:"+itemList.get(position).price+"원, 피로도:"+itemList.get(position).tired+"회복");
        holder.imageView.setImageResource(itemList.get(position).drawable);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemList.get(position).price<=User.getInstance().getCoin()){
                    User.getInstance().setCoin(User.getInstance().getCoin()-itemList.get(position).price);
                    User.getInstance().setTired(User.getInstance().getTired()-itemList.get(position).tired);
//                    Toast.makeText(context, "구매 완료~!! 힘이 나요!!", Toast.LENGTH_LONG).show();
                    View view = View.inflate(context,R.layout.toast_layout,null);
                    Toast toast = new Toast(context);
                    toast.setView(view);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();

                    Message msg = new Message();
                    msg.what=1;
                    ShopActivity.handler.sendMessage(msg);
                }
                else{
                    Toast.makeText(context, "코인이 부족해요ㅠㅠ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageButton btn;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_textview);
            btn = itemView.findViewById(R.id.purchaseBtn);
            textView = itemView.findViewById((R.id.item_info));
        }
    }
}
