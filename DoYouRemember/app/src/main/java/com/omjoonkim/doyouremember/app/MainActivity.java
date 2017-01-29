package com.omjoonkim.doyouremember.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.omjoonkim.doyouremember.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	@BindView(R.id.toolbar)
	Toolbar toolbar;

	@BindView(R.id.viewpager)
	ViewPager viewPager;

	@BindView(R.id.tabs_main)
	TabLayout tabLayout;


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);

	}
}
