package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public interface FrequentlyUsedAccountView {
//    void notifyItemInserted(int position);
    void notifyItemRemoved(int position);
    void notifyItemChanged(List<FrequentlyUesdAccountAdapter.ItemView> data);
    void showBackgroundImg();

    void addFrequentlyUsedAccount();

    void goWriteList(int position);
    void goRevise(int position);
    void showDeleteDialog(int position);
}
