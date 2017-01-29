package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog.SelectBankDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class RegisterFrequentlyUsedAccountActivity extends AppCompatActivity implements RegisterView,SelectBankDialog.SelectBankListener {

    private RegisterPresenter presenter = null;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_text_name)
    EditText editName;
    @BindView(R.id.edit_text_account_number)
    EditText editAccountNumber;
    @BindView(R.id.text_view_bank)
    TextView txtBank;

    @OnFocusChange(R.id.edit_text_account_number)
    void onFocusChangeAccountNumber(boolean hasFocus){
        if(hasFocus) {
            editAccountNumber.setBackgroundResource(R.drawable.back_change);
        } else {
            editAccountNumber.setBackgroundResource(R.drawable.back);
        }
    }
    @OnFocusChange(R.id.edit_text_name)
    void onFocusChangeName(boolean hasFocus){
        if(hasFocus) {
            editName.setBackgroundResource(R.drawable.back_change);
        } else {
            editName.setBackgroundResource(R.drawable.back);
        }
    }
    @OnClick(R.id.text_view_bank)
    void onBankClick(){
        presenter.onChooseBank();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_frequently_used_account);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_icon);
        presenter = new RegisterPresenterImpl(this);

    }

    @Override
    public void showDialog() {

        SelectBankDialog selectBankDialog = SelectBankDialog.newDialogInstance();
        selectBankDialog.show(getFragmentManager(), "Test");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_frequently_used_account, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if(item.getItemId() == R.id.add_account) {

            finish(); //Todo 프레그먼트로 데이터 이동
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectBank(String bank) {
        txtBank.setText(bank);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.onDestroy();
    }

}
