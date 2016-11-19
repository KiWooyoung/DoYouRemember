package com.omjoonkim.doyouremember.realm;


public class Account {

	long id = -1;

	String accountNumber = null;

	String bankType = null;

	boolean favorite = false;


	public BankType getBankType() {

		return BankType.valueOf( bankType );
	}
}

enum BankType {
	SINHAN( "sinhan" );

	String bank;

	BankType( String bank ) {

		this.bank = bank;
	}
}