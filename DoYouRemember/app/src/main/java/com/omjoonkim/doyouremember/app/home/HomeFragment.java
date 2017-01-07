package com.omjoonkim.doyouremember.app.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.ReceiveMoneyFragment;
import com.omjoonkim.doyouremember.app.home.sendmoney.SendMoneyFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment {
    public static final String TAG = HomeFragment.class.getSimpleName();

    @OnClick({R.id.button21, R.id.button22})
    void onClick(View view){
        Log.v(TAG, "HELLO");
        switch (view.getId()){
            case R.id.button21:
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragment_master, new SendMoneyFragment())
                        .commit();
                break;
            case R.id.button22:
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.fragment_master, new ReceiveMoneyFragment())
                        .commit();
                break;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        if(getChildFragmentManager().findFragmentById(R.id.fragment_master) == null){
            getChildFragmentManager().beginTransaction()
                    .add(R.id.fragment_master, new SendMoneyFragment())
                    .commit();
        }
    }


}
