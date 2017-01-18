package com.omjoonkim.doyouremember.app.home.sendmoney.listener;


import com.omjoonkim.doyouremember.model.HomeSendData;

import java.util.List;

public interface OnHomeSendClickListener {
    void OnClickSendCopy(HomeSendData homeSendData);
    void OnClickSendDelete(List<HomeSendData> homeSendDataList, int position);
}
