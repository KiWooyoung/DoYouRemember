package com.omjoonkim.doyouremember.app.home.sendmoney;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.home.sendmoney.listener.OnHomeSendClickListener;
import com.omjoonkim.doyouremember.app.home.sendmoney.presenter.HomeSendPresenter;
import com.omjoonkim.doyouremember.app.home.sendmoney.presenter.HomeSendPresenterImpl;
import com.omjoonkim.doyouremember.app.writing.sendmoney.WritingSendActivity;
import com.omjoonkim.doyouremember.common.EmptyRecyclerView;
import com.omjoonkim.doyouremember.realm.entitiy.SendMoneyRealmObject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class HomeSendMoneyFragment extends Fragment implements HomeSendPresenter.View {

    @BindView(R.id.recycler_home_send)
    EmptyRecyclerView recyclerView;

    @BindView(R.id.empty_view_home_send)
    View emptyView;

    @BindDimen(R.dimen.send_list_bottom_margin)
    int spacingBottomSize;

    @BindDimen(R.dimen.send_list_first_margin)
    int spacingFirstSize;

    private HomeSendAdapter adapter;
    private HomeSendPresenter homeSendPresenter;
    private Realm realm;
    RealmResults<SendMoneyRealmObject> sendMoneyRealmResults;

    @OnClick(R.id.fab_send_writing)
    void onFabClick(View view){
        homeSendPresenter.onClickFab();
    }

    public static HomeSendMoneyFragment newInstance() {
        return new HomeSendMoneyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeSendPresenter = new HomeSendPresenterImpl();
        homeSendPresenter.setView(this);
        realm = Realm.getDefaultInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_money, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initRecyclerViewInit();
    }

    private void initRecyclerViewInit() {
        adapter = new HomeSendAdapter(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setEmptyView(emptyView);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HomeSendItemDecoration(spacingBottomSize, spacingFirstSize));
        sendMoneyRealmResults = realm.where(SendMoneyRealmObject.class).findAllSorted( "dateTime" );
        adapter.setData(sendMoneyRealmResults);
        adapter.setClickListener(new OnHomeSendClickListener() {
            @Override
            public void onChangeItemCount() {
                homeSendPresenter.onChangeItemCount();
            }

            @Override
            public void onClickSendCopy(String account) {
                homeSendPresenter.onClickCopy(account);
            }

            @Override
            public void onClickSendEdit( long id ) {
                homeSendPresenter.onClickEdit(id);
            }

            @Override
            public void onClickSendDelete(long id) {
                homeSendPresenter.onClickDelete(id);
            }
        });
    }

    @Override
    public void copyHomeSendAccount(String account) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", account);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getActivity().getApplicationContext(),"계좌가 복사되었습니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteHomeSendItem(long id) {
        realm.beginTransaction();
        sendMoneyRealmResults.where().equalTo( "id", id).findFirst().deleteFromRealm();
        realm.commitTransaction();
        RealmResults<SendMoneyRealmObject> sendMoneyRealmResults = realm.where(SendMoneyRealmObject.class).findAllSorted( "dateTime" );
        adapter.setData(sendMoneyRealmResults);
        adapter.notifyDataSetChangedAndSwipeClear();
        Toast.makeText(getActivity().getApplicationContext(), "Deleted" , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void editHomeSendItem( long id ) {
        startActivity( new Intent( getContext(),WritingSendActivity.class ).putExtra( "id",id ) );
    }

    @Override
    public void loadHomeSendWritingActivity() {
        startActivity(new Intent(this.getContext(), WritingSendActivity.class));
    }

    @Override
    public void setRecyclerBackgroundImg() {
        if (adapter.getItemCount() != 0) {

        } else {

        }
    }

    @Override
    public void onResume() {

        super.onResume();
        RealmResults<SendMoneyRealmObject> sendMoneyRealmResults = realm.where(SendMoneyRealmObject.class).findAllSorted( "dateTime" );
        adapter.setData(sendMoneyRealmResults);
        adapter.notifyDataSetChangedAndSwipeClear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close(); // Remember to close Realm when done.
    }

}
