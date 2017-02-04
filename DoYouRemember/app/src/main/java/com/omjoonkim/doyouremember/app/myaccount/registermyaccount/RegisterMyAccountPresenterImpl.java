package com.omjoonkim.doyouremember.app.myaccount.registermyaccount;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class RegisterMyAccountPresenterImpl implements RegisterMyAccountPresenter {

    RegisterMyAccountActivity view = null;
    RegisterMyAccountModel model = null;

    public RegisterMyAccountPresenterImpl(RegisterMyAccountActivity view) {
        this.view = view;
        this.model = new RegisterMyAccountModel();
    }

    @Override
    public void onChooseBank() {
        view.showDialog();
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
