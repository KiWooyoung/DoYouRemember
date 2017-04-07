package com.omjoonkim.doyouremember.app.home.receivemoney.listener;

import com.omjoonkim.doyouremember.model.HomeReceiveChildData;
import com.omjoonkim.doyouremember.model.HomeReceiveParentData;

import java.util.List;

public interface OnHomeReceiveClickListener {
    void onSendKakaoLink(String receivePrice);
    void onCheckedDebtors(int parentPosition);
}
