package com.omjoonkim.doyouremember.realm.entitiy;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class NotificationRealmObject extends RealmObject {

	public static final int RED = 0;
	public static final int YELLOW = 1;
	public static final int BLUE = 2;
	public static final int GRAY = 3;

	@PrimaryKey
	private long id;
	private String workType = null;
	private Date dateTime;
	private Date alarmTime;

	public void setId(long id) {
		this.id = id;
	}

	public NotificationRealmObject setWorkType(String workType) {
		this.workType = workType;
		return this;
	}

	public void setDateTime( Date dateTime ) {
		this.dateTime = dateTime;
	}

	public NotificationRealmObject setAlarmTime( Date alarmTime ) {
		this.alarmTime = alarmTime;
		return this;
	}

	public String getWorkType() {
		return workType;
	}

	public long getId() {
		return id;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public Date getAlarmTime() {
		return alarmTime;
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
