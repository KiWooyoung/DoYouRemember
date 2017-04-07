package com.omjoonkim.doyouremember.app.home.receivemoney.dialog;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class SelectMessageFragment extends Fragment {

    public static final String TAG = SelectMessageFragment.class.getSimpleName();

    @BindView(R.id.textView_kakao_message)
    TextView tvMessage;

    @BindView(R.id.imageView_message_index)
    ImageView imgMessageIndicator;

    private String kakaoMessage;
    private Realm realm;

    public static SelectMessageFragment newInstance(int position, String receivePrice) {
        SelectMessageFragment selectMessageFragment = new SelectMessageFragment();
        Bundle args = new Bundle();
        args.putInt("messagePosition", position);
        args.putString("receivePrice", receivePrice);
        selectMessageFragment.setArguments(args);
        return selectMessageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        realm = AppRealm.get().DylRealm();
        View view = inflater.inflate(R.layout.fragment_select_kakao_message, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Resources res = getResources();

        AccountRealmObject myAccount = realm.where(AccountRealmObject.class)
                .equalTo("isMine", true)
                .equalTo("favorite", true)
                .findFirst();

        switch (getArguments().getInt("messagePosition")){
            case 0:
                kakaoMessage = String.format(res.getString(R.string.kakao_message_1)
                        , myAccount.getBankType()
                        , myAccount.getAccountNumber()
                        , getArguments().getString("receivePrice"));

                imgMessageIndicator.setImageResource(R.drawable.index_1);
                break;
            case 1:
                kakaoMessage = String.format(res.getString(R.string.kakao_message_2)
                        , myAccount.getBankType()
                        , myAccount.getAccountNumber()
                        , getArguments().getString("receivePrice"));
                imgMessageIndicator.setImageResource(R.drawable.index_2);
                break;
            case 2:
                kakaoMessage = String.format(res.getString(R.string.kakao_message_3)
                        , myAccount.getBankType()
                        , myAccount.getAccountNumber()
                        , getArguments().getString("receivePrice"));
                imgMessageIndicator.setImageResource(R.drawable.index_3);
                break;
        }

        tvMessage.setText(kakaoMessage);
    }

    public String getKaKaoMessage() {
        return tvMessage.getText().toString();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
