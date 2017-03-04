package com.omjoonkim.doyouremember.app.myaccount;

import com.omjoonkim.doyouremember.app.myaccount.adapter.MyAccountAdapter;

import java.util.List;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public interface MyAccountView {
    void goRegisterMyAccount();
    void showDefaultText();
    void notifyItemsChanged(List<MyAccountAdapter.ItemView> items);
    void notifyChangeFavoriteMyAccount(int position);

    void goRevise(int position);
    void showDeleteDialog(int position);

    void notifyItemRemoved(int position);

    void copyMyAccount(int position);
}
