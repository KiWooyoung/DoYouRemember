package com.omjoonkim.doyouremember.app.home.receivemoney.presenter;


public class HomeReceivePresenterImpl implements HomeReceivePresenter {

    public static final String TAG = HomeReceivePresenterImpl.class.getSimpleName();

    private HomeReceivePresenter.View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onClickKakaoLink() {
        this.view.sendKaKaoLink();
    }

    @Override
    public void onClickedChecked(int parentPosition) {
        this.view.updateDebtorCount(parentPosition);
    }
}
