package com.omjoonkim.doyouremember.app.myaccount.registermyaccount;

import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by wooyoungki on 2017. 1. 29..
 */

public class RegisterMyAccountModel {

    private Realm realm;

    public void registerMyAccount(final String bankType, final String myAccountNumber) {

        realm = AppRealm.get().DylRealm();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number num = realm.where(PersonRealmObject.class).max("id");
                long ID;
                if (num == null) {
                    ID = 1;
                } else {
                    ID = num.intValue() + 1;
                }
                PersonRealmObject personRealmObject = realm.createObject(PersonRealmObject.class, ID);

                AccountRealmObject accountRealmObject = realm.createObject(AccountRealmObject.class, ID);
                accountRealmObject.setBankType(bankType);
                accountRealmObject.setAccountNumber(myAccountNumber);
                accountRealmObject.setMine(true);
                accountRealmObject.setFavorite(false);

                personRealmObject.getAccountList().add(accountRealmObject);

            }
        });
    }

    public void reviseMyAccount(final String accountNumber, final String newAccountNumber, final String newBankType) {
        realm = AppRealm.get().DylRealm();

        final RealmResults<AccountRealmObject> result = realm.where(AccountRealmObject.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                AccountRealmObject accountRealmObject = result.where().equalTo("accountNumber", accountNumber).findFirst();

                accountRealmObject.setAccountNumber(newAccountNumber);
                accountRealmObject.setBankType(newBankType);
            }
        });

        realm.close();
    }

    public boolean checkOverlapAccount(String myAccountNumber) {
        realm = AppRealm.get().DylRealm();

        RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
                .equalTo("accountList.accountNumber", myAccountNumber).findAll();

        //Todo realm.close(); 이거 어디서 쒀줘야할까
        return (result.size() > 0);
    }

    public boolean checkReviseOverlapAccount(String accountNumber, String newAccountNumber) {
        realm = AppRealm.get().DylRealm();

        RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
                .equalTo("accountList.accountNumber", newAccountNumber).findAll();

        return !accountNumber.equals(newAccountNumber) && (result.size() > 0);

    }

}
