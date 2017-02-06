package com.omjoonkim.doyouremember.app.myaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.myaccount.MyAccountPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class MyAccountAdapter extends RecyclerSwipeAdapter<RecyclerView.ViewHolder> {
    Context context;


    private List<ItemView> items;
    private MyAccountPresenter presenter;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public List<ItemView> getItems() {
        return items;
    }

    public void setItems(List<ItemView> items) {
        this.items = items;
    }

    public MyAccountAdapter(MyAccountPresenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (viewType == TYPE_HEADER) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_my_account, parent, false);
            return new HeaderViewHolder(itemView);
        } else if (viewType == TYPE_ITEM) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_account, parent, false);
            return new ItemViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {

        if (viewHolder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;

            headerViewHolder.txtMainMyAccount.setText(presenter.setMainAccountBox());

        } else if (viewHolder instanceof ItemViewHolder) {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
            itemViewHolder.imgBookmarkStar.setImageResource(items.get(position - 1).getBookmarkStar());
            itemViewHolder.txtMyAccountNumber.setText(items.get(position - 1).getMyAccountNumber());

            itemViewHolder.swipeLayout.getSurfaceView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemManger.closeAllItems();
                    Toast.makeText(context, position+"", Toast.LENGTH_SHORT).show();
                }
            });

            //Todo 아래의 리스너들 버터나이프로못하나 궁금합니다. + view단(액티비티,프레그먼트)로 빼는방법 알기
            itemViewHolder.imgCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    presenter.
                }
            });

            itemViewHolder.imgReviseMyAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.swipeRevise(position - 1);
                }
            });

            itemViewHolder.imgDeleteMyAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.swipeDelete(position - 1);
                }
            });

            itemViewHolder.imgBookmarkStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    presenter.onClickFavoriteMyAccount(position, itemViewHolder.txtMyAccountNumber.getText().toString().substring(3));
                }
            });

            mItemManger.bindView(viewHolder.itemView, position);

        }
    }

    @Override
    public int getItemCount() {
        return items.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_my_account;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_bookmark_star)
        ImageView imgBookmarkStar;
        @BindView(R.id.text_view_my_account_number)
        TextView txtMyAccountNumber;

        @BindView(R.id.swipe_my_account)
        SwipeLayout swipeLayout;
        @BindView(R.id.image_view_copy_my_account)
        ImageView imgCopy;
        @BindView(R.id.image_view_revise_my_account)
        ImageView imgReviseMyAccount;
        @BindView(R.id.image_view_delete_my_account)
        ImageView imgDeleteMyAccount;


        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //Todo headerView 액티비티로 뺄수 있나 찾아보기.
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        //Todo view단에서 관리하는 방법 찾기.
        @BindView(R.id.text_view_main_account_box)
        TextView txtMainMyAccount;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
