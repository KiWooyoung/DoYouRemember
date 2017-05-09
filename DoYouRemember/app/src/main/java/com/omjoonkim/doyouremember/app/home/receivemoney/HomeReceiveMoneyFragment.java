package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.dialog.SelectMessageDialog;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.app.home.receivemoney.presenter.HomeReceivePresenter;
import com.omjoonkim.doyouremember.app.home.receivemoney.presenter.HomeReceivePresenterImpl;
import com.omjoonkim.doyouremember.app.writing.receivemoney.WritingReceiveActivity;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;
import com.omjoonkim.doyouremember.realm.entitiy.ReceiveMoneyRealmObject;
import com.thoughtbot.expandablecheckrecyclerview.listeners.OnCheckChildClickListener;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;


public class HomeReceiveMoneyFragment extends Fragment implements HomeReceivePresenter.View {

    public static final String TAG = HomeReceiveMoneyFragment.class.getSimpleName();

    @BindView(R.id.recycler_home_receive)
    RecyclerView recyclerView;

    private HomeReceiveAdapter adapter;
    private HomeReceivePresenter homeReceivePresenter;
    private Realm realm;
    RealmResults<ReceiveMoneyRealmObject> receiveMoneyRealmResults;

    @OnClick(R.id.fab_receive_writing)
    void onFabReceiveClick(View view){
        homeReceivePresenter.onClickFab();
    }

    public static HomeReceiveMoneyFragment newInstance() {
        return new HomeReceiveMoneyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeReceivePresenter = new HomeReceivePresenterImpl();
        homeReceivePresenter.setView(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_receive_money, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerViewInit();
    }

    private void initRecyclerViewInit() {

        receiveMoneyRealmResults = realm.where(ReceiveMoneyRealmObject.class).findAll();
        List<HomeReceiveParentData> parentDatas = new ArrayList<HomeReceiveParentData>();

        for(int i=0; i<receiveMoneyRealmResults.size(); i++){
            List<HomeReceiveChildData> childDatas = new ArrayList<HomeReceiveChildData>();
            for (int j=0; j<receiveMoneyRealmResults.get(i).getDebtorList().size(); j++){
                childDatas.add(new HomeReceiveChildData(
                        receiveMoneyRealmResults.get(i).getDebtorList().get(j).getName(),
                        receiveMoneyRealmResults.get(i).getDebtorList().get(j).getPrice()));
            }
            parentDatas.add(new HomeReceiveParentData(receiveMoneyRealmResults.get(i).getTitle(),childDatas));
        }

        adapter = new HomeReceiveAdapter(parentDatas);
        adapter.setChildClickListener(new OnCheckChildClickListener() {
            @Override
            public void onCheckChildCLick(View v, boolean checked, CheckedExpandableGroup group, int childIndex) {
                // 부모 포지션 가져오기
                int index = adapter.getGroups().indexOf(group);
                adapter.notifyItemChanged(index);
            }
        });

        adapter.setClickListener(new OnHomeReceiveClickListener() {

            @Override
            public void onSendKakaoLink(String receivePrice) {
                homeReceivePresenter.onClickKakaoLink(receivePrice);
            }

            @Override
            public void onCheckedDebtors(int parentPosition) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void loadHomeReceiveWritingActivity() {
        startActivity(new Intent(this.getContext(), WritingReceiveActivity.class));
    }

    @Override
    public void updateDebtorCount(final int parentPosition) {

    }

    @Override
    public void sendKaKaoLink(String receivePrice) {
        SelectMessageDialog selectMessageDialog = SelectMessageDialog.newDialogInstance(receivePrice);
        selectMessageDialog.show(getFragmentManager(), "message select dialog");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
