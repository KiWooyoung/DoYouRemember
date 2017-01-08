package com.omjoonkim.doyouremember.app.home.sendmoney;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;


public class SendMoneyFragment extends Fragment {

    @BindView(R.id.recycler_home_send)
    RecyclerView recyclerView;

    @BindDimen(R.dimen.send_list_bottom_margin)
    int spacingBottomSize;

    @BindDimen(R.dimen.send_list_first_margin)
    int spacingFirstSize;

    HomeSendAdapter adapter;

    public static SendMoneyFragment newInstance() {
        return new SendMoneyFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_money, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerViewInit();
    }

    private void initRecyclerViewInit() {
        adapter = new HomeSendAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HomeSendItemDecoration(spacingBottomSize, spacingFirstSize));
    }

}
