package com.omjoonkim.doyouremember.realm.entitiy;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class PersonRealmObject extends RealmObject {

	@PrimaryKey
	private //Todo Account,Person 모델에 PrimaryKey로 long id 가 꼭 필요할까요?
	long id;

	@Index
	private
	String name;

	private
	String profileImage;


	private RealmList<AccountRealmObject> accountList = null;

//	@Index
//	boolean isMine; //Todo person -> account 로 변경

	public long getId() {

		return id;
	}

	public PersonRealmObject setId( int id ) {

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

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getProfileImage() {
		return profileImage;
	}



//	public boolean isMine() {
//
//		return isMine;
//	}
//
//	public PersonRealmObject setMine( boolean mine ) {
//
//		isMine = mine;
//		return this;
//	}
}
