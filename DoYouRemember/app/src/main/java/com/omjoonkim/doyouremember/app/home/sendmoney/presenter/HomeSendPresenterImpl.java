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
    public void OnClickCopy(HomeSendData homeSendData) {
        this.view.copyHomeSendAccount(homeSendData);
    }

    @Override
    public void onClickEdit() {
        this.view.editHomeSendItem();
    }

    @Override
    public void OnClickDelete(List<HomeSendData> homeSendDataList, int position) {
        this.view.deleteHomeSendItem(homeSendDataList, position);
    }
}
