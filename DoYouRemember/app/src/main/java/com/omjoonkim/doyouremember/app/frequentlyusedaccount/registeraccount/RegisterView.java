package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;

/**
 * Created by wooyoungki on 2017. 1. 21..
 */

public interface RegisterView {
    void showBankTypeDialog();
    void setRevise();

    void goFinish();
    void showAllTypeToast();
    void showOverlapAccountToast();
}
