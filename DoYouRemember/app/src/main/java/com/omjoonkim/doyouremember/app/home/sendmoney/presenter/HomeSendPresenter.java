package com.omjoonkim.doyouremember.app.home.sendmoney.presenter;

import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

public interface HomeSendPresenter {
    void setView(HomeSendPresenter.View view);
    void onChangeItemCount();
    void onClickFab();
    void onClickCopy(String account);
    void onClickEdit();
    void onClickDelete(List<HomeSendData> homeSendDataList, int position);

    interface View {
        void copyHomeSendAccount(String account);
        void deleteHomeSendItem(List<HomeSendData> homeSendDataList, int position);
        void editHomeSendItem();
        void loadHomeSendWritingActivity();
        void setRecyclerBackgroundImg();
    }
}