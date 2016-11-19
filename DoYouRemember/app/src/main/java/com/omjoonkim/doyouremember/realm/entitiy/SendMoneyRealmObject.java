package com.omjoonkim.doyouremember.realm.entitiy;


import com.omjoonkim.doyouremember.realm.CommonRealmObjcet;

import io.realm.RealmList;


public class SendMoneyRealmObject extends CommonRealmObjcet {

	String title;

	long timeStamp = 0;

	AccountRealmObject creditorAccount;

	int price;

	RealmList<NotificationRealmObject> notifications = new RealmList<>();


	public String getTitle() {

		return title;
	}

	public SendMoneyRealmObject setTitle( String title ) {

		this.title = title;
		return this;
	}

	public long getTimeStamp() {

		return timeStamp;
	}

	public SendMoneyRealmObject setTimeStamp( long timeStamp ) {

		this.timeStamp = timeStamp;
		return this;
	}

	public AccountRealmObject getCreditorAccount() {

		return creditorAccount;
	}

	public SendMoneyRealmObject setCreditorAccount( AccountRealmObject creditorAccount ) {

		this.creditorAccount = creditorAccount;
		return this;
	}

	public int getPrice() {

		return price;
	}

	public SendMoneyRealmObject setPrice( int price ) {

		this.price = price;
		return this;
	}

	public RealmList<NotificationRealmObject> getNotifications() {

		return notifications;
	}

	public SendMoneyRealmObject setNotifications( RealmList<NotificationRealmObject> notifications ) {

		this.notifications = notifications;
		return this;
	}
}
