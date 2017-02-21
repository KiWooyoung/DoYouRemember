package com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.FrequentlyUsedAccountPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by owner on 2017-01-17.
 */

public class FrequentlyUesdAccountAdapter extends RecyclerSwipeAdapter<FrequentlyUesdAccountAdapter.ViewHolder> {
    Context context;
    private List<ItemView> items;
    private FrequentlyUsedAccountPresenter frequentlyUsedAccountPresenter;

    public List<ItemView> getItems() {
        return items;
    }

    public void setItems(List<ItemView> items) {
        this.items = items;
    }

    public void removeItem(int position) {
        items.remove(position);
    }

    public FrequentlyUesdAccountAdapter(FrequentlyUsedAccountPresenter frequentlyUsedAccountPresenter, Context context) {
        this.items = new ArrayList<>();
        this.frequentlyUsedAccountPresenter = frequentlyUsedAccountPresenter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_frequently_used_account, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtAccountHolder.setText(items.get(position).getAccountHolder());
        holder.txtAccountInfo.setText(items.get(position).getAccountInfo());
        holder.imgProfileImage.setImageResource(items.get(position).getProfileImage());

        holder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, " onClick : " + position + items.get(position).getAccountInfo(), Toast.LENGTH_SHORT).show();
                mItemManger.closeAllItems();
            }
        });

        //Todo 아래의 리스너들 버터나이프로못하나 궁금합니다.
        holder.imgAccountCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequentlyUsedAccountPresenter.swipeWriteList(position);
            }
        });

        holder.imgRevise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequentlyUsedAccountPresenter.swipeRevise(position);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequentlyUsedAccountPresenter.swipeDelete(position);
            }
        });

        mItemManger.bindView(holder.itemView, position);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    /** 중요 */
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.swipe)
        SwipeLayout swipeLayout;
        @BindView(R.id.image_view_account_copy)
        ImageView imgAccountCopy;
        @BindView(R.id.image_view_revise)
        ImageView imgRevise;
        @BindView(R.id.image_view_delete)
        ImageView imgDelete;

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
        private long id;

        public ItemView(long id, String accountHolder, String accountInfo, int profileImage) {
            this.id = id;
            this.accountHolder = accountHolder;
            this.accountInfo = accountInfo;
            this.profileImage = profileImage;
        }

        public long getId() {
            return id;
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
