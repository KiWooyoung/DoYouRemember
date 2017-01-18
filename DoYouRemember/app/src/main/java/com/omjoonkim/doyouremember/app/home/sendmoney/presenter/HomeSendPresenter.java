package com.omjoonkim.doyouremember.app.home.sendmoney.presenter;

import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

public interface HomeSendPresenter {
    void setView(HomeSendPresenter.View view);
    void OnClickCopy(HomeSendData homeSendData);
    void onClickEdit();
    void OnClickDelete(List<HomeSendData> homeSendDataList, int position);

    interface View {
        void copyHomeSendAccount(HomeSendData homeSendData);
        void deleteHomeSendItem(List<HomeSendData> homeSendDataList, int position);
        void editHomeSendItem();
    }
}