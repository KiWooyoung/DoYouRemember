package com.omjoonkim.doyouremember.app.frequentlyusedaccount;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount.RegisterFrequentlyUsedAccountActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by owner on 2017-01-13.
 */

public class FrequentlyUsedAccountFragment extends Fragment implements com.omjoonkim.doyouremember.app.frequentlyusedaccount.FrequentlyUsedAccountView {
    SwipeLayout swipeLayout;
    @BindView(R.id.recycler_view_FUAccount)
    RecyclerView recyclerView;
    @BindView(R.id.image_view_background)
    ImageView imgBackground;
    @BindView(R.id.text_view_default_text)
    TextView txtDefaultText;

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
        adapter = new FrequentlyUesdAccountAdapter(presenter);

        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setMode(Attributes.Mode.Single);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.setModel();
//        adapter.notifyDataSetChanged();

    }

                //Todo 자주쓰는 계좌 작성페이지에서 필요한 정보(이름,은행,계좌번호)중에 한가지라도 안적으면 어떻게 되는지 상의하기 (토스트? 대화상자?)
                //Todo 추가버튼 눌러서 아이템 생성하고 다시 돌아왓을때 화면 초기화 하는범 물어보기
    @Override  //Todo 스와이프 닫을때 버벅거리는데 물어보기 , 터치 개념 물어보기
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                adapter.closeAllItems();
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                adapter.closeAllItems();
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
        adapter.removeItem(position);
        adapter.notifyItemRemoved(position);
        adapter.closeAllItems();
        showBackgroundImg();
    }

    @Override
    public void notifyItemChanged(List<FrequentlyUesdAccountAdapter.ItemView> data) {
        adapter.setItems(data);
        adapter.notifyDataSetChanged();
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
        startActivity(new Intent(getActivity(), RegisterFrequentlyUsedAccountActivity.class));
    }

    @Override
    public void goWriteList(int position) {
        Toast.makeText(getContext(), "화면으로 이동", Toast.LENGTH_SHORT).show();
        //Todo 홈 작성화면으로 이동 + 포지션값 전달
    }

    @Override
    public void goRevise(int position) {
        //Todo 현재 Item 정보를 자주쓰는계좌로 옴겨서 수정화면 보여주기
        Intent intent = new Intent(getContext(), RegisterFrequentlyUsedAccountActivity.class);
        intent.putExtra("position", position);
        intent.putExtra("name", adapter.getItems().get(position).getAccountHolder());
        intent.putExtra("accountInfo", adapter.getItems().get(position).getAccountInfo());
        startActivity(intent);
    }

    @Override
    public void showDeleteDialog(int position) {
        //Todo 삭제 다이얼로그 생성
//        DeleteAccountDialog deleteAccountDialog = DeleteAccountDialog.newDialogInstance();
//        deleteAccountDialog.show(getActivity().getFragmentManager(), "delete dialog");
        Log.i("MyTag",adapter.getItems().size() + "   우영");
        presenter.deleteAccount(position, adapter.getItems().get(position).getAccountInfo());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        if (adapter != null) //Todo 이거 질문 객체 다 끝날떄 null 해주면 좋나
            adapter = null;
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
