package com.omjoonkim.doyouremember.app.home;

public class HomePresenterImpl implements HomePresenter{
    private HomePresenter.View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onClickSend() {
        view.loadSendMoneyFragment();
    }

    @Override
    public void onClickReceive() {
        view.loadReceiveMoneyFragment();
    }
}
