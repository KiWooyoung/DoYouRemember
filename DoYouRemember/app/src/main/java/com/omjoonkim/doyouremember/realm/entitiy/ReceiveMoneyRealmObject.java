package com.omjoonkim.doyouremember.realm.entitiy;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ReceiveMoneyRealmObject extends RealmObject {

	@PrimaryKey
	private long id;
	private String title;
	private RealmList<DebtorRealmObject> debtorList = null;
	private NotificationRealmObject notification;

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setNotification(NotificationRealmObject notification) {
		this.notification = notification;
	}

	public ReceiveMoneyRealmObject setDebtorList(RealmList<DebtorRealmObject> debtorList) {
		this.debtorList = debtorList;
		return this;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public RealmList<DebtorRealmObject> getDebtorList() {
		return debtorList;
	}

	public NotificationRealmObject getNotification() {
		return notification;
	}
}
