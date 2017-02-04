package com.omjoonkim.doyouremember.app.home.receivemoney;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;


public class HomeReceiveAdapter extends CheckableChildRecyclerViewAdapter
        <HomeReceiveMoneyParentViewHolder, HomeReceiveMoneyChildViewHolder> {

    public static final String TAG = HomeReceiveAdapter.class.getSimpleName();

    public HomeReceiveAdapter(List<HomeReceiveParentData> groups) {
        super(groups);
    }

    @Override
    public HomeReceiveMoneyChildViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_receive_child, parent, false);
        return new HomeReceiveMoneyChildViewHolder(view);
    }

    @Override
    public void onBindCheckChildViewHolder(HomeReceiveMoneyChildViewHolder holder, int flatPosition, CheckedExpandableGroup group, int childIndex) {
        final HomeReceiveChildData childData = (HomeReceiveChildData) group.getItems().get(childIndex);
        holder.bind(childData);
    }

    @Override
    public HomeReceiveMoneyParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_receive_parent, parent, false);
        return new HomeReceiveMoneyParentViewHolder(view);
    }

    @Override
    public void onBindGroupViewHolder(HomeReceiveMoneyParentViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.bind((HomeReceiveParentData) group, flatPosition);
    }
}
