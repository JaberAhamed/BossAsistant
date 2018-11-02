package com.example.jahed.bossassistant;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jahed on 10/9/2018.
 */

public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {


    private int offset;

    public ItemOffsetDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);



            outRect.right = offset;
            outRect.left = offset;
            outRect.top = offset;
            outRect.bottom = offset;



    }
}
