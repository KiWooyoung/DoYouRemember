package com.omjoonkim.doyouremember.app.home.sendmoney.presenter;

import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

public class HomeSendPresenterImpl implements HomeSendPresenter {

    private HomeSendPresenter.View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onChangeItemCount() {
        this.view.setRecyclerBackgroundImg();
    }

    @Override
    public void onClickFab() {
        this.view.loadHomeSendWritingActivity();
    }

    @Override
    public void onClickCopy(String account) {
        this.view.copyHomeSendAccount(account);
    }

    @Override
    public void onClickEdit() {
        this.view.editHomeSendItem();
    }

    @Override
    public void onClickDelete(List<HomeSendData> homeSendDataList, int position) {
        this.view.deleteHomeSendItem(homeSendDataList, position);
    }
}
