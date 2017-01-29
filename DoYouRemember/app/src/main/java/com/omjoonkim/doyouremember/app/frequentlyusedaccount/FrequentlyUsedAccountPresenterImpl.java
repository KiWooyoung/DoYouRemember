package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public class FrequentlyUsedAccountPresenterImpl implements FrequentlyUsedAccountPresenter {

    FrequentlyUsedAccountFragment view;
    FrequentlyUsedAccountModel frequentlyUsedAccountModel;

    public FrequentlyUsedAccountPresenterImpl(FrequentlyUsedAccountFragment view) {
        this.view = view;
        frequentlyUsedAccountModel = new FrequentlyUsedAccountModel();
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void onAddFrequentlyUsedAccount() {
        view.addFrequentlyUsedAccount();
    }

    @Override
    public void modifyAccount() {

    }

    @Override
    public void createList() {

    }

    @Override
    public void setModel() {
        frequentlyUsedAccountModel.loadData(this);
    }

    @Override
    public void init(List<FrequentlyUesdAccountAdapter.ItemView> data) {
        view.notifyItemChanged(data);

    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void swipeWriteList(int position) {
        view.goWriteList(position);
    }

    @Override
    public void swipeRevise() {

    }

    @Override
    public void swipeDelete() {
        view.showDeleteDialog();
    }


}
