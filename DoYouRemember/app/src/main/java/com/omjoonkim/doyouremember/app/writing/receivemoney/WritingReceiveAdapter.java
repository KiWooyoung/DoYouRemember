package com.omjoonkim.doyouremember.app.writing.receivemoney;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.writing.receivemoney.listener.OnWritingReceiveClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WritingReceiveAdapter extends RecyclerView.Adapter<WritingReceiveHolder> {

    public static final String TAG = WritingReceiveAdapter.class.getSimpleName();

    private Context mContext;
    private OnWritingReceiveClickListener listener;
    private List<String> mItems;

    public WritingReceiveAdapter(Context context){
        this.mContext = context;
        mItems = new ArrayList<String>();
        initData();
    }

    public void initData(){
        mItems.clear();
        mItems.add("보낼사람");
    }

    public void addData() {
        mItems.add("보낼사람");
        notifyDataSetChanged();
    }

    public void deleteData(int position) {
        if (mItems.size() == 1){
            Toast.makeText(mContext, "적어도 한 사람이상 있어야합니다.", Toast.LENGTH_SHORT).show();
        }else{
            mItems.remove(position);
            notifyItemRemoved(position);
            notifyDataSetChanged();
        }
    }


    public void setClickListener(OnWritingReceiveClickListener listener) {
        this.listener = listener;
    }

    @Override
    public WritingReceiveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_writing_receive, parent, false);
        return new WritingReceiveHolder(v);
    }

    @Override
    public void onBindViewHolder(WritingReceiveHolder holder, int position) {
        if (mItems != null) {
            holder.bind(mItems.get( position ), listener, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}
