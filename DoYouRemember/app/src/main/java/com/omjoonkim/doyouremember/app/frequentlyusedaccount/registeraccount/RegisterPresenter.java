package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;

/**
 * Created by wooyoungki on 2017. 1. 21..
 */

public interface RegisterPresenter {

    void onRegister(String name, String accountNumber, String bank);
    void onChooseBank();
    void onDestroy();

    void checkRevise();

    void onRevise(String accountNumber, String newName, String newAccountNumber, String newBankType);
}
