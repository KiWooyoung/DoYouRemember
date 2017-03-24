package com.omjoonkim.doyouremember.app.home.sendmoney.presenter;


public interface HomeSendPresenter {
    void setView(HomeSendPresenter.View view);
    void onChangeItemCount();
    void onClickFab();
    void onClickCopy(String account);
    void onClickEdit( long id );
    void onClickDelete(long id);

    interface View {
        void copyHomeSendAccount(String account);
        void deleteHomeSendItem( long id);
        void editHomeSendItem( long id );
        void loadHomeSendWritingActivity();
        void setRecyclerBackgroundImg();
    }
}