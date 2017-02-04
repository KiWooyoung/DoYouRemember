package com.omjoonkim.doyouremember.app.myaccount.registermyaccount;

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

public class RegisterMyAccountActivity extends AppCompatActivity implements RegisterMyAccountView, SelectBankDialog.SelectBankListener {

    RegisterMyAccountPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.text_view_my_bank_name)
    TextView txtMyBankName;
    @BindView(R.id.edit_text_my_account_number)
    EditText editMyAccountNumber;
    @OnClick(R.id.text_view_my_bank_name)
    void selectMyBank(){
        presenter.onChooseBank();
    }
    @OnFocusChange(R.id.edit_text_my_account_number)
    void onFocusChangeAccountNumber(boolean hasFocus) {
        if (hasFocus) {
            editMyAccountNumber.setBackgroundResource(R.drawable.back_change);
        } else {
            editMyAccountNumber.setBackgroundResource(R.drawable.back);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_my_account);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_icon);

        presenter = new RegisterMyAccountPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_frequently_used_account, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        } else if(item.getItemId() == R.id.add_account) {
            finish(); //Todo 프레그먼트로 데이터 이동
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.onDestroy();
    }

    @Override
    public void onSelectBank(String bank) {
        txtMyBankName.setText(bank);
    }

    @Override
    public void showDialog() {
        SelectBankDialog selectBankDialog = SelectBankDialog.newDialogInstance();
        selectBankDialog.show(getFragmentManager(), "showDialog");
    }
}
