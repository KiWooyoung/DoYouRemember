package com.omjoonkim.doyouremember.app.frequentlyusedaccount;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount.RegisterFrequentlyUsedAccountActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by owner on 2017-01-13.
 */

public class FrequentlyUsedAccountFragment extends Fragment implements com.omjoonkim.doyouremember.app.frequentlyusedaccount.View {

    @BindView(R.id.recycler_view_FUAccount)
    RecyclerView recyclerView;
    @BindView(R.id.image_view_background)
    ImageView imgBackground;
    @BindView(R.id.text_view_default_text)
    TextView txtDefaultText;
    @OnClick(R.id.fab_writing_frequently_used_account)
    public void onClick(){
        presenter.onAddAccount();
    }

    LinearLayoutManager layoutManager = null;
    FrequentlyUesdAccountAdapter adapter = null;
    PresenterImpl presenter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frequently_used_account, container, false);
        ButterKnife.bind(this, view);

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        presenter = new PresenterImpl(this);
        adapter = new FrequentlyUesdAccountAdapter(presenter);

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        presenter.setModel();

        return view;
    }

    @Override
    public void notifyItemRemoved(int position) {

    }

    @Override
    public void notifyItemInserted(int position) {

    }

    @Override
    public void notifyItemChanged(List<FrequentlyUesdAccountAdapter.ItemView> data) {
        adapter.setItems(data);
        adapter.notifyDataSetChanged();
        showBackgroundImg();
    }

    @Override
    public void showToast() {
        Toast.makeText(getContext(), "TEST성공", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showBackgroundImg() {
        if ( adapter.getItems().size() != 0 ) {
            imgBackground.setVisibility(View.INVISIBLE);
            txtDefaultText.setVisibility(View.INVISIBLE);
        }
        else {
            imgBackground.setVisibility(View.VISIBLE);
            txtDefaultText.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void addAccount() {
        startActivity(new Intent(getActivity(),RegisterFrequentlyUsedAccountActivity.class));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
