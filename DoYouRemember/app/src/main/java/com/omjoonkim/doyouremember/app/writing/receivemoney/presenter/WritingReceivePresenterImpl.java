package com.omjoonkim.doyouremember.app.writing.receivemoney.presenter;


public class WritingReceivePresenterImpl implements WritingReceivePresenter {

    private WritingReceivePresenter.View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onClickDelete(int position) {
        this.view.deleteWritingReceivePeople(position);
    }

}
