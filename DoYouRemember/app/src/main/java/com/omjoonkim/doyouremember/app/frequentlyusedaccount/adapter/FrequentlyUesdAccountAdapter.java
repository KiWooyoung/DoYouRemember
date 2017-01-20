package com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.Presenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by owner on 2017-01-17.
 */

public class FrequentlyUesdAccountAdapter extends RecyclerView.Adapter<FrequentlyUesdAccountAdapter.ViewHolder> {

    private List<ItemView> items;
    private Presenter presenter;

    public List<ItemView> getItems() {
        return items;
    }

    public void setItems(List<ItemView> items) {
        this.items = items;
    }

    public FrequentlyUesdAccountAdapter(Presenter presenter) {
        this.items = new ArrayList<>();
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frequently_used_account, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtAccountHolder.setText(items.get(position).getAccountHolder());
        holder.txtAccountInfo.setText(items.get(position).getAccountInfo());
        holder.imgProfileImage.setImageResource(items.get(position).getProfileImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_text_view_account_holder)
        TextView txtAccountHolder;
        @BindView(R.id.item_text_view_account_info)
        TextView txtAccountInfo;
        @BindView(R.id.item_image_view_profile_image)
        ImageView imgProfileImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

     public static class ItemView {

        private String accountHolder;
        private String accountInfo;
        private int profileImage;

        public ItemView(String accountHolder, String accountInfo, int profileImage) {
            this.accountHolder = accountHolder;
            this.accountInfo = accountInfo;
            this.profileImage = profileImage;
        }

        public String getAccountHolder() {
            return accountHolder;
        }

        public void setAccountHolder(String accountHolder) {
            this.accountHolder = accountHolder;
        }

        public String getAccountInfo() {
            return accountInfo;
        }

        public void setAccountInfo(String accountInfo) {
            this.accountInfo = accountInfo;
        }

        public int getProfileImage() {
            return profileImage;
        }

        public void setProfileImage(int profileImage) {
            this.profileImage = profileImage;
        }
    }
}
