package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog.SelectBankDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;

public class RegisterFrequentlyUsedAccountActivity extends AppCompatActivity implements RegisterView, SelectBankDialog.SelectBankListener {

    private RegisterPresenter presenter = null;
    private Intent intent = null;
    private String name, bankType, accountNumber = null;
    private int position = 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_text_name)
    EditText editName;
    @BindView(R.id.edit_text_account_number)
    EditText editAccountNumber;
    @BindView(R.id.text_view_bank)
    TextView txtBank;

    @OnFocusChange(R.id.edit_text_account_number)
    void onFocusChangeAccountNumber(boolean hasFocus) {
        if (hasFocus) {
            editAccountNumber.setBackgroundResource(R.drawable.back_change);
        } else {
            editAccountNumber.setBackgroundResource(R.drawable.back);
        }
    }

    @OnFocusChange(R.id.edit_text_name)
    void onFocusChangeName(boolean hasFocus) {
        if (hasFocus) {
            editName.setBackgroundResource(R.drawable.back_change);
        } else {
            editName.setBackgroundResource(R.drawable.back);
        }
    }

    @OnClick(R.id.text_view_bank)
    void onBankClick() {
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

        intent = getIntent();

        presenter.checkRevise();

    }

    @Override
    public void setRevise() {

        position = intent.getIntExtra("position", 0);
        name = intent.getStringExtra("name");
        if (name != null) {
            bankType = intent.getStringExtra("accountInfo").substring(0, 2);
            accountNumber = intent.getStringExtra("accountInfo").substring(3);

            editName.setText(name);
            txtBank.setText(bankType);
            editAccountNumber.setText(accountNumber);
        }
    }

    @Override
    public void showBankTypeDialog() {

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
        } else if (item.getItemId() == R.id.add_account) {
            if (name == null) {
                presenter.onRegister(editName.getText().toString(), editAccountNumber.getText().toString(), txtBank.getText().toString());
            } else {
                presenter.onRevise(accountNumber, editName.getText().toString(), editAccountNumber.getText().toString(), txtBank.getText().toString());
            }
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
        if (presenter != null) {
            presenter.onDestroy();
            presenter = null;
        }
    }

    @Override
    public void goFinish() {
        finish();
    }

    @Override
    public void showAllTypeToast() {
        Toast.makeText(this, "모두 입력해 주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showOverlapAccountToast() {
        Toast.makeText(this, "계좌가 중복 되었습니다.", Toast.LENGTH_SHORT).show();
    }


}
