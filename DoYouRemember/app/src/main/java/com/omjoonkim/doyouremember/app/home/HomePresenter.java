package com.omjoonkim.doyouremember.app.home;

public interface HomePresenter {
    void setView(HomePresenter.View view);
    void onClickSend();
    void onClickReceive();

    interface View {
        void loadSendMoneyFragment();
        void loadReceiveMoneyFragment();
    }
}
