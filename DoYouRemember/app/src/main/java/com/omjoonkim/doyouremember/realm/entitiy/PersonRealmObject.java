package com.omjoonkim.doyouremember.realm.entitiy;


import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class PersonRealmObject extends RealmObject{

	@PrimaryKey
	long id;

	@Index
	String name;

	RealmList<AccountRealmObject> accountList;

	@Index
	boolean isMine;

	public long getId() {

		return id;
	}

	public PersonRealmObject setId( long id ) {

		this.id = id;
		return this;
	}

	public String getName() {

		return name;
	}

	public PersonRealmObject setName( String name ) {

		this.name = name;
		return this;
	}

	public RealmList<AccountRealmObject> getAccountList() {

		return accountList;
	}

	public PersonRealmObject setAccountList( RealmList<AccountRealmObject> accountList ) {

		this.accountList = accountList;
		return this;
	}

	public boolean isMine() {

		return isMine;
	}

	public PersonRealmObject setMine( boolean mine ) {

		isMine = mine;
		return this;
	}
}
