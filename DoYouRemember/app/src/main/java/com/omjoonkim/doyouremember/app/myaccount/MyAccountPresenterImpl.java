package com.omjoonkim.doyouremember.app.myaccount;

import com.omjoonkim.doyouremember.app.myaccount.adapter.MyAccountAdapter;

import java.util.List;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class MyAccountPresenterImpl implements MyAccountPresenter {

    MyAccountView view;
    MyAccountModel model;

    public MyAccountPresenterImpl(MyAccountFragment view) {
        this.view = view;
        this.model = new MyAccountModel();
    }

    @Override
    public void onAddMyAccount() {
        view.goRegisterMyAccount();
    }

    @Override
    public void setModel() {
        model.loadData(this);
    }

    @Override
    public void setView(List<MyAccountAdapter.ItemView> items) {
        view.notifyItemsChanged(items);
    }

    @Override
    public void onClickFavoriteMyAccount(int position, String myAccountNumber) {
        model.updateFavoriteMyAccount(myAccountNumber);
        view.notifyChangeFavoriteMyAccount(position);
    }

    @Override
    public String setMainAccountBox() {
        return model.setMainAccountBoxData();
    }

    @Override
    public void swipeCopy() {

    }

    @Override
    public void swipeRevise(int position) {
        view.goRevise(position);
    }

    @Override
    public void swipeDelete(int position) {
        view.showDeleteDialog(position);
    }

    @Override
    public void deleteMyAccount(int position, String accountNumber) {
        model.deleteData(accountNumber);
        view.notifyItemRemoved(position);
    }


}
