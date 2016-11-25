package com.omjoonkim.doyouremember.realm.entitiy;


import com.omjoonkim.doyouremember.realm.CommonRealmObjcet;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class ReceiveMoneyRealmObject extends RealmObject {

	@PrimaryKey
	long id;

	String title;

	long deadLine;

	RealmList<NotificationRealmObject> notifications;


	public long getId() {

		return id;
	}

	public ReceiveMoneyRealmObject setId( long id ) {

		this.id = id;
		return this;
	}

	public String getTitle() {

		return title;
	}

	public ReceiveMoneyRealmObject setTitle( String title ) {

		this.title = title;
		return this;
	}

	public long getDeadLine() {

		return deadLine;
	}

	public ReceiveMoneyRealmObject setDeadLine( long deadLine ) {

		this.deadLine = deadLine;
		return this;
	}

	public RealmList<NotificationRealmObject> getNotifications() {

		return notifications;
	}

	public ReceiveMoneyRealmObject setNotifications( RealmList<NotificationRealmObject> notifications ) {

		this.notifications = notifications;
		return this;
	}
}
