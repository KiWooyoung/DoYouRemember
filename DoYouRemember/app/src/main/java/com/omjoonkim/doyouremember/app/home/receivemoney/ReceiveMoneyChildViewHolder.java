package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.support.annotation.NonNull;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ReceiveMoneyChildViewHolder extends ChildViewHolder {

    public static final String TAG = ReceiveMoneyChildViewHolder.class.getSimpleName();

    @BindView(R.id.checkbox_isReceiveDone)
    CheckBox cbReceiveDone;

    @BindView(R.id.textView_debtor_name)
    TextView tvDebtor;

    @BindView(R.id.textView_receive_price_individual)
    TextView tvReceivePriceIndiIndividual;

    @BindView(R.id.textView_receiveDoneLine)
    TextView tvReceiveDoneLine;

    @BindView(R.id.imageView_receive_KaKaoLink)
    ImageView imgKaKaoLink;

    public ReceiveMoneyChildViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final HomeReceiveChildData childData,
                           final List<HomeReceiveParentData> parentDataList,
                           final int parentPosition,
                           final OnHomeReceiveClickListener listener){

        // 받을 사람 이름
        tvDebtor.setText(childData.getDebtorName());

        // 받을 돈(원)
        String priceReceiveIndividualKRW = NumberFormat.getInstance(Locale.KOREA).format(childData.getPriceIndividual());
        tvReceivePriceIndiIndividual.setText(priceReceiveIndividualKRW+"원");

        // 카카오 링크 버튼
        imgKaKaoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSendKakaoLink();
            }
        });

        // 받았는지 여부 체크
        cbReceiveDone.setChecked(childData.isReceiveChecked());
        cbReceiveDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    tvReceiveDoneLine.setBackgroundResource(R.drawable.rounded_corner_done_line_on);
                }else{
                    tvReceiveDoneLine.setBackgroundResource(R.drawable.rounded_corner_done_line_off);
                }
                childData.setReceiveChecked(isChecked);
                parentDataList.get(parentPosition).sumChecked();
                listener.onCheckedDebtors(parentPosition);
            }
        });
    }
}
