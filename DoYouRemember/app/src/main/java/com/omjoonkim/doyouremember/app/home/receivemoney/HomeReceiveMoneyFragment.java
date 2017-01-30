package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.presenter.HomeReceivePresenter;
import com.omjoonkim.doyouremember.app.home.receivemoney.presenter.HomeReceivePresenterImpl;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeReceiveMoneyFragment extends Fragment implements HomeReceivePresenter.View {

    public static final String TAG = HomeReceiveMoneyFragment.class.getSimpleName();

    @BindView(R.id.recycler_home_receive)
    RecyclerView recyclerView;

    private HomeReceiveAdapter adapter;
    private HomeReceivePresenter homeReceivePresenter;

    public static HomeReceiveMoneyFragment newInstance() {
        return new HomeReceiveMoneyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeReceivePresenter = new HomeReceivePresenterImpl();
        homeReceivePresenter.setView(this);
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

        HomeReceiveChildData child1 = new HomeReceiveChildData("문소윤", 8000);
        HomeReceiveChildData child2 = new HomeReceiveChildData("신선아", 15000);
        HomeReceiveChildData child3 = new HomeReceiveChildData("박은명", 20000);

        HomeReceiveParentData parent1 = new HomeReceiveParentData("Mash-Up 신년파티DDDDDDDDDDD", Arrays.asList(child1, child2, child3));
        HomeReceiveParentData parent2 = new HomeReceiveParentData("내일도 출근이다....ㅠㅠㅠ", Arrays.asList(child1, child2, child3));

        final List<HomeReceiveParentData> parentDatas = Arrays.asList(parent1, parent2);

        adapter = new HomeReceiveAdapter(parentDatas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void updateDebtorCount(final int parentPosition) {
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                adapter.notifyParentChanged(parentPosition);
//            }
//        });
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
}
