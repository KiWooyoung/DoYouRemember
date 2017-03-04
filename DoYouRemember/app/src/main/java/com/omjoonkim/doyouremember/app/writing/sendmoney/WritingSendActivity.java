package com.omjoonkim.doyouremember.app.writing.sendmoney;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.dialog.SelectBankDialog;

import com.omjoonkim.doyouremember.app.writing.selectfrequentlyusedaccount.SelectFrequentlyUsedActivity;
import com.omjoonkim.doyouremember.realm.entitiy.SendMoneyRealmObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.realm.Realm;

public class WritingSendActivity extends AppCompatActivity implements SelectBankDialog.SelectBankListener{

    public static final String TAG = WritingSendActivity.class.getSimpleName();

    static final int GET_FREQUENT_USER = 1;

    @BindView(R.id.toolbar_writing)
    Toolbar tbWriting;

    @BindView(R.id.editText_writing_send_title)
    EditText etWritingTitle;

    @BindView(R.id.textView_writing_send_deadline_date)
    TextView tvSendDate;

    @BindView(R.id.textView_writing_send_deadline_time)
    TextView tvSendTime;

    @BindView(R.id.editText_writing_send_creditor)
    EditText etWritingCreditor;

    @BindView(R.id.textView_writing_send_bank)
    TextView tvSendBank;

    @BindView(R.id.editText_writing_send_account)
    EditText etEtWritingAccount;

    @BindView(R.id.editText_writing_send_price)
    EditText etEtWritingPrice;

    private Realm realm;

    private Calendar calendar;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

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
            etWritingCreditor.setBackgroundResource(R.drawable.back_change);
        } else {
            etWritingCreditor.setBackgroundResource(R.drawable.back);
        }
    }

    @OnFocusChange(R.id.editText_writing_send_account)
    void onFocusChangeAccount(boolean hasFocus){
        if(hasFocus) {
            etEtWritingAccount.setBackgroundResource(R.drawable.back_change);
        } else {
            etEtWritingAccount.setBackgroundResource(R.drawable.back);
        }
    }

    @OnFocusChange(R.id.editText_writing_send_price)
    void onFocusChangePrice(boolean hasFocus){
        if(hasFocus) {
            etEtWritingPrice.setBackgroundResource(R.drawable.back_change);
        } else {
            etEtWritingPrice.setBackgroundResource(R.drawable.back);
        }
    }

    @OnClick(R.id.textView_writing_send_deadline_date)
    void onClickSendDate(){
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                tvSendDate.setText(year+" - "+(month+1)+" - "+dayOfMonth);
            }
        }, year, month ,day ).show();
    }

    @OnClick(R.id.textView_writing_send_deadline_time)
    void onClickSendTime(){
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tvSendTime.setText(hourOfDay+":"+minute);
            }
        }, hour, minute, false).show();
    }

    @OnClick(R.id.textView_writing_send_bank)
    void onClickBank(){
        SelectBankDialog selectBankDialog = SelectBankDialog.newDialogInstance();
        selectBankDialog.show(getSupportFragmentManager(), "Test122");
    }

    @OnClick(R.id.imageView_frequent_user_select)
    void onSelectFrequentUser(){
        Intent intent = new Intent(WritingSendActivity.this, SelectFrequentlyUsedActivity.class);
        startActivityForResult(intent, GET_FREQUENT_USER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GET_FREQUENT_USER){
            if (resultCode == RESULT_OK){
                etWritingCreditor.setText(data.getStringExtra("ACCOUNT_NAME"));
                tvSendBank.setText(data.getStringExtra("ACCOUNT_BANK"));
                etEtWritingAccount.setText(data.getStringExtra("ACCOUNT_NUMBER"));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_send);

        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        initView();
    }

    private void initView(){
        // 액션바
        setSupportActionBar(tbWriting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tbWriting.setNavigationIcon(R.drawable.back_icon);

        // 오늘 날짜 및 시간 설정
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month =  calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);

        int isAMorPM = calendar.get(Calendar.AM_PM);

        tvSendDate.setText(year+"/"+(month+1)+"/"+day);
        tvSendTime.setText(getTimeFormat(isAMorPM,hour,minute));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_send_writing, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.send_write_finish:
                writeSendForm(realm);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String getTimeFormat(int isAMorPM, int hour, int minute){
        String timeFormat = "";
        switch (isAMorPM){
            case Calendar.AM:
                timeFormat =  "오전 "+String.valueOf(String.format("%02d", hour))+" : "+String.valueOf(String.format("%02d", minute));
                break;
            case Calendar.PM:
                timeFormat =  "오후 "+String.valueOf(String.format("%02d", hour))+" : "+String.valueOf(String.format("%02d", minute));
                break;
        }
        return timeFormat;
    }

    private void writeSendForm(Realm realm){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number num = realm.where(SendMoneyRealmObject.class).max("id");
                int nextID;
                if(num == null) {
                    nextID = 1;
                } else {
                    nextID = num.intValue() + 1;
                }

                SendMoneyRealmObject sendMoneyRealmObject = realm.createObject(SendMoneyRealmObject.class, nextID);
                sendMoneyRealmObject.setTitle(etWritingTitle.getText().toString());
                sendMoneyRealmObject.setCreditor(etWritingCreditor.getText().toString());
                sendMoneyRealmObject.setBankType(tvSendBank.getText().toString());
                sendMoneyRealmObject.setAccount(etEtWritingAccount.getText().toString());
                int intPrice = Integer.valueOf(etEtWritingPrice.getText().toString());
                sendMoneyRealmObject.setPrice(intPrice);
            }
        });
    }

    @Override
    public void onSelectBank(String bank) {
        tvSendBank.setText(bank);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}