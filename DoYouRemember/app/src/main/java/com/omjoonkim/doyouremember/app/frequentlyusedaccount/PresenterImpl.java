package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public class PresenterImpl implements Presenter {

    FrequentlyUsedAccountFragment view;
    Model model;

    public PresenterImpl(FrequentlyUsedAccountFragment view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public void onAddAccount() {
        view.addAccount();
    }

    @Override
    public void modifyAccount() {

    }

    @Override
    public void createList() {

    }

    @Override
    public void setModel() {
        model.loadData(this);
    }

    @Override
    public void init(List<FrequentlyUesdAccountAdapter.ItemView> data) {
        view.notifyItemChanged(data);

    }

    @Override
    public void onDestroy() {
        view = null;
    }


}
