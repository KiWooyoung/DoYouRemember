package com.omjoonkim.doyouremember.app.writing;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.omjoonkim.doyouremember.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnFocusChange;

public class WritingSendActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_writing)
    Toolbar tbWriting;

    @BindView(R.id.editText_writing_send_title)
    EditText etWritingTitle;

    @BindView(R.id.editText_writing_send_creditor)
    EditText getEtWritingCreditor;

    @BindView(R.id.editText_writing_send_account)
    EditText getEtWritingAccount;

    @BindView(R.id.editText_writing_send_price)
    EditText getEtWritingPrice;


    @OnFocusChange(R.id.editText_writing_send_title)
    void onFocusChangeTitle(boolean hasFocus){
        if(hasFocus) {
            etWritingTitle.setBackgroundResource(R.drawable.back_change);
        } else {
            etWritingTitle.setBackgroundResource(R.drawable.back);
        }
    }

    @OnFocusChange(R.id.editText_writing_send_creditor)
    void onFocusChangeCreditor(boolean hasFocus){
        if(hasFocus) {
            getEtWritingCreditor.setBackgroundResource(R.drawable.back_change);
        } else {
            getEtWritingCreditor.setBackgroundResource(R.drawable.back);
        }
    }

    @OnFocusChange(R.id.editText_writing_send_account)
    void onFocusChangeAccount(boolean hasFocus){
        if(hasFocus) {
            getEtWritingAccount.setBackgroundResource(R.drawable.back_change);
        } else {
            getEtWritingAccount.setBackgroundResource(R.drawable.back);
        }
    }

    @OnFocusChange(R.id.editText_writing_send_price)
    void onFocusChangePrice(boolean hasFocus){
        if(hasFocus) {
            getEtWritingPrice.setBackgroundResource(R.drawable.back_change);
        } else {
            getEtWritingPrice.setBackgroundResource(R.drawable.back);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_send);

        ButterKnife.bind(this);

        setSupportActionBar(tbWriting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tbWriting.setNavigationIcon(R.drawable.back_icon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_frequently_used_account, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
