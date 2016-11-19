package com.omjoonkim.doyouremember.realm.entitiy;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class SendMoneyRealmObject extends RealmObject {

	@PrimaryKey
	long id = -1;

	String title;

	long timeStamp = 0;

	AccountRealmObject creditorAccount;

	int price;

	RealmList<NotificationRealmObject> notifications = new RealmList<>();




}
