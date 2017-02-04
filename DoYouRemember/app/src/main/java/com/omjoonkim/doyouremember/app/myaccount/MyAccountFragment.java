package com.omjoonkim.doyouremember.app.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.util.Attributes;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.myaccount.adapter.MyAccountAdapter;
import com.omjoonkim.doyouremember.app.myaccount.registermyaccount.RegisterMyAccountActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by owner on 2017-01-13.
 */

public class MyAccountFragment extends Fragment implements MyAccountView{

    MyAccountPresenter presenter = null;
    MyAccountAdapter adapter = null;
    LinearLayoutManager layoutManager = null;
    List<MyAccountAdapter.ItemView> testItem = new ArrayList<>();
    @BindView(R.id.recycler_view_my_accounts)
    RecyclerView recyclerView;
    @BindView(R.id.text_view_my_account_box)
    TextView txtMyAccountBox;
    @BindView(R.id.card_view_account_box)
    CardView cardViewAccountBox;
    @OnClick(R.id.fab_writing_my_account)
    void OnClick(){
        presenter.onAddMyAccount();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_account, container, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        presenter = new MyAccountPresenterImpl(this);
        adapter = new MyAccountAdapter(testItem,presenter);

        recyclerView.setAdapter(adapter);

        adapter.setMode(Attributes.Mode.Single);

//        testItem.add(new MyAccountAdapter.ItemView("우리",R.drawable.bookmark_blank_star));
//        testItem.add(new MyAccountAdapter.ItemView("국민",R.drawable.bookmark_blank_star));
//        testItem.add(new MyAccountAdapter.ItemView("신한",R.drawable.bookmark_blank_star));
//        testItem.add(new MyAccountAdapter.ItemView("농협",R.drawable.bookmark_blank_star));
//        testItem.add(new MyAccountAdapter.ItemView("기업",R.drawable.bookmark_blank_star));
        showDefaultText();
        return view;
    }


    @Override
    public void goRegisterMyAccount() {
        startActivity(new Intent(getContext(), RegisterMyAccountActivity.class));
    }

    @Override
    public void showDefaultText() {
        if (adapter.getItems().size() != 0) {
            cardViewAccountBox.setVisibility(View.INVISIBLE);
            txtMyAccountBox.setVisibility(View.INVISIBLE);
        } else {
            cardViewAccountBox.setVisibility(View.VISIBLE);
            txtMyAccountBox.setVisibility(View.VISIBLE);
        }
    }
}
