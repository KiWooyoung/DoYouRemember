package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public interface Presenter {

    void deleteAccount();
    void onAddAccount();
    void modifyAccount();
    void createList();
    void setModel();
    void init(List<FrequentlyUesdAccountAdapter.ItemView> data);

    void onDestroy();
}
