package com.omjoonkim.doyouremember.app.home.sendmoney.listener;


import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

public interface OnHomeSendClickListener {
    void onChangeItemCount();
    void onClickSendCopy(String account);
    void onClickSendEdit();
    void onClickSendDelete(List<HomeSendData> homeSendDataList, int position);
}
