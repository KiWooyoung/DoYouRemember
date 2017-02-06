package com.omjoonkim.doyouremember.app.myaccount;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

    @BindView(R.id.recycler_view_my_accounts)
    RecyclerView recyclerView;
    @BindView(R.id.text_view_my_account_box)
    TextView txtMyAccountBox;
    @BindView(R.id.card_view_account_box)
    CardView cardViewAccountBox;
    @BindView(R.id.fab_writing_my_account)
    FloatingActionButton fab;
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
        adapter = new MyAccountAdapter(presenter,getContext());

        recyclerView.setAdapter(adapter);

        adapter.setMode(Attributes.Mode.Single);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                adapter.mItemManger.closeAllItems();
                adapter.closeAllItems();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.setModel();
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
            fab.setImageResource(R.drawable.add_button);
        } else {
            cardViewAccountBox.setVisibility(View.VISIBLE);
            txtMyAccountBox.setVisibility(View.VISIBLE);
            fab.setImageResource(R.drawable.write_button);
        }
    }

    @Override
    public void notifyItemsChanged(List<MyAccountAdapter.ItemView> items) {
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
        showDefaultText();
    }

    @Override
    public void notifyChangeFavoriteMyAccount(int position) {
        adapter.notifyItemChanged(position);
    }

    @Override
    public void goRevise(int position) {
        Intent intent = new Intent(getContext(), RegisterMyAccountActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("bankType", adapter.getItems().get(position).getMyAccountNumber().substring(0,2));
        intent.putExtra("accountNumber", adapter.getItems().get(position).getMyAccountNumber().substring(3));
        startActivity(intent);
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
    }

    @Override
    public void showDeleteDialog(int position) {
        //Todo 3.delete 다이얼로그 생성하기
        presenter.deleteMyAccount(position, adapter.getItems().get(position).getMyAccountNumber().substring(3));
    }

    @Override
    public void notifyItemRemoved(int position) {
//        adapter.mItemManger.removeShownLayouts(swipeLayout);
        adapter.getItems().remove(position);
        adapter.notifyItemRemoved(position + 1);
        adapter.notifyItemRangeChanged(position + 1,adapter.getItems().size());
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
        showDefaultText();
    }
}
