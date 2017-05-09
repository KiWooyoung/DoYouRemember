package com.omjoonkim.doyouremember.app.writing.receivemoney;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;
import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.writing.receivemoney.listener.OnWritingReceiveClickListener;
import com.omjoonkim.doyouremember.app.writing.receivemoney.presenter.WritingReceivePresenter;
import com.omjoonkim.doyouremember.app.writing.receivemoney.presenter.WritingReceivePresenterImpl;
import com.omjoonkim.doyouremember.realm.entitiy.DebtorRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.NotificationRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.ReceiveMoneyRealmObject;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import io.realm.Realm;

public class WritingReceiveActivity extends AppCompatActivity implements WritingReceivePresenter.View{

	public static final String TAG = WritingReceiveActivity.class.getSimpleName();

	@BindView( R.id.toolbar_writing )
	Toolbar tbWriting;

	@BindView(R.id.view_writing_receive_add_people)
	RelativeLayout vAddBtn;

	@BindView( R.id.editText_writing_receive_title )
	EditText etWritingTitle;

	@BindView(R.id.recyclerView_writing_receive)
	RecyclerView recyclerView;

	@BindView( R.id.toggle_alarm_activate )
	JellyToggleButton jellyToggleButton;

	@BindView( R.id.textView_writing_receive_deadline_date )
	TextView tvReceiveDate;

	@BindView( R.id.textView_writing_receive_deadline_time )
	TextView tvReceiveTime;

	@BindView( R.id.textView_writing_receive_alarm_date )
	TextView tvAlarmDate;

	@BindView( R.id.textView_writing_receive_alarm_time )
	TextView tvAlarmTime;

	@BindView( R.id.fakeView2 )
	View fakeView2;

	@BindView( R.id.imageView_receiveAlarmDate )
	View imageView_sendAlarmDate;

	@BindView( R.id.imageView_receiveAlarmTime )
	View imageView_sendAlarmTime;

	private WritingReceiveAdapter adapter;
	private WritingReceivePresenter presenter;

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

	private Realm realm;
	ReceiveMoneyRealmObject receiveMoneyRealmObject;

	@OnFocusChange( R.id.editText_writing_receive_title )
	void onFocusChangeTitle( boolean hasFocus ) {
		if ( hasFocus ) {
			etWritingTitle.setBackgroundResource( R.drawable.back_change );
		}
		else {
			etWritingTitle.setBackgroundResource( R.drawable.back );
		}
	}

	@OnClick(R.id.btn_writing_receive_add_people)
	void onClickAddPeople(){
		adapter.addData();
		//recyclerView.smoothScrollToPosition(adapter.getItemCount()-1);
	}

	@OnClick( R.id.textView_writing_receive_deadline_date )
	void onClickReceiveDate() {
		new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {
				WritingReceiveActivity.this.year = year;
				WritingReceiveActivity.this.month = month;
				day = dayOfMonth;
				tvReceiveDate.setText( year + "/" + ( month + 1 ) + "/" + dayOfMonth );
				calendar.set( year, month, dayOfMonth );
			}
		}, year, month, day ).show();
	}

	@OnClick( R.id.textView_writing_receive_deadline_time )
	void onClickReceiveTime() {

		new TimePickerDialog( this, new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {
				hour = hourOfDay;
				WritingReceiveActivity.this.minute = minute;
				tvReceiveTime.setText( getTimeFormat( hourOfDay > 12 ? Calendar.PM : Calendar.AM, hourOfDay - 12 > 0 ? hourOfDay - 12 : hourOfDay, minute ) );
				calendar.set( Calendar.HOUR_OF_DAY, hourOfDay );
				calendar.set( Calendar.MINUTE, minute );
			}
		}, hour, minute, false ).show();
	}

	@OnClick( R.id.textView_writing_receive_alarm_date )
	void onClickAlarmDate() {

		new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet( DatePicker view, int year, int month, int dayOfMonth ) {
				WritingReceiveActivity.this.alarmYear = year;
				WritingReceiveActivity.this.alarmMonth = month;
				alarmDay = dayOfMonth;
				tvAlarmDate.setText( year + "/" + ( month + 1 ) + "/" + dayOfMonth );
				alarmCalendar.set( year, month, dayOfMonth );
			}
		}, alarmYear, alarmMonth, alarmDay ).show();
	}

	@OnClick( R.id.textView_writing_receive_alarm_time )
	void onClickAlarmTime() {

		new TimePickerDialog( this, new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet( TimePicker view, int hourOfDay, int minute ) {

				alarmHour = hourOfDay;
				WritingReceiveActivity.this.alarmMinute = minute;
				tvAlarmTime.setText( getTimeFormat( hourOfDay > 12 ? Calendar.PM : Calendar.AM, hourOfDay - 12 > 0 ? hourOfDay - 12 : hourOfDay, minute ) );
				alarmCalendar.set( Calendar.HOUR_OF_DAY, hourOfDay );
				alarmCalendar.set( Calendar.MINUTE, minute );
			}
		}, alarmHour, alarmMinute, false ).show();
	}

	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_writing_receive );

		ButterKnife.bind(this);
		realm = Realm.getDefaultInstance();

		presenter = new WritingReceivePresenterImpl();
		presenter.setView(this);

		initRecyclerViewInit();
		initView();
	}

	private void initRecyclerViewInit() {
		adapter = new WritingReceiveAdapter(getApplicationContext());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		adapter.setClickListener(new OnWritingReceiveClickListener() {
			@Override
			public void onClickSendDelete(int position) {
				presenter.onClickDelete(position);
			}
		});
		recyclerView.setAdapter(adapter);
	}

	@Override
	public void deleteWritingReceivePeople(int position) {
		adapter.deleteData(position);
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

		tvReceiveDate.setText( year + "/" + ( month + 1 ) + "/" + day );
		tvReceiveTime.setText( getTimeFormat( isAMorPM, hour, minute ) );
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
				if (etWritingTitle.getText().toString().trim().length() > 0 && checkItem()){
					writeReceiveForm(realm);
					finish();
					return true;
				}else{
					Toast.makeText(WritingReceiveActivity.this,"모두 입력해주세요",Toast.LENGTH_LONG).show();
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

	private boolean checkItem() {
		for(int i = 0; i < adapter.getItemCount(); i++) {
			View allView = recyclerView.getLayoutManager().findViewByPosition(i);
			EditText etReceiveDebtor = (EditText)allView.findViewById(R.id.editText_writing_receive_debtor);
			EditText etReceivePrice = (EditText)allView.findViewById(R.id.editText_writing_receive_price);

			if (etReceiveDebtor.getText().toString().trim().length() > 0
					&& etReceivePrice.getText().toString().trim().length() > 0) {
				return true;
			}
		}
		return false;
	}

	private void writeReceiveForm( Realm realm ) {
		realm.executeTransaction( new Realm.Transaction() {
			  @Override
			  public void execute(Realm realm) {

				  Number num = realm.where( ReceiveMoneyRealmObject.class ).max( "id" );
				  int nextID;
				  if ( num == null ) {
					  nextID = 1;
				  }
				  else {
					  nextID = num.intValue() + 1;
				  }

				  if(receiveMoneyRealmObject == null) {
					  receiveMoneyRealmObject = realm.createObject(ReceiveMoneyRealmObject.class, nextID);
				  }

				  receiveMoneyRealmObject.setTitle(etWritingTitle.getText().toString());
				  NotificationRealmObject notificationRealmObject = realm.createObject(NotificationRealmObject.class, nextID);
				  notificationRealmObject.setWorkType("receive");
				  notificationRealmObject.setDateTime(calendar.getTime());
				  notificationRealmObject.setAlarmTime(alarmCalendar.getTime());

				  receiveMoneyRealmObject.setNotification(notificationRealmObject);

				  for (int i = 0; i < adapter.getItemCount(); i++) {
					  View allView = recyclerView.getLayoutManager().findViewByPosition(i);
					  EditText etReceiveDebtor = (EditText) allView.findViewById(R.id.editText_writing_receive_debtor);
					  EditText etReceivePrice = (EditText) allView.findViewById(R.id.editText_writing_receive_price);

					  Number debtorNum = realm.where( DebtorRealmObject.class ).max( "id" );
					  int debtorID;

					  if ( debtorNum == null ) {
						  debtorID = 1;
					  } else {
						  debtorID = debtorNum.intValue() + 1;
					  }

					  DebtorRealmObject debtorRealmObject = realm.createObject(DebtorRealmObject.class, debtorID);
					  debtorRealmObject.setName(etReceiveDebtor.getText().toString());
					  long intPrice = Integer.valueOf(etReceivePrice.getText().toString());
					  debtorRealmObject.setPrice(intPrice);
					  receiveMoneyRealmObject.getDebtorList().add(debtorRealmObject);
				  }
			  }
   	    });
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		realm.close();
	}

}