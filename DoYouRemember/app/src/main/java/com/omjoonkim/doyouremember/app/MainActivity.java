package com.omjoonkim.doyouremember.app;


import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.HomeFragment;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.lang.reflect.Field;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private SearchView searchView;
    private MenuItem menuItem;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.tabs_main)
    TabLayout tabLayout;

    @BindView(R.id.toolbar_title)
    TextView txtToolbarTitle;

    MainFragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

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


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1) {
                    searchView.setVisibility(View.VISIBLE);
                    menuItem.setVisible(true);
                } else {
//                    searchView.setVisibility(View.INVISIBLE);
                    menuItem.setVisible(false);
                    searchView.setVisibility(GONE);
                    searchView.clearAnimation();
//                    Todo 서치뷰 나온 상태에서 프래그먼트 변경하면 꺼지지 않는데 꺼지는거로 만들기.
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

        Typeface typeFace1 = Typeface.createFromAsset(getAssets(), "야놀자 야체 OTF Regular.otf");
        txtToolbarTitle.setTypeface(typeFace1);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_search_view, menu);
//
//        menuItem = menu.findItem(R.id.action_search);
//        menuItem.setVisible(false);
//        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        searchView.setQueryHint(getString(R.string.text_view_hint));
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                System.out.println(s);
//                return false;
//            }
//        });
//        return true;
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search_view, menu);
//        menuItem = menu.findItem(R.id.action_search);
//        menuItem.setVisible(false);
//        searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("계좌를 검색하세요.");
//
//        int searchPlateId = searchView.getContext().getResources().getIdentifier("android:id/search_plate", null, null);
//        View searchPlate = searchView.findViewById(searchPlateId);
//        if (searchPlate != null) {
//            searchPlate.setBackgroundColor(Color.DKGRAY);
//            int searchTextId = searchPlate.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
//            TextView searchText = (TextView) searchPlate.findViewById(searchTextId);
////            if (searchText != null) {
//                searchText.setTextColor(Color.WHITE);
//                searchText.setHintTextColor(Color.WHITE);
////            }
//        }
//        return true;
//    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }


//Todo 1.삭제완료,복사완료를 프레그먼트가 아닌 부모액티비티에서 호출 가능 여부 확인하기.

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search_view, menu);
//
//        menuItem = menu.findItem(R.id.action_search);
//        menuItem.setVisible(false);
//        searchView = (SearchView) menuItem.getActionView();
//        int searchImgId = getResources().getIdentifier("android:id/search_button", null, null);
//        ImageView v = (ImageView) searchView.findViewById(searchImgId);
//        if(v!=null) {
//            Log.i("MyTag","asd");
//            v.setImageResource(R.drawable.search_icon_white);
//        }
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//        return super.onPrepareOptionsMenu(menu);
//    }

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        //getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_search_view, menu);
//
//        menuItem = menu.findItem(R.id.action_search);
//        menuItem.setVisible(false);

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu_search_view, menu);

        menuItem = menu.findItem(R.id.action_search);
        //        searchView = (SearchView) menuItem.getActionView();
//        menuItem.setIcon(R.drawable.back_icon);
//        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_icon);
        menuItem.setVisible(false);
        // Get the SearchView and set the searchable configuration

        SearchManager searchManager = (SearchManager) getSystemService(MainActivity.this.SEARCH_SERVICE);

        //MenuItem searchItem=menu.findItem(R.id.searchitem);

        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView = (SearchView) menuItem.getActionView();

//        searchView.setBackgroundColor(Color.parseColor("#086A87"));

        // Sets the SearchableInfo for this SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        //expand the search widget

        searchView.setIconifiedByDefault(false);
//        searchView.setMaxWidth(100);
//        searchView.setBaselineAligned(true);
//        searchView.setKeepScreenOn(false);
//        searchView
//        searchView.
        // Show the submit button
        searchView.setQueryHint(getString(R.string.search_view_hint));
//        searchView.setSubmitButtonEnabled(true);
        searchView.setKeepScreenOn(true);

        ImageView searchClose = (ImageView) searchView.findViewById (android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setKeepScreenOn(true);
        searchClose.setVisibility(View.VISIBLE);
//change color
        searchClose.setColorFilter (Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
// change image
        searchClose.setImageResource(R.drawable.x_button);
//
        ImageView searchSubmit = (ImageView) searchView.findViewById (android.support.v7.appcompat.R.id.search_mag_icon);
// change color
        searchSubmit.setColorFilter (Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
// change image
        searchSubmit.setImageResource(R.drawable.search_icon_2);
//ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
        EditText searchBox=((EditText) searchView.findViewById (android.support.v7.appcompat.R.id.search_src_text));
        searchBox.setSingleLine();
        searchBox.setHintTextColor(Color.parseColor("#c8c8c8"));
        searchBox.setLines(1);
        searchBox.setTextSize(18);
        searchBox.setTextColor(Color.parseColor("#FFFFFF"));
        try {
            Field cursor = TextView.class.getDeclaredField("cursor");
            cursor.setAccessible(true);
            cursor.set(searchBox, R.drawable.cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        View plateView = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        plateView.setBackgroundColor(Color.WHITE);
////        If you do not use the support library, use this code instead.
//
        ImageView searchButton = (ImageView) searchView.findViewById (android.support.v7.appcompat.R.id.action_mode_close_button);
// change color
//        searchButton.setColorFilter (Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
// change image
        if(searchButton != null) {
            searchButton.setImageResource(R.drawable.back_icon);
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
