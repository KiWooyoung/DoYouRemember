package com.omjoonkim.doyouremember.app.frequentlyusedaccount;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog.DeleteMessageDialog;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount.RegisterFrequentlyUsedAccountActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by owner on 2017-01-13.
 */

public class FrequentlyUsedAccountFragment extends Fragment implements com.omjoonkim.doyouremember.app.frequentlyusedaccount.FrequentlyUsedAccountView, DeleteMessageDialog.DeleteMessageListener {

    private int position  = 3;

    SwipeLayout swipeLayout;
    @BindView(R.id.recycler_view_FUAccount)
    RecyclerView recyclerView;
    @BindView(R.id.image_view_background)
    ImageView imgBackground;
    @BindView(R.id.text_view_default_text)
    TextView txtDefaultText;
    @BindView(R.id.image_view_delete_complete)
    ImageView imgDeleteComplete;

    @BindView(R.id.fab_writing_frequently_used_account)
    FloatingActionButton fab;

    @OnClick(R.id.fab_writing_frequently_used_account)
    public void onClick() {
        presenter.onAddFrequentlyUsedAccount();
    }

    LinearLayoutManager layoutManager = null;
    FrequentlyUesdAccountAdapter adapter = null;
    FrequentlyUsedAccountPresenterImpl presenter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frequently_used_account, container, false);
        ButterKnife.bind(this, view);
        swipeLayout = (SwipeLayout) view.findViewById(R.id.swipe);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        presenter = new FrequentlyUsedAccountPresenterImpl(this);
        adapter = new FrequentlyUesdAccountAdapter(presenter, getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        "김범준".compareTo("기우영");
        adapter.setMode(Attributes.Mode.Single);
        presenter.setModel();
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.closeAllItems();
    }

                //Todo 추가버튼 눌러서 아이템 생성하고 다시 돌아왓을때 화면 초기화 하는범 물어보기
    @Override  //Todo 스와이프 닫을때 버벅거리는데 물어보기 , 터치 개념 물어보기
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                adapter.mItemManger.closeAllItems();
                adapter.closeAllItems();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                adapter.mItemManger.closeAllItems();
            }
        });


//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
////                adapter.closeAllItems();
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
////                adapter.closeAllExcept(swipeLayout);
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
////                adapter.closeAllItems();
//            }
//        });
    }

    @Override
    public void notifyItemRemoved(int position) {
        adapter.mItemManger.removeShownLayouts(swipeLayout);
        adapter.removeItem(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position,adapter.getItems().size());
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
        showBackgroundImg();
    }

    @Override
    public void notifyItemChanged(List<FrequentlyUesdAccountAdapter.ItemView> data) {
        adapter.setItems(data);
        adapter.notifyDataSetChanged();
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
        showBackgroundImg();
    }

    @Override
    public void showBackgroundImg() {
        if (adapter.getItems().size() != 0) {
            imgBackground.setVisibility(View.INVISIBLE);
            txtDefaultText.setVisibility(View.INVISIBLE);
        } else {
            imgBackground.setVisibility(View.VISIBLE);
            txtDefaultText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFrequentlyUsedAccount() {
        startActivityForResult(new Intent(getActivity(), RegisterFrequentlyUsedAccountActivity.class),0);
    }

    @Override
    public void goWriteList(int position) {
        Toast.makeText(getContext(), "화면으로 이동", Toast.LENGTH_SHORT).show();
        //Todo 1.홈 작성화면으로 이동 + 포지션값 전달
    }

    @Override
    public void goRevise(int position) {
        Intent intent = new Intent(getContext(), RegisterFrequentlyUsedAccountActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("id",adapter.getItems().get(position).getId());
        intent.putExtra("name", adapter.getItems().get(position).getAccountHolder());
        intent.putExtra("accountInfo", adapter.getItems().get(position).getAccountInfo());
        startActivityForResult(intent,1);
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
    }

    @Override
    public void showDeleteDialog(int position) {
        //Todo 2.삭제 다이얼로그 생성

        DeleteMessageDialog deleteAccountDialog = DeleteMessageDialog.newDialogInstance();
        deleteAccountDialog.setTargetFragment(FrequentlyUsedAccountFragment.this, 100);
        deleteAccountDialog.show(getFragmentManager(), "delete dialog");
//        Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
        this.position = position; //Todo this가 static 안되는 이유가 뭐엿찌? -검색 ㄱ
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        if (adapter != null) //Todo 이거 질문 객체 다 끝날떄 null 해주면 좋나
            adapter = null;
    }

    @Override
    public void setOnClickDeleteMessage() {
        presenter.deleteAccount(this.position, adapter.getItems().get(this.position).getAccountInfo());
        imgDeleteComplete.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgDeleteComplete.setVisibility(View.INVISIBLE);
            }
        },1300);
    }

    @Override
    public void setOnClickCancelMessage() {
        adapter.mItemManger.closeAllItems();
        adapter.closeAllItems();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == Activity.RESULT_OK)
            presenter.setModel();
        if(requestCode == 1 && resultCode == Activity.RESULT_OK)
           new Thread(new Runnable() {
               @Override
               public void run() {
                   presenter.setModel();
               }
           }).start();
    }

    //    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.add_frequently_used_account,menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == R.id.add_account) {
//            Toast.makeText(getContext(), "aaa", Toast.LENGTH_SHORT).show();
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
