package com.omjoonkim.doyouremember.realm.entitiy;


import com.omjoonkim.doyouremember.realm.CommonRealmObjcet;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class NotificationRealmObject extends RealmObject {

	@PrimaryKey
	long id;

	Date dateTime;

	long optionTime;

	public long getId() {

		return id;
	}

	public NotificationRealmObject setId( long id ) {

		this.id = id;
		return this;
	}

	public Date getDeadline() {

		return dateTime;
	}

	public NotificationRealmObject setDeadline( Date deadline ) {

		this.dateTime = deadline;
		return this;
	}

	public long getOptionTime() {

		return optionTime;
	}

	public NotificationRealmObject setOptionTime( long optionTime ) {

		this.optionTime = optionTime;
		return this;
	}
}
