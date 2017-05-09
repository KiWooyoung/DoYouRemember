package com.omjoonkim.doyouremember.app.home.sendmoney;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.realm.entitiy.SendMoneyRealmObject;

import java.util.ArrayList;
import java.util.List;

public class HomeSendAdapter extends RecyclerSwipeAdapter<HomeSendViewHolder>{

    public static final String TAG = HomeSendAdapter.class.getSimpleName();

    private OnHomeSendClickListener listener;
    private Context mContext;
    private List<SendMoneyRealmObject> sendMoneyRealmObjectList = null;

    public HomeSendAdapter(Context context){
        this.mContext = context;
    }

    public void setClickListener(OnHomeSendClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<SendMoneyRealmObject> sendMoneyRealmObjectList){
        this.sendMoneyRealmObjectList = sendMoneyRealmObjectList;
    }

    @Override
    public HomeSendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_send, parent, false);
        return new HomeSendViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HomeSendViewHolder holder, final int position) {
        if (sendMoneyRealmObjectList != null) {
            holder.bind(sendMoneyRealmObjectList.get( position ), position, listener, mItemManger);
        }
    }

    @Override
    public int getItemCount() {
        if (sendMoneyRealmObjectList == null) {
            return 0;
        }
        return sendMoneyRealmObjectList.size();
    }

    public void notifyDataSetChangedAndSwipeClear(){
        notifyDataSetChanged();
        mItemManger.closeAllItems();
    }



    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_home_send_item;
    }

}
