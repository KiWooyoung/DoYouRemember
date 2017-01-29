package com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.omjoonkim.doyouremember.R.id.image_view_cancel;
import static com.omjoonkim.doyouremember.R.id.relative_layout_cancel;
import static com.omjoonkim.doyouremember.R.id.text_view_busan;
import static com.omjoonkim.doyouremember.R.id.text_view_city;
import static com.omjoonkim.doyouremember.R.id.text_view_hana;
import static com.omjoonkim.doyouremember.R.id.text_view_ibk;
import static com.omjoonkim.doyouremember.R.id.text_view_kb;
import static com.omjoonkim.doyouremember.R.id.text_view_nonghyub;
import static com.omjoonkim.doyouremember.R.id.text_view_sinhan;
import static com.omjoonkim.doyouremember.R.id.text_view_woori;

/**
 * Created by wooyoungki on 2017. 1. 27..
 */

public class SelectBankDialog extends DialogFragment {

    private String bank = null;
    private SelectBankListener listener = null;

    @BindView(R.id.text_view_kb) TextView txtKb;
    @BindView(R.id.text_view_woori) TextView txtWoori;
    @BindView(R.id.text_view_sinhan) TextView txtSinhan;
    @BindView(R.id.text_view_hana) TextView txtHana;
    @BindView(R.id.text_view_city) TextView txtCity;
    @BindView(R.id.text_view_nonghyub) TextView txtNonghyub;
    @BindView(R.id.text_view_ibk) TextView txtIbk;
    @BindView(R.id.text_view_busan) TextView txtBusan;
    @OnClick({relative_layout_cancel, image_view_cancel, text_view_kb, text_view_woori,
    text_view_sinhan, text_view_hana, text_view_city, text_view_nonghyub, text_view_ibk, text_view_busan})
    public void onClick(View v) {
        switch (v.getId()) {
            case relative_layout_cancel :
                dismiss();
                break;
            case image_view_cancel :
                dismiss();
                break;
            case text_view_kb :
                bank = txtKb.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_woori :
                bank = txtWoori.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_sinhan :
                bank = txtSinhan.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_hana :
                bank = txtHana.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_city :
                bank = txtCity.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_nonghyub :
                bank = txtNonghyub.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_ibk :
                bank = txtIbk.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
            case text_view_busan :
                bank = txtBusan.getText().toString().substring(0,2);
                dismiss();
                listener.onSelectBank(bank);
                break;
        }
    }

    public static SelectBankDialog newDialogInstance() {
        SelectBankDialog frag = new SelectBankDialog();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_register_frequently_used_account_select_bank, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listener = (SelectBankListener) getActivity();
    }

    public interface SelectBankListener {
        void onSelectBank(String bank);
    }

}
