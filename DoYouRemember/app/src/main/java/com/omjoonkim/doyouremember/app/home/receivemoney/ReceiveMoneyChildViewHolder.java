package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemRecyclerMangerImpl;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;
import com.omjoonkim.doyouremember.model.HomeSendData;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveMoneyChildViewHolder extends ChildViewHolder {

    public static final String TAG = ReceiveMoneyChildViewHolder.class.getSimpleName();

    @BindView(R.id.checkbox_isReceiveDone)
    CheckBox cbReceiveDone;

    @BindView(R.id.textview_debtor_name)
    TextView tvDebtor;

    @BindView(R.id.textview_receive_price_individual)
    TextView tvReceivePriceIndiIndividual;

    @BindView(R.id.imageview_receive_kakaolink)
    ImageView imgKakaolink;

    public ReceiveMoneyChildViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateData(final HomeReceiveChildData childData){
        tvDebtor.setText(childData.getDebtorName());
        cbReceiveDone.setChecked(childData.isReceiveChecked());
        String priceReceiveIndividualKRW = NumberFormat.getInstance(Locale.KOREA).format(childData.getPriceIndividual());
        tvReceivePriceIndiIndividual.setText(priceReceiveIndividualKRW+"원");
    }

    public void occurEvent(final HomeReceiveChildData childData,
                           final List<HomeReceiveParentData> parentDataList,
                           final int parentPosition,
                           final OnHomeReceiveClickListener listener){
        imgKakaolink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSendKakaoLink();
            }
        });

        cbReceiveDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                childData.setReceiveChecked(isChecked);
                parentDataList.get(parentPosition).sumChecked();
                listener.onCheckedDebtors(parentPosition);
            }
        });

    }
}
