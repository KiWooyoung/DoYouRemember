package com.omjoonkim.doyouremember.app.home.receivemoney;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;
import com.thoughtbot.expandablecheckrecyclerview.viewholders.CheckableChildViewHolder;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeReceiveMoneyChildViewHolder extends CheckableChildViewHolder {

    public static final String TAG = HomeReceiveMoneyChildViewHolder.class.getSimpleName();

    @BindView(R.id.checkedTextView_debtor_name)
    CheckedTextView ctvDebtorName;

    @BindView(R.id.textView_receive_price_individual)
    TextView tvReceivePriceIndiIndividual;

    @BindView(R.id.textView_receiveDoneLine)
    TextView tvReceiveDoneLine;

    @BindView(R.id.imageView_receive_KaKaoLink)
    ImageView imgKaKaoLink;

    public HomeReceiveMoneyChildViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public Checkable getCheckable() {
        return ctvDebtorName;
    }

    public void bind(HomeReceiveChildData childData){

        ctvDebtorName.setText(childData.getDebtorName());

        String priceReceiveIndividualKRW = NumberFormat.getInstance(Locale.KOREA).format(childData.getPriceIndividual());
        tvReceivePriceIndiIndividual.setText(priceReceiveIndividualKRW+"원");

        // 카카오 링크 버튼
        imgKaKaoLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("imgKaKaoLink","========kakaolink===========");
            }
        });
    }

}
