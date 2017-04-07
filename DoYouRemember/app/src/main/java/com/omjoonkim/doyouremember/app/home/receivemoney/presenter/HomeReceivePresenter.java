package com.omjoonkim.doyouremember.app.home.receivemoney.presenter;

import com.omjoonkim.doyouremember.model.HomeReceiveChildData;

import java.util.List;

public interface HomeReceivePresenter {
    void setView(HomeReceivePresenter.View view);
    void onClickFab();
    void onClickKakaoLink(String receivePrice);
    void onClickedChecked(int parentPosition);

    interface View {
        void loadHomeReceiveWritingActivity();
        void updateDebtorCount(int parentPosition);
        void sendKaKaoLink(String receivePrice);
    }
}

