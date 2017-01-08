package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveMoneyFragment extends Fragment {

    public static ReceiveMoneyFragment newInstance() {
        return new ReceiveMoneyFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_receive_money, container, false);
    }

}
