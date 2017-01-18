package com.omjoonkim.doyouremember.app.home.receivemoney;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReceiveMoneyFragment extends Fragment {

    @BindView(R.id.recycler_home_receive)
    RecyclerView recyclerView;

    private HomeReceiveAdapter adapter;

    public static ReceiveMoneyFragment newInstance() {
        return new ReceiveMoneyFragment();
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

        HomeReceiveParentData parent1 = new HomeReceiveParentData("Mash-Up 신년파티DDDDDDDDDDD", "정민", Arrays.asList(child1, child2, child3));
        final List<HomeReceiveParentData> parentDatas = Arrays.asList(parent1);

        adapter = new HomeReceiveAdapter(getActivity().getApplicationContext(), parentDatas);
        adapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onParentExpanded(int parentPosition) {
                Toast.makeText(getActivity(), "expanded", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onParentCollapsed(int parentPosition) {
                Toast.makeText(getActivity(), "collapsed", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }
}
