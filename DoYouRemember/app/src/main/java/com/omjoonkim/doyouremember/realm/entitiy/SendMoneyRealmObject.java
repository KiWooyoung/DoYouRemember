package com.omjoonkim.doyouremember.realm.entitiy;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class SendMoneyRealmObject extends RealmObject {

	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int BLUE = 2;
	public static final int GRAY = 3;

	@PrimaryKey
	private long id;
	private String title;
	private String creditor;
	private String bankType;
	private String account;
	private long price;
	private Date dateTime;
	private Date alarmTime;

	public void setId( long id ) {

		this.id = id;
	}

	public void setTitle( String title ) {

		this.title = title;
	}

	public void setCreditor( String creditor ) {

		this.creditor = creditor;
	}

	public void setBankType( String bankType ) {

		this.bankType = bankType;
	}

	public void setAccount( String account ) {

		this.account = account;
	}

	public void setPrice( long price ) {

		this.price = price;
	}

	public long getId() {

		return id;
	}

	public String getTitle() {

		return title;
	}

	public String getCreditor() {

		return creditor;
	}

	public String getBankType() {

		return bankType;
	}

	public String getAccount() {

		return account;
	}

	public long getPrice() {

		return price;
	}

	public Date getDateTime() {

		return dateTime;
	}

	public SendMoneyRealmObject setDateTime( Date dateTime ) {

		this.dateTime = dateTime;
		return this;
	}

	public Date getAlarmTime() {

		return alarmTime;
	}

	public SendMoneyRealmObject setAlarmTime( Date alarmTime ) {

		this.alarmTime = alarmTime;
		return this;
	}

	public String getTheLastTime() {

		long diff = ( dateTime.getTime() - System.currentTimeMillis() ) / 1000;
		if ( diff < 60 )
			return "곧";
		else if ( diff < 60 * 60 )
			return ( diff / 60 + "분 전" );
		else if ( diff < 60 * 60 * 24 )
			return ( diff / 60 / 60 + "시간 전" );
		else if ( diff < 60 * 60 * 24 * 7 )
			return diff / 60 / 60 / 24 + "일 전";
		else if ( diff < 60 * 60 * 24 * 7 * 4 )
			return ( diff / 60 / 60 / 24 / 7 + "주 전" );
		else
			return ( new SimpleDateFormat( "MM월 dd일" ).format( dateTime.getTime() ) );
	}

	public int getDeadLineColor() {

		long diffHour = ( dateTime.getTime()- System.currentTimeMillis() ) / ( 1000 * 60 * 60 );
		if ( diffHour < 12 )
			return RED;
		else if ( diffHour < 24 )
			return YELLOW;
		else if ( diffHour < 24 * 12 * 3 )
			return BLUE;
		else
			return GRAY;
	}


}
