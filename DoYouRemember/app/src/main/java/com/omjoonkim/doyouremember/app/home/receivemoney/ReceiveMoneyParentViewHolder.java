package com.omjoonkim.doyouremember.app.home.receivemoney;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.daimajia.swipe.SwipeLayout;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveMoneyParentViewHolder extends ParentViewHolder {

    public static final String TAG = ReceiveMoneyParentViewHolder.class.getSimpleName();

    @BindView(R.id.textview_receive_title)
    TextView tvReceiveTitle;

    @BindView(R.id.textview_receive_debtor_count)
    TextView tvReceiveDebtorCount;

    @BindView(R.id.textview_receive_price_total)
    TextView tvReceivePriceTotal;

    @BindView(R.id.frame_receive_deadline)
    FrameLayout frameReceiveDeadline;

    @BindView(R.id.swipe_home_receive_item)
    SwipeLayout swipeHomeReceive;

    @BindView(R.id.imageview_receive_delete)
    ImageView imgReceiveDelete;

    public ReceiveMoneyParentViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateData(HomeReceiveParentData parentData){
        tvReceiveTitle.setText(parentData.getTitle());
        parentData.sumChecked();
        String countReceiveDebtors = parentData.getCheckedCount() + "명/" + parentData.getChildList().size()+"명";
        Log.v(TAG, countReceiveDebtors);
        tvReceiveDebtorCount.setText(countReceiveDebtors);
        String priceReceiveTotalKRW = NumberFormat.getInstance(Locale.KOREA).format(parentData.getTotalPrice());
        tvReceivePriceTotal.setText(priceReceiveTotalKRW+"원");
    }

    public void occurEvent(final HomeReceiveChildData homeReceiveChildData,
                           final OnHomeReceiveClickListener listener){

    }
}

