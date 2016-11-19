package com.omjoonkim.doyouremember.realm.entitiy;


import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class AccountRealmObject extends RealmObject {

	@PrimaryKey
	long id = -1;

	@Index
	String accountNumber = null;

	@Index
	String bankType = null;

	@Index
	PersonRealmObject person;

	@Index
	boolean favorite = false;

	public long getId() {

		return id;
	}

	public AccountRealmObject setId( long id ) {

		this.id = id;
		return this;
	}

	public String getAccountNumber() {

		return accountNumber;
	}

	public AccountRealmObject setAccountNumber( String accountNumber ) {

		this.accountNumber = accountNumber;
		return this;
	}

	public AccountRealmObject setBankType( String bankType ) {

		this.bankType = bankType;
		return this;
	}

	public boolean isFavorite() {

		return favorite;
	}

	public AccountRealmObject setFavorite( boolean favorite ) {

		this.favorite = favorite;
		return this;
	}

	public String getBankType() {

		return bankType;
	}

	public BankType getBankTypeEnum() {

		return BankType.valueOf( bankType );
	}

	enum BankType {
		SINHAN( "sinhan" );

		String bank;

		BankType( String bank ) {

			this.bank = bank;
		}
	}
}
