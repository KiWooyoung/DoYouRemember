package com.omjoonkim.doyouremember.app.home.sendmoney;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.ArrayList;
import java.util.List;

public class HomeSendAdapter extends RecyclerSwipeAdapter<HomeSendViewHolder>{

    public static final String TAG = HomeSendAdapter.class.getSimpleName();

    private OnHomeSendClickListener listener;
    private Context mContext;
    private List<HomeSendData> mDataset;

    public HomeSendAdapter(Context context){
        this.mContext = context;
        this.mDataset = new ArrayList<>();
        setData();
    }

    public void setClickListener(OnHomeSendClickListener listener) {
        this.listener = listener;
    }

    public void setData(){
        mDataset.clear();
        mDataset.add(new HomeSendData("강남 안드팀 회식", "기획팀 문소윤", "시티 11102327408293", 12000, 0));
        mDataset.add(new HomeSendData("디자인팀 회식", "신선아", "우리 11102327408293", 109000, 1));
        mDataset.add(new HomeSendData("12월3일 사당역 와우카페", "디자인팀 박은명", "신한 11102327408293", 8800, 2));
        mDataset.add(new HomeSendData("인사동 점심", "김정민", "국민 11102327408293", 27000, 3));
        mDataset.add(new HomeSendData("교촌치킨 회식", "안드팀 김범준", "신한 11102327408293", 9000, 3));
        mDataset.add(new HomeSendData("커피스미스 스터디", "기우영", "하나 11102327408293", 4300, 3));
    }

    @Override
    public HomeSendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_home_send, parent, false);
        return new HomeSendViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final HomeSendViewHolder holder, final int position) {
        holder.updateData(mDataset.get(position));
        holder.occurEvent(mDataset, position, listener, mItemManger);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_home_send_item;
    }

}
