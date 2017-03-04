package com.omjoonkim.doyouremember.realm.entitiy;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class SendMoneyRealmObject extends RealmObject {

	@PrimaryKey
	private long id;
	private String title;
	private String creditor;
	private String bankType;
	private String account;
	private int price;

	public void setId(long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCreditor(String creditor) {
		this.creditor = creditor;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setPrice(int price) {
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

	public int getPrice() {
		return price;
	}
}
