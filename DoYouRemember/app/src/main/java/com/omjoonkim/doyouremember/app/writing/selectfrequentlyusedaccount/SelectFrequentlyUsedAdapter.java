package com.omjoonkim.doyouremember.app.writing.selectfrequentlyusedaccount;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;
import com.omjoonkim.doyouremember.app.writing.selectfrequentlyusedaccount.listener.OnItemClickListener;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectFrequentlyUsedAdapter extends RecyclerView.Adapter<SelectFrequentlyUsedAdapter.SelectFrequentlyUsedViewHolder> {

    private Context mContext;
    private OnItemClickListener clickListener;
    private List<PersonRealmObject> items;

    public SelectFrequentlyUsedAdapter(Context context, OnItemClickListener listener) {
        this.mContext = context;
        this.clickListener = listener;
    }

    public void setData(List<PersonRealmObject> items){
        this.items = items;
    }

    @Override
    public SelectFrequentlyUsedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_select_frequent_user, parent, false);
        return new SelectFrequentlyUsedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SelectFrequentlyUsedViewHolder holder, int position) {
        holder.txtAccountHolder.setText(items.get(position).getName());
        String accountInfo = items.get(position).getAccountList().get(0).getBankType()
                + " "
                + items.get(position).getAccountList().get(0).getAccountNumber();
        holder.txtAccountInfo.setText(accountInfo);
        holder.imgProfileImage.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class SelectFrequentlyUsedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.item_text_view_account_holder)
        TextView txtAccountHolder;
        @BindView(R.id.item_text_view_account_info)
        TextView txtAccountInfo;
        @BindView(R.id.item_image_view_profile_image)
        ImageView imgProfileImage;

        public SelectFrequentlyUsedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition());
        }
    }
}
