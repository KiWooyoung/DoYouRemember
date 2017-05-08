package com.omjoonkim.doyouremember.app.writing.receivemoney.presenter;



public interface WritingReceivePresenter {

    void setView(WritingReceivePresenter.View view);
    void onClickDelete(int position);

    interface View {
        void deleteWritingReceivePeople(int position);
    }
}
