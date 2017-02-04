package com.omjoonkim.doyouremember.app.home;

public interface HomePresenter {
    void setView(HomePresenter.View view);
    void onClickSend();
    void onClickReceive();
    void onClickFab();

    interface View {
        void loadSendMoneyFragment();
        void loadReceiveMoneyFragment();
        void loadHomeWritingActivity();
    }
}
