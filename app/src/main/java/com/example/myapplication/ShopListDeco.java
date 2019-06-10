package com.example.myapplication;

        import android.graphics.Rect;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;

public class ShopListDeco extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.right = 50;
        }
    }
}
