package com.omjoonkim.doyouremember.app.home.receivemoney.presenter;


import android.util.Log;

import com.omjoonkim.doyouremember.app.home.receivemoney.ReceiveMoneyChildViewHolder;
import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.util.List;

public class HomeReceivePresenterImpl implements HomeReceivePresenter {

    public static final String TAG = HomeReceivePresenterImpl.class.getSimpleName();

    private HomeReceivePresenter.View view;

    @Override
    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onClickKakaoLink() {

    }

    @Override
    public void onClickedChecked(int parentPosition) {
        this.view.updateDebtorCount(parentPosition);
    }
}
