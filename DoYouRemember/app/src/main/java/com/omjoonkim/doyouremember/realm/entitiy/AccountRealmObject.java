package com.omjoonkim.doyouremember.realm.entitiy;


import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;


public class AccountRealmObject extends RealmObject {

    @PrimaryKey
    long id;

    @Index //Todo index도 물어보기 이거 있으면 primarykey 필요한가?
    String accountNumber = null;

    @Index
    String bankType = null;

//    PersonRealmObject person; //Todo 이게 아직 까지 필요있나해서 일단 주석처리하곘습니다.-우영

    @Index
	boolean isMine;

    @Index
    boolean favorite = false;

    public long getId() {
        return id;
    }

    public AccountRealmObject setId(int id) {
        this.id = id;
        return this;  //Todo 반환형을 객체 자신으로 하는 이유는? 궁금하네
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public AccountRealmObject setAccountNumber(String accountNumber) {

        this.accountNumber = accountNumber;
        return this;
    }

    public AccountRealmObject setBankType(String bankType) {

        this.bankType = bankType;
        return this;
    }

    public boolean isFavorite() {

        return favorite;
    }

    public AccountRealmObject setFavorite(boolean favorite) {

        this.favorite = favorite;
        return this;
    }

    public String getBankType() {

        return bankType;
    }

    public boolean isMine() {

        return isMine;
    }

    public AccountRealmObject setMine( boolean mine ) {

        isMine = mine;
        return this;
    }

    public BankType getBankTypeEnum() {

        return BankType.valueOf(bankType);
    }

    enum BankType {
        SINHAN("sinhan");

        String bank;

        BankType(String bank) {

            this.bank = bank;
        }
    }
}
