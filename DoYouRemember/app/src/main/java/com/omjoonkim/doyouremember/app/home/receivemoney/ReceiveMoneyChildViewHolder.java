package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveMoneyChildViewHolder extends ChildViewHolder {

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

    public void update(final HomeReceiveChildData childData){
        tvDebtor.setText(childData.getDebtorName());
        String priceReceiveIndividualKRW = NumberFormat.getInstance(Locale.KOREA).format(childData.getPriceIndividual());
        tvReceivePriceIndiIndividual.setText(priceReceiveIndividualKRW+"원");
    }
}
