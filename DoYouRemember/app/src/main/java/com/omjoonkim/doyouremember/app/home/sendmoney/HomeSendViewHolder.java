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
import com.omjoonkim.doyouremember.realm.entitiy.SendMoneyRealmObject;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeSendViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.textView_title)
    TextView tvSendTitle;

    @BindView(R.id.textView_creditor)
    TextView tvSendCreditor;

    @BindView(R.id.textView_account)
    TextView tvSendAccount;

    @BindView(R.id.textView_bankType)
    TextView tvSendBankType;

    @BindView(R.id.textView_price)
    TextView tvSendPrice;

    @BindView(R.id.frame_send_deadline)
    FrameLayout frameSendDeadline;

    @BindView(R.id.swipe_home_send_item)
    SwipeLayout swipeHomeSend;

    @BindView(R.id.imageView_copy)
    ImageView imgCopy;

    @BindView(R.id.imageView_edit)
    ImageView imgEdit;

    @BindView(R.id.imageView_delete)
    ImageView imgDelete;

    @BindView( R.id.textView_deadLine )
    TextView textView_deadLine;

    public HomeSendViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final SendMoneyRealmObject item,
                     final int position,
                     final OnHomeSendClickListener listener,
                     final SwipeItemRecyclerMangerImpl itemAdapterManger){

        swipeHomeSend.setShowMode(SwipeLayout.ShowMode.PullOut);
        swipeHomeSend.addDrag(SwipeLayout.DragEdge.Right,
                swipeHomeSend.findViewById(R.id.swipe_home_send_menu));

        tvSendTitle.setText(item.getTitle());
        tvSendCreditor.setText(item.getCreditor());
        tvSendBankType.setText(item.getBankType());
        tvSendAccount.setText(item.getAccount());
        String priceSendRW = NumberFormat.getInstance(Locale.KOREA).format(item.getPrice());
        tvSendPrice.setText(priceSendRW+"원");

        textView_deadLine.setText( item.getTheLastTime() );

        switch (item.getDeadLineColor()){
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

        imgCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSendCopy(item.getAccount());
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSendEdit(item.getId());
            }
        });

        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSendDelete( item.getId() );
                itemAdapterManger.removeShownLayouts(swipeHomeSend);
                itemAdapterManger.closeAllItems();
            }
        });

        itemAdapterManger.bindView(itemView, position);
    }
}
