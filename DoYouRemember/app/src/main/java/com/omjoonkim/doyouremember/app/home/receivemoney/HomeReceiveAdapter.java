package com.omjoonkim.doyouremember.app.home.receivemoney;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.receivemoney.listener.OnHomeReceiveClickListener;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.util.List;


public class HomeReceiveAdapter extends ExpandableRecyclerAdapter
        <HomeReceiveParentData, HomeReceiveChildData,
                ReceiveMoneyParentViewHolder, ReceiveMoneyChildViewHolder> {

    public static final String TAG = HomeReceiveAdapter.class.getSimpleName();
    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    private OnHomeReceiveClickListener listener;
    private Context mContext;
    private List<HomeReceiveParentData> homeReceiveParentDatas;

    public HomeReceiveAdapter(Context context, @NonNull List<HomeReceiveParentData> parentList) {
        super(parentList);
        this.mContext = context;
        this.homeReceiveParentDatas = parentList;
    }

    public void setClickListener(OnHomeReceiveClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReceiveMoneyParentViewHolder onCreateParentViewHolder(@NonNull ViewGroup parentViewGroup, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_receive_parent, parentViewGroup, false);
        return new ReceiveMoneyParentViewHolder(v);
    }

    @NonNull
    @Override
    public ReceiveMoneyChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_receive_child, childViewGroup, false);
        return new ReceiveMoneyChildViewHolder(v);
    }

    @Override
    public void onBindParentViewHolder(@NonNull ReceiveMoneyParentViewHolder parentViewHolder, int parentPosition, @NonNull HomeReceiveParentData parent) {
        parentViewHolder.updateData(parent);
    }

    @Override
    public void onBindChildViewHolder(@NonNull ReceiveMoneyChildViewHolder childViewHolder, int parentPosition, int childPosition, @NonNull HomeReceiveChildData child) {
        childViewHolder.updateData(child);
        childViewHolder.occurEvent(child, homeReceiveParentDatas, parentPosition, listener);
    }

}
