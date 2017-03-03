package com.omjoonkim.doyouremember.app.writing.selectfrequentlyusedaccount;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SelectFrequentUserItemDecoration extends RecyclerView.ItemDecoration {
    private final int spacingFirstSize;

    public SelectFrequentUserItemDecoration(int spacingFirstSize) {
        this.spacingFirstSize = spacingFirstSize;
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int currentPosition  = parent.getChildAdapterPosition(view);

        if (currentPosition == 0){
            outRect.top = spacingFirstSize;
        }
    }
}