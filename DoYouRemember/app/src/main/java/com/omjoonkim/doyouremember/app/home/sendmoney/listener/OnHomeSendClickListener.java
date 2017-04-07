package com.omjoonkim.doyouremember.app.home.sendmoney.listener;


import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

public interface OnHomeSendClickListener {
    void onClickSendCopy(String account);
    void onClickSendEdit( long id );
    void onClickSendDelete(long id);
}
