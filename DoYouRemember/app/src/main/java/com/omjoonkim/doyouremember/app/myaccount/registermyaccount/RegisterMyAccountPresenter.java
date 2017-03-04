package com.omjoonkim.doyouremember.app.myaccount.registermyaccount;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public interface RegisterMyAccountPresenter {
    void onChooseBank();
    void onDestroy();

    void onRegisterMyAccount(String bankType, String myAccountNumber);

    void onReviseMyAccount(String accountNumber, String newBankType, String newAccountNumber);
}
