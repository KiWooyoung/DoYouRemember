package com.omjoonkim.doyouremember.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.HomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.viewpager)
	ViewPager viewPager;

	@BindView(R.id.tabs_main)
	TabLayout tabLayout;

	MainFragmentPagerAdapter fragmentPagerAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		fragmentPagerAdapter = new MainFragmentPagerAdapter(getSupportFragmentManager());
		fragmentPagerAdapter.addFragment(HomeFragment.newInstance(), "홈");
		fragmentPagerAdapter.addFragment(HomeFragment.newInstance(), "자주쓰는 계좌");
		fragmentPagerAdapter.addFragment(HomeFragment.newInstance(), "내 계좌");
		viewPager.setAdapter(fragmentPagerAdapter);
		viewPager.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}
		});
		tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				viewPager.setCurrentItem(tab.getPosition());
			}

			@Override
			public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override
			public void onTabReselected(TabLayout.Tab tab) {

			}
		});
		tabLayout.setupWithViewPager(viewPager);
	}

}
