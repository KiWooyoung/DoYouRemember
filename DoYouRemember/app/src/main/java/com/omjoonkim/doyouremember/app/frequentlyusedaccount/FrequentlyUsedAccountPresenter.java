package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public interface FrequentlyUsedAccountPresenter {

    void deleteAccount();
    void onAddFrequentlyUsedAccount();
    void modifyAccount();
    void createList();
    void setModel();
    void init(List<FrequentlyUesdAccountAdapter.ItemView> data);

    void onDestroy();

    void swipeWriteList(int position);
    void swipeRevise();
    void swipeDelete();
}
