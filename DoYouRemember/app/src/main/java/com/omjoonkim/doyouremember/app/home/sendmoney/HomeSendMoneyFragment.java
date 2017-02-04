package com.omjoonkim.doyouremember.app.home.sendmoney;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.app.home.sendmoney.presenter.HomeSendPresenter;
import com.omjoonkim.doyouremember.app.home.sendmoney.presenter.HomeSendPresenterImpl;
import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeSendMoneyFragment extends Fragment implements HomeSendPresenter.View {

    @BindView(R.id.recycler_home_send)
    RecyclerView recyclerView;

    @BindDimen(R.dimen.send_list_bottom_margin)
    int spacingBottomSize;

    @BindDimen(R.dimen.send_list_first_margin)
    int spacingFirstSize;

    private HomeSendAdapter adapter;
    private HomeSendPresenter homeSendPresenter;

    public static HomeSendMoneyFragment newInstance() {
        return new HomeSendMoneyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeSendPresenter = new HomeSendPresenterImpl();
        homeSendPresenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_money, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerViewInit();
    }

    private void initRecyclerViewInit() {
        adapter = new HomeSendAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HomeSendItemDecoration(spacingBottomSize, spacingFirstSize));
        adapter.setClickListener(new OnHomeSendClickListener() {
            @Override
            public void onClickSendCopy(HomeSendData homeSendData) {
                homeSendPresenter.onClickCopy(homeSendData);
            }

            @Override
            public void onClickSendEdit() {
                homeSendPresenter.onClickEdit();
            }

            @Override
            public void onClickSendDelete(List<HomeSendData> homeSendDataList, int position) {
                homeSendPresenter.onClickDelete(homeSendDataList, position);
            }
        });
    }

    @Override
    public void copyHomeSendAccount(HomeSendData homeSendData) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", homeSendData.getTitle());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity().getApplicationContext(), "Copy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteHomeSendItem(List<HomeSendData> homeSendDataList, int position) {
        homeSendDataList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, homeSendDataList.size());
        Toast.makeText(getActivity().getApplicationContext(), "Deleted" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editHomeSendItem() {

    }
}
