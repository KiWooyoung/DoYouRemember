package com.omjoonkim.doyouremember.app.myaccount;

import com.omjoonkim.doyouremember.app.myaccount.adapter.MyAccountAdapter;

import java.util.List;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public interface MyAccountPresenter {
    void onAddMyAccount();

    void setModel();
    void setView(List<MyAccountAdapter.ItemView> items);

    void onClickFavoriteMyAccount(int position, String myAccountNumber);

    String setMainAccountBox();

    void swipeCopy(int position);
    void swipeRevise(int position);
    void swipeDelete(int position);

    void deleteMyAccount(int position, String accountNumber);
}
