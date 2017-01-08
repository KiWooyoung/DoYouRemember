package com.omjoonkim.doyouremember.app.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.ReceiveMoneyFragment;
import com.omjoonkim.doyouremember.app.home.sendmoney.SendMoneyFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment implements HomePresenter.View{
    public static final String TAG = HomeFragment.class.getSimpleName();

    public static HomeFragment newInstance() {return new HomeFragment();}

    private Fragment sendMoneyFragment;
    private Fragment receiveMoneyFragment;
    HomePresenter homePresenter;

    @OnClick({R.id.btn_subtab_send, R.id.btn_subtab_receive})
    void onSubtabClick(View view){
        switch (view.getId()){
            case R.id.btn_subtab_send:
                homePresenter.onClickSend();
                break;
            case R.id.btn_subtab_receive:
                homePresenter.onClickReceive();
                break;
        }
    }

    @OnClick(R.id.fab_writing)
    void onFabClick(View view){
        homePresenter.onClickFab();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenterImpl();
        homePresenter.setView(this);
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

    @Override
    public void loadSendMoneyFragment() {
        if(sendMoneyFragment == null){
            sendMoneyFragment =  SendMoneyFragment.newInstance();
        }
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_master,sendMoneyFragment).commit();
    }

    @Override
    public void loadReceiveMoneyFragment() {
        if(receiveMoneyFragment == null){
            receiveMoneyFragment =  ReceiveMoneyFragment.newInstance();
        }
        getChildFragmentManager().beginTransaction().replace(R.id.fragment_master, receiveMoneyFragment).commit();
    }

    @Override
    public void loadHomeWritingActivity() {

    }
}
