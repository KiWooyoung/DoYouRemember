package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public interface FrequentlyUsedAccountPresenter {

    void deleteAccount(int position, String info);
    void onAddFrequentlyUsedAccount();
    void modifyAccount();
    void setModel();
    void setView(List<FrequentlyUesdAccountAdapter.ItemView> data);

    void swipeWriteList(int position);
    void swipeRevise(int position);
    void swipeDelete(int position);

    void onDestroy();
}
