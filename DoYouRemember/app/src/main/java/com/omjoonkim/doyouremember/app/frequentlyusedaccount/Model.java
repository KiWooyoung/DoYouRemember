package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 2017-01-17.
 */

public class Model {

    public void loadData(PresenterImpl presenter) {
        presenter.init(initData());
    }

    public List<FrequentlyUesdAccountAdapter.ItemView> initData() {
        List<FrequentlyUesdAccountAdapter.ItemView> data = new ArrayList<>();
        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd", R.mipmap.ic_launcher));
        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd", R.mipmap.ic_launcher));
        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd", R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));
//        data.add(new FrequentlyUesdAccountAdapter.ItemView("asd","asd",R.mipmap.ic_launcher));

        return data;

    }
}
