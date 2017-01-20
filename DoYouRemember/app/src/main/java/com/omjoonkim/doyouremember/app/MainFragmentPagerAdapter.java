package com.omjoonkim.doyouremember.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.omjoonkim.doyouremember.app.frequentlyusedaccount.FrequentlyUsedAccountFragment;
import com.omjoonkim.doyouremember.app.home.HomeFragment;
import com.omjoonkim.doyouremember.app.myaccount.MyAccountFragment;

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "홈", "자주쓰는 계좌", "내 계좌" };


    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new FrequentlyUsedAccountFragment();
            case 2:
                return new MyAccountFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
