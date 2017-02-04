package com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omjoonkim.doyouremember.R;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class DeleteAccountDialog extends DialogFragment {

//    @BindView(R.id.relative_layout_delete)
//    RelativeLayout relativeLayout;

    public static DeleteAccountDialog newDialogInstance(){
        DeleteAccountDialog dialog = new DeleteAccountDialog();
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_frequently_used_account, container, false);
//        ButterKnife.bind(this, view);

//        relativeLayout.setBackgroundResource(0);

        return view;
    }
}
