package com.omjoonkim.doyouremember;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

	@ViewById(R.id.toolbar)
	Toolbar toolbar;

	@ViewById(R.id.viewpager)
	ViewPager viewPager;

	@ViewById(R.id.tabs_main)
	TabLayout tabLayout;

	@Click(R.id.fab_writing)
	void onClickFab(){
		Toast.makeText(MainActivity.this, "floatingbutton click", Toast.LENGTH_SHORT).show();
	}

	@AfterViews
	protected void initialize(){
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		viewPager.setAdapter(new MainFragmentPagerAdapter(getSupportFragmentManager()));
		tabLayout.setupWithViewPager(viewPager);
	}

}
