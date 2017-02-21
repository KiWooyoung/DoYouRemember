package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import android.os.Handler;
import android.os.Looper;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public class FrequentlyUsedAccountPresenterImpl implements FrequentlyUsedAccountPresenter {

    private FrequentlyUsedAccountFragment view;
    private FrequentlyUsedAccountModel model;

    FrequentlyUsedAccountPresenterImpl(FrequentlyUsedAccountFragment view) {
        this.view = view;
        model = new FrequentlyUsedAccountModel();
    }

    @Override
    public void deleteAccount(int position, String info) {
        model.deleteData(info);
        view.notifyItemRemoved(position);
    }

    @Override
    public void onAddFrequentlyUsedAccount() {
        view.addFrequentlyUsedAccount();
    }

    @Override
    public void modifyAccount() {

    }

    @Override
    public void setModel() {
        model.loadData(this);
    }

    @Override
    public void setView(final List<FrequentlyUesdAccountAdapter.ItemView> data) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                view.notifyItemChanged(data);
            }
        });
    }

    @Override
    public void onDestroy() {
        view = null;
        model = null;
    }

    @Override
    public void swipeWriteList(int position) {
        view.goWriteList(position);
    }

    @Override
    public void swipeRevise(int position) {
        view.goRevise(position);
    }

    @Override
    public void swipeDelete(int position) {
        view.showDeleteDialog(position);
    }
}
