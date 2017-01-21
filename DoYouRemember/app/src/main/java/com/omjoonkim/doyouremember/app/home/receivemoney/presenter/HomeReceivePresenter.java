package com.omjoonkim.doyouremember.app.home.receivemoney.presenter;

import com.omjoonkim.doyouremember.model.HomeReceiveChildData;

import java.util.List;

public interface HomeReceivePresenter {
    void setView(HomeReceivePresenter.View view);
    void onClickKakaoLink();
    void onClickedChecked(int parentPosition);

    interface View {
        void updateDebtorCount(int parentPosition);
    }
}
