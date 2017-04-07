package com.omjoonkim.doyouremember.app.writing.sendmoney;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;
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


public class WritingSendActivity extends AppCompatActivity implements SelectBankDialog.SelectBankListener {

	public static final String TAG = WritingSendActivity.class.getSimpleName();

	static final int GET_FREQUENT_USER = 1;

	@BindView( R.id.toolbar_writing )
	Toolbar tbWriting;

	@BindView( R.id.editText_writing_send_title )
	EditText etWritingTitle;

	@BindView( R.id.textView_writing_send_deadline_date )
	TextView tvSendDate;

	@BindView( R.id.textView_writing_send_deadline_time )
	TextView tvSendTime;


	@BindView( R.id.textView_writing_send_alarm_date )
	TextView tvAlarmDate;

	@BindView( R.id.textView_writing_send_alarm_time )
	TextView tvAlarmTime;


	@BindView( R.id.editText_writing_send_creditor )
	EditText etWritingCreditor;

	@BindView( R.id.textView_writing_send_bank )
	TextView tvSendBank;

	@BindView( R.id.editText_writing_send_account )
	EditText etEtWritingAccount;

	@BindView( R.id.editText_writing_send_price )
	EditText etEtWritingPrice;

	@BindView( R.id.toggle_alarm_activate )
	JellyToggleButton jellyToggleButton;

	@BindView( R.id.fakeView2 )
	View fakeView2;

	@BindView( R.id.imageView_sendAlarmDate )
	View imageView_sendAlarmDate;

	@BindView( R.id.imageView_sendAlarmTime )
	View imageView_sendAlarmTime;

	private Realm realm;

	private Calendar calendar;
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private Calendar alarmCalendar;
	private int alarmYear;
	private int alarmMonth;
	private int alarmDay;
	private int alarmHour;
	private int alarmMinute;

	SendMoneyRealmObject sendMoneyRealmObject;

	@OnFocusChange( R.id.editText_writing_send_title )
	void onFocusChangeTitle( boolean hasFocus ) {

		if ( hasFocus ) {
			etWritingTitle.setBackgroundResource( R.drawable.back_change );
		}
		else {
			etWritingTitle.setBackgroundResource( R.drawable.back );
		}
	}

	@OnFocusChange( R.id.editText_writing_send_creditor )
	void onFocusChangeCreditor( boolean hasFocus ) {

		if ( hasFocus ) {
			etWritingCreditor.setBackgroundResource( R.drawable.back_change );
		}
		else {
			etWritingCreditor.setBackgroundResource( R.drawable.back );
		}
	}

	@OnFocusChange( R.id.editText_writing_send_account )
	void onFocusChangeAccount( boolean hasFocus ) {

		if ( hasFocus ) {
			etEtWritingAccount.setBackgroundResource( R.drawable.back_change );
		}
		else {
			etEtWritingAccount.setBackgroundResource( R.drawable.back );
		}
	}

	@OnFocusChange( R.id.editText_writing_send_price )
	void onFocusChangePrice( boolean hasFocus ) {

		if ( hasFocus ) {
			etEtWritingPrice.setBackgroundResource( R.drawable.back_change );
		}
		else {
			etEtWritingPrice.setBackgroundResource( R.drawable.back );
		}
	}

	@OnClick( R.id.textView_writing_send_alarm_date )
	void onClickAlarmDate() {

		new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {

				WritingSendActivity.this.alarmYear = year;
				WritingSendActivity.this.alarmMonth = month;
				alarmDay = dayOfMonth;
				tvAlarmDate.setText( year + "/" + ( month + 1 ) + "/" + dayOfMonth );
				alarmCalendar.set( year, month, dayOfMonth );
			}
		}, alarmYear, alarmMonth, alarmDay ).show();
	}

	@OnClick( R.id.textView_writing_send_alarm_time )
	void onClickAlarmTime() {

		new TimePickerDialog( this, new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {

				alarmHour = hourOfDay;
				WritingSendActivity.this.alarmMinute = minute;
				tvAlarmTime.setText( getTimeFormat( hourOfDay > 12 ? Calendar.PM : Calendar.AM, hourOfDay - 12 > 0 ? hourOfDay - 12 : hourOfDay, minute ) );
				alarmCalendar.set( Calendar.HOUR_OF_DAY, hourOfDay );
				alarmCalendar.set( Calendar.MINUTE, minute );
			}
		}, alarmHour, alarmMinute, false ).show();
	}

	@OnClick( R.id.textView_writing_send_deadline_date )
	void onClickSendDate() {

		new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {

				WritingSendActivity.this.year = year;
				WritingSendActivity.this.month = month;
				day = dayOfMonth;
				tvSendDate.setText( year + "/" + ( month + 1 ) + "/" + dayOfMonth );
				calendar.set( year, month, dayOfMonth );
			}
		}, year, month, day ).show();
	}

	@OnClick( R.id.textView_writing_send_deadline_time )
	void onClickSendTime() {

		new TimePickerDialog( this, new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {

				hour = hourOfDay;
				WritingSendActivity.this.minute = minute;
				tvSendTime.setText( getTimeFormat( hourOfDay > 12 ? Calendar.PM : Calendar.AM, hourOfDay - 12 > 0 ? hourOfDay - 12 : hourOfDay, minute ) );
				calendar.set( Calendar.HOUR_OF_DAY, hourOfDay );
				calendar.set( Calendar.MINUTE, minute );
			}
		}, hour, minute, false ).show();
	}

	@OnClick( R.id.textView_writing_send_bank )
	void onClickBank() {

		SelectBankDialog selectBankDialog = SelectBankDialog.newDialogInstance();
		selectBankDialog.show( getSupportFragmentManager(), "Test122" );
	}

	@OnClick( R.id.imageView_frequent_user_select )
	void onSelectFrequentUser() {

		Intent intent = new Intent( WritingSendActivity.this, SelectFrequentlyUsedActivity.class );
		startActivityForResult( intent, GET_FREQUENT_USER );
	}

	@Override
	protected void onActivityResult( int requestCode, int resultCode, Intent data ) {

		if ( requestCode == GET_FREQUENT_USER ) {
			if ( resultCode == RESULT_OK ) {
				etWritingCreditor.setText( data.getStringExtra( "ACCOUNT_NAME" ) );
				tvSendBank.setText( data.getStringExtra( "ACCOUNT_BANK" ) );
				etEtWritingAccount.setText( data.getStringExtra( "ACCOUNT_NUMBER" ) );
			}
		}
	}


	@Override
	protected void onCreate( Bundle savedInstanceState ) {

		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_writing_send );
		realm = Realm.getDefaultInstance();
		if ( getIntent() != null ) {
			long id = getIntent().getLongExtra( "id", -1 );
			if ( id > -1 )
				sendMoneyRealmObject = realm.where( SendMoneyRealmObject.class ).equalTo( "id", id ).findFirst();
		}
		ButterKnife.bind( this );
		initView();
	}

	private void initView() {
		// 액션바
		setSupportActionBar( tbWriting );
		getSupportActionBar().setDisplayHomeAsUpEnabled( true );
		getSupportActionBar().setDisplayShowTitleEnabled( false );
		tbWriting.setNavigationIcon( R.drawable.back_icon );

		// 오늘 날짜 및 시간 설정
		alarmCalendar = Calendar.getInstance();
		calendar = Calendar.getInstance();
		if ( sendMoneyRealmObject != null ) {
			alarmCalendar.setTime( sendMoneyRealmObject.getAlarmTime() );
			calendar.setTime( sendMoneyRealmObject.getDateTime() );
			etWritingTitle.setText( sendMoneyRealmObject.getTitle() );
			etEtWritingAccount.setText( sendMoneyRealmObject.getAccount() );
			etEtWritingPrice.setText( sendMoneyRealmObject.getPrice()+"" );
			etWritingCreditor.setText( sendMoneyRealmObject.getCreditor() );
			tvSendBank.setText( sendMoneyRealmObject.getBankType() );
		}

		alarmYear = alarmCalendar.get( Calendar.YEAR );
		alarmMonth = alarmCalendar.get( Calendar.MONTH );
		alarmDay = alarmCalendar.get( Calendar.DATE );
		alarmHour = alarmCalendar.get( Calendar.HOUR );
		alarmMinute = alarmCalendar.get( Calendar.MINUTE );

		year = calendar.get( Calendar.YEAR );
		month = calendar.get( Calendar.MONTH );
		day = calendar.get( Calendar.DATE );
		hour = calendar.get( Calendar.HOUR );
		minute = calendar.get( Calendar.MINUTE );
		int isAMorPM = calendar.get( Calendar.AM_PM );

		tvSendDate.setText( year + "/" + ( month + 1 ) + "/" + day );
		tvSendTime.setText( getTimeFormat( isAMorPM, hour, minute ) );
		tvAlarmDate.setText( year + "/" + ( month + 1 ) + "/" + day );
		tvAlarmTime.setText( getTimeFormat( isAMorPM, hour, minute ) );

		jellyToggleButton.setDuration( 100 );
		jellyToggleButton.setOnStateChangeListener( new JellyToggleButton.OnStateChangeListener() {

			@Override
			public void onStateChange( float process, State state, JellyToggleButton jtb ) {

				if ( state == State.RIGHT )
					showAlarmView( true );
				else
					showAlarmView( false );
			}
		} );
	}

	private void showAlarmView( boolean b ) {

		if ( b ) {
			fakeView2.setVisibility( View.VISIBLE );
			imageView_sendAlarmDate.setVisibility( View.VISIBLE );
			imageView_sendAlarmTime.setVisibility( View.VISIBLE );
			tvAlarmDate.setVisibility( View.VISIBLE );
			tvAlarmTime.setVisibility( View.VISIBLE );
		}
		else {
			fakeView2.setVisibility( View.GONE );
			imageView_sendAlarmDate.setVisibility( View.GONE );
			imageView_sendAlarmTime.setVisibility( View.GONE );
			tvAlarmDate.setVisibility( View.GONE );
			tvAlarmTime.setVisibility( View.GONE );
		}
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate( R.menu.add_send_writing, menu );
		return super.onCreateOptionsMenu( menu );
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch (item.getItemId()){
			case android.R.id.home:
				finish();
				return true;
			case R.id.send_write_finish:
				if (checkInput()){
					writeSendForm( realm );
					finish();
					return true;
				}else{
					Toast.makeText(WritingSendActivity.this,"모두 입력해주세요",Toast.LENGTH_LONG).show();
				}
		}
		return super.onOptionsItemSelected(item);
	}

	private String getTimeFormat( int isAMorPM, int hour, int minute ) {

		String timeFormat = "";
		switch ( isAMorPM ) {
			case Calendar.AM:
				timeFormat = "오전 " + String.valueOf( String.format( "%02d", hour ) ) + " : " + String.valueOf( String.format( "%02d", minute ) );
				break;
			case Calendar.PM:
				timeFormat = "오후 " + String.valueOf( String.format( "%02d", hour ) ) + " : " + String.valueOf( String.format( "%02d", minute ) );
				break;
		}
		return timeFormat;
	}

	private void writeSendForm( Realm realm ) {

		realm.executeTransaction( new Realm.Transaction() {

			@Override
			public void execute( Realm realm ) {

				Number num = realm.where( SendMoneyRealmObject.class ).max( "id" );
				int nextID;
				if ( num == null ) {
					nextID = 1;
				}
				else {
					nextID = num.intValue() + 1;
				}
				if(sendMoneyRealmObject == null) {
					sendMoneyRealmObject = new SendMoneyRealmObject();
					sendMoneyRealmObject.setId( nextID );
				}
				sendMoneyRealmObject.setTitle( etWritingTitle.getText().toString() );
				sendMoneyRealmObject.setCreditor( etWritingCreditor.getText().toString() );
				sendMoneyRealmObject.setBankType( tvSendBank.getText().toString() );
				sendMoneyRealmObject.setAccount( etEtWritingAccount.getText().toString() );
				sendMoneyRealmObject.setDateTime( calendar.getTime() );
				sendMoneyRealmObject.setAlarmTime( alarmCalendar.getTime() );
				long intPrice = Integer.valueOf( etEtWritingPrice.getText().toString() );
				sendMoneyRealmObject.setPrice( intPrice );
				realm.copyToRealm( sendMoneyRealmObject );

			}
		} );
	}

	private boolean checkInput() {
		if (tvSendBank.getText() != null
				&& etWritingTitle.getText().toString() !=null
				&& etWritingCreditor.getText().toString() != null
				&& etEtWritingAccount.getText().toString() != null
				&& etEtWritingPrice.getText().toString() != null){
			return true;
		}
		return false;
	}

	@Override
	public void onSelectBank( String bank ) {
		tvSendBank.setText( bank );
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		realm.close();
	}
}