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
        if (view != null)
            view = null;
    }

    @Override
    public void onRegisterMyAccount(String bankType, String myAccountNumber) {

        boolean flagOverlap;

        flagOverlap = model.checkOverlapAccount(myAccountNumber);

        if (myAccountNumber.equals("") || bankType.equals("")) {
            view.showAllTypeToast();
        } else if (flagOverlap) {
            view.showOverlapAccountToast();
        } else {
            model.registerMyAccount(bankType, myAccountNumber);
            view.goFinish();
        }
    }

    @Override
    public void onReviseMyAccount(String accountNumber, String newBankType, String newAccountNumber) {
        boolean flagOverlap;

        flagOverlap = model.checkReviseOverlapAccount(accountNumber, newAccountNumber);

        if (newAccountNumber.equals("") || newBankType.equals("")) {
            view.showAllTypeToast();
        } else if (flagOverlap) {
            view.showOverlapAccountToast();
        } else {
            model.reviseMyAccount(accountNumber, newAccountNumber, newBankType);
            view.goFinish();
        }
    }
}
