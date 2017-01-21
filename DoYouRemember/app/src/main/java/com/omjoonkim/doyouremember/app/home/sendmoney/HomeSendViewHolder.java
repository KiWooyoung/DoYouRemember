package com.omjoonkim.doyouremember.app.home.sendmoney;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemAdapterMangerImpl;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.model.HomeSendData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSendViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.textview_title)
    TextView tvSendTitle;

    @BindView(R.id.textview_creditor)
    TextView tvSendCreditor;

    @BindView(R.id.textview_account)
    TextView tvSendAccount;

    @BindView(R.id.textview_price)
    TextView tvSendPrice;

    @BindView(R.id.frame_send_deadline)
    FrameLayout frameSendDeadline;

    @BindView(R.id.swipe_home_send_item)
    SwipeLayout swipeHomeSend;

    @BindView(R.id.imageview_copy)
    ImageView imgCopy;

    @BindView(R.id.imageview_edit)
    ImageView imgEdit;

    @BindView(R.id.imageview_delete)
    ImageView imgDelete;

    public HomeSendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateData(final HomeSendData homeSendData){

        swipeHomeSend.setShowMode(SwipeLayout.ShowMode.PullOut);

        swipeHomeSend.addDrag(SwipeLayout.DragEdge.Right,
                swipeHomeSend.findViewById(R.id.swipe_home_send_menu));

        tvSendTitle.setText(homeSendData.getTitle());
        tvSendCreditor.setText(homeSendData.getCreditor());
        tvSendAccount.setText(homeSendData.getAccount());
        String priceSendRW = NumberFormat.getInstance(Locale.KOREA).format(homeSendData.getPrice());
        tvSendPrice.setText(priceSendRW+"원");

        switch (homeSendData.getDeadline()){
            case 0 :
                frameSendDeadline.setBackgroundResource(R.drawable.rounded_corner_red);
                break;
            case 1 :
                frameSendDeadline.setBackgroundResource(R.drawable.rounded_corner_yellow);
                break;
            case 2 :
                frameSendDeadline.setBackgroundResource(R.drawable.rounded_corner_blue);
                break;
            case 3 :
                frameSendDeadline.setBackgroundResource(R.drawable.rounded_corner_gray);
                break;
        }
    }

    public void occurEvent(final List<HomeSendData> homeSendDataList,
                           final int position,
                           final OnHomeSendClickListener listener,
                           final SwipeItemRecyclerMangerImpl itemAdapterManger){
        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSendCopy(homeSendDataList.get(position));
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSendEdit();
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemAdapterManger.removeShownLayouts(swipeHomeSend);
                itemAdapterManger.closeAllItems();
                listener.onClickSendDelete(homeSendDataList, position);
            }
        });

        itemAdapterManger.bindView(itemView, position);

    }
}
