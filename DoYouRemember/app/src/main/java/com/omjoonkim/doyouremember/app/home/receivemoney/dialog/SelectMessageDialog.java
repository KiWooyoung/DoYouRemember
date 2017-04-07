package com.omjoonkim.doyouremember.app.home.receivemoney.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.omjoonkim.doyouremember.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectMessageDialog extends DialogFragment {

    @BindView(R.id.viewPager_message)
    ViewPager mPager;

    private SelectMessagePagerAdapter selectMessagePagerAdapter;
    private KakaoLink kakaoLink;
    private KakaoTalkLinkMessageBuilder kakaoTalkLinkMessageBuilder;

    @OnClick(R.id.view_send_message)
    void onSendMessageClick(){
        SelectMessageFragment currentMessage = (SelectMessageFragment) selectMessagePagerAdapter.getRegisteredFragment(mPager.getCurrentItem());
        try {
            kakaoTalkLinkMessageBuilder.addText(currentMessage.getKaKaoMessage());
            kakaoTalkLinkMessageBuilder.addWebButton("까먹지망고로 이동");
            kakaoLink.sendMessage(kakaoTalkLinkMessageBuilder, getContext());
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
        } catch (KakaoParameterException e) {
            e.getMessage();
        }
    }

    @OnClick(R.id.btn_send_cancel)
    void onSendCancelClick(){
        dismiss();
    }


    public static SelectMessageDialog newDialogInstance(String receivePrice){
        SelectMessageDialog selectMessageDialog  = new SelectMessageDialog();

        Bundle args = new Bundle();
        args.putString("receivePrice", receivePrice);
        selectMessageDialog.setArguments(args);

        return selectMessageDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_select_kakao_message, null);
        ButterKnife.bind(this, view);

        try {
            kakaoLink = KakaoLink.getKakaoLink(getContext());
            kakaoTalkLinkMessageBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();
        } catch (KakaoParameterException e) {
            Log.e("error", e.getMessage());
        }

        selectMessagePagerAdapter = new SelectMessagePagerAdapter(
                getChildFragmentManager(), getArguments().getString("receivePrice"));
        mPager.setOffscreenPageLimit(3);
        mPager.setPageMargin((int)dpToPixels(15,getContext()));
        mPager.setAdapter(selectMessagePagerAdapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        Window mWindow = getDialog().getWindow();
        WindowManager.LayoutParams mLayoutParams = mWindow.getAttributes();
        mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mLayoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        mLayoutParams.gravity = Gravity.CENTER;
        mWindow.setAttributes(mLayoutParams);
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
}