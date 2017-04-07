package com.omjoonkim.doyouremember.app.home.sendmoney.presenter;


public class HomeSendPresenterImpl implements HomeSendPresenter {

    private HomeSendPresenter.View view;

    @Override
    public void setView(View view) {
        this.view = view;
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
    public void onClickEdit( long id ) {
        this.view.editHomeSendItem(id);
    }

    @Override
    public void onClickDelete(long id) {
        this.view.deleteHomeSendItem(id);
    }
}
