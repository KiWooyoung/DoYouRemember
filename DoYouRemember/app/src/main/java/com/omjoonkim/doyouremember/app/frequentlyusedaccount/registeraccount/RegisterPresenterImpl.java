package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;

/**
 * Created by wooyoungki on 2017. 1. 21..
 */

public class RegisterPresenterImpl implements RegisterPresenter {

    RegisterView view = null;
    RegisterModel model = null;

    public RegisterPresenterImpl(RegisterView view) {
        this.view = view;
        model = new RegisterModel();
    }


    @Override
    public void onRegister(String name, String accountNumber, String bank) {
        //Todo 양식 다 넣어라고 조건 넣기
        model.registerFrequentlyUsedAccount(name, accountNumber, bank);
    }

    @Override
    public void onChooseBank() {
        view.showDialog();
    }

    @Override
    public void onDestroy() {
        if(view != null)
            view = null;
        if(model != null)
            model = null;
    }
}
