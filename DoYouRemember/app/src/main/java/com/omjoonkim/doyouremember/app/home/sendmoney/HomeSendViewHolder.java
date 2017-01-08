package com.omjoonkim.doyouremember.app.home.sendmoney;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.omjoonkim.doyouremember.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSendViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.textview_title)
    TextView textView_title;

    @BindView(R.id.textview_creditor)
    TextView textview_creditor;

    @BindView(R.id.textview_account)
    TextView textview_account;

    @BindView(R.id.textview_price)
    TextView textview_price;

    @BindView(R.id.frame_send_deadline)
    FrameLayout frame_send_deadline;

    @BindView(R.id.swipe_home_send_item)
    SwipeLayout swipeLayout;

    @BindView(R.id.imageview_copy)
    ImageView imageview_copy;

    @BindView(R.id.imageview_delete)
    ImageView imageview_delete;

    public HomeSendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
