package com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.omjoonkim.doyouremember.R.id.design_menu_item_text;
import static com.omjoonkim.doyouremember.R.id.image_view_cancel;
import static com.omjoonkim.doyouremember.R.id.image_view_delete;


public class DeleteMessageDialog extends android.support.v4.app.DialogFragment {

    private DeleteMessageListener listener = null;

    private TextView tvDelMsg;

    @OnClick({image_view_cancel, image_view_delete})
    public void onClick(View v){
        switch (v.getId()) {
            case image_view_cancel :
                dismiss();
                listener.setOnClickCancelMessage();
                break;
            case image_view_delete :
                listener.setOnClickDeleteMessage();
                dismiss();
//                sendBackResult();
                break;
        }
    }

    public static DeleteMessageDialog newDialogInstance(String deleteMessage){
        DeleteMessageDialog deleteMessageDialog  = new DeleteMessageDialog();

        Bundle args = new Bundle();
        args.putString("deleteMessage", deleteMessage);
        deleteMessageDialog.setArguments(args);

        return deleteMessageDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_delete_frequently_used_account, container, false);
        ButterKnife.bind(this, view);

        getDialog().getWindow().setBackgroundDrawableResource(R.drawable.rounded_dialog);

        tvDelMsg = (TextView) view.findViewById(R.id.textView_delete_message);
        String deleteMessage = getArguments().getString("deleteMessage");
        tvDelMsg.setText(deleteMessage);

        listener = (DeleteMessageListener) getTargetFragment();

        return view;
    }


    public interface DeleteMessageListener {
        void setOnClickDeleteMessage();
        void setOnClickCancelMessage();
    }

    //    public void sendBackResult() {
//        DeleteMessageListener listener = (DeleteMessageListener) getTargetFragment();
//        listener.setOnClickMessage();
//        dismiss();
//    }

}












