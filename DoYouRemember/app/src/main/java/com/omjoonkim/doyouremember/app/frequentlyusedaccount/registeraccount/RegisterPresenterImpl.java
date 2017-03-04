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

        boolean flagOverlap;

        flagOverlap = model.checkOverlapAccount(accountNumber);

        if (name.equals("") || accountNumber.equals("") || bank.equals("")) {
            view.showAllTypeToast();
        } else if (flagOverlap) {
            view.showOverlapAccountToast();
        } else {
            model.registerFrequentlyUsedAccount(name, accountNumber, bank);
            view.goFinish();
        }
    }

    @Override
    public void onChooseBank() {
        view.showBankTypeDialog();
    }

    @Override
    public void onDestroy() {
        if (view != null) //Todo 객체를 다 죽이는 본질적인 이유를 공부하기
            view = null;
        if (model != null)
            model = null;

    }

    @Override
    public void checkRevise() {
        view.setRevise();
    }

    @Override
    public void onRevise(String accountNumber, String newName, String newAccountNumber, String newBankType) {

        boolean flagOverlap;

        flagOverlap = model.checkReviseOverlapAccount(accountNumber, newAccountNumber);

        if (newName.equals("") || newAccountNumber.equals("") || newBankType.equals("")) {
            view.showAllTypeToast();
        } else if (flagOverlap) {
            view.showOverlapAccountToast();
        } else {
            model.reviseFrequentlyUsedAccount(accountNumber, newName, newAccountNumber, newBankType);
            view.goFinish();
        }
    }


}
