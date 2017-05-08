package com.omjoonkim.doyouremember.app.writing.receivemoney;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.writing.receivemoney.listener.OnWritingReceiveClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;


public class WritingReceiveHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.textView_send_people)
    TextView tvPeopleTitle;

    @BindView(R.id.imageView_delete_send_people)
    ImageView imgDelPeople;

    @BindView(R.id.editText_writing_receive_debtor)
    EditText etWritingDebtor;

    @BindView(R.id.editText_writing_receive_price)
    EditText etWritingPrice;

    @OnFocusChange( R.id.editText_writing_receive_debtor )
    void onFocusChangeDebtor( boolean hasFocus ) {
        if ( hasFocus ) {
            etWritingDebtor.setBackgroundResource( R.drawable.back_change );
        }
        else {
            etWritingDebtor.setBackgroundResource( R.drawable.back );
        }
    }

    @OnFocusChange( R.id.editText_writing_receive_price )
    void onFocusChangePrice( boolean hasFocus ) {
        if ( hasFocus ) {
            etWritingPrice.setBackgroundResource( R.drawable.back_change );
        }
        else {
            etWritingPrice.setBackgroundResource( R.drawable.back );
        }
    }

    public WritingReceiveHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String peopleTitle, final OnWritingReceiveClickListener listener, final int position){

        tvPeopleTitle.setText(peopleTitle+(position+1));
        imgDelPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickSendDelete(position);
            }
        });
    }
}
