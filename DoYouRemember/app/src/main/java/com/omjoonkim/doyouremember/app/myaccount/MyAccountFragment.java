package com.omjoonkim.doyouremember.app.myaccount;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;

import butterknife.ButterKnife;

/**
 * Created by owner on 2017-01-13.
 */

public class MyAccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
