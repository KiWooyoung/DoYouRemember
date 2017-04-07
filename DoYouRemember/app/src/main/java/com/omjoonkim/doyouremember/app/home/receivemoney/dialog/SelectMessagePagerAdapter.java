package com.omjoonkim.doyouremember.app.home.receivemoney.dialog;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

public class SelectMessagePagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();
    private static int NUM_MESSAGE = 3;

    private String receivePrice;

    public SelectMessagePagerAdapter(FragmentManager fm, String receivePrice) {
        super(fm);
        this.receivePrice = receivePrice;
    }

    @Override
    public Fragment getItem(int position) {
        return SelectMessageFragment.newInstance(position, receivePrice);
    }

    @Override
    public int getCount() {
        return NUM_MESSAGE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragments.add(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getRegisteredFragment(int position) {
        return mFragments.get(position);
    }

}