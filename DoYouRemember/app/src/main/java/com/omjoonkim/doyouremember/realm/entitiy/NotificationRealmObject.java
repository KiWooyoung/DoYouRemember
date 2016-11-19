package com.omjoonkim.doyouremember.realm.entitiy;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class NotificationRealmObject extends RealmObject {

	@PrimaryKey
	long id = -1;

	long deadline;

	long optionTime;

	public long getId() {

		return id;
	}

	public NotificationRealmObject setId( long id ) {

		this.id = id;
		return this;
	}

	public long getDeadline() {

		return deadline;
	}

	public NotificationRealmObject setDeadline( long deadline ) {

		this.deadline = deadline;
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
