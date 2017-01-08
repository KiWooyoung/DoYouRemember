package com.omjoonkim.doyouremember.app.home.sendmoney;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class HomeSendItemDecoration extends RecyclerView.ItemDecoration {
    private final int spacingBottomSize;
    private final int spacingFirstSize;

    public HomeSendItemDecoration(int spacingBottomSize, int spacingFirstSize) {
        this.spacingBottomSize = spacingBottomSize;
        this.spacingFirstSize = spacingFirstSize;
    }

    @Override
    public void getItemOffsets(Rect outRect,
                               View view,
                               RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.bottom = spacingBottomSize;

        final int currentPosition  = parent.getChildAdapterPosition(view);

        if (currentPosition == 0){
            outRect.top = spacingFirstSize;
        }

    }
}
