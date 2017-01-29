package com.omjoonkim.doyouremember.app.myaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.myaccount.MyAccountPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class MyAccountAdapter extends RecyclerSwipeAdapter<MyAccountAdapter.ViewHolder> {
    Context context;
    private List<ItemView> items;
    private MyAccountPresenter presenter;

    public MyAccountAdapter(List<ItemView> items, MyAccountPresenter presenter) {
        this.items = items;
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_account, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.imgBookmarkStar.setImageResource(items.get(position).getBookmarkStar());
        viewHolder.txtMyAccountNumber.setText(items.get(position).getMyAccountNumber());

        //Todo 아래의 리스너들 버터나이프로못하나 궁금합니다.
        viewHolder.imgShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "haha", Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.imgReviseMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.imgDeleteMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mItemManger.bindView(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_bookmark_star)
        ImageView imgBookmarkStar;
        @BindView(R.id.text_view_my_account_number)
        TextView txtMyAccountNumber;

        @BindView(R.id.image_view_share_my_account)
        ImageView imgShare;
        @BindView(R.id.image_view_revise_my_account)
        ImageView imgReviseMyAccount;
        @BindView(R.id.image_view_delete_my_account)
        ImageView imgDeleteMyAccount;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public static class ItemView {
        private String myAccountNumber;
        private int bookmarkStar;

        public ItemView(String myAccountNumber, int bookmarkStar) {
            this.myAccountNumber = myAccountNumber;
            this.bookmarkStar = bookmarkStar;
        }

        public String getMyAccountNumber() {
            return myAccountNumber;
        }

        public void setMyAccountNumber(String myAccountNumber) {
            this.myAccountNumber = myAccountNumber;
        }

        public int getBookmarkStar() {
            return bookmarkStar;
        }

        public void setBookmarkStar(int bookmarkStar) {
            this.bookmarkStar = bookmarkStar;
        }
    }
}
