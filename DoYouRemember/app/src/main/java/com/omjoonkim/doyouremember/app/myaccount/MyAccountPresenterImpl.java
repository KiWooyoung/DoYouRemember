package com.omjoonkim.doyouremember.app.myaccount;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class MyAccountPresenterImpl implements MyAccountPresenter {

    MyAccountFragment view;
    MyAccountModel model;

    public MyAccountPresenterImpl(MyAccountFragment view) {
        this.view = view;
        this.model = new MyAccountModel();
    }

    @Override
    public void onAddMyAccount() {
        view.goRegisterMyAccount();
    }
}
