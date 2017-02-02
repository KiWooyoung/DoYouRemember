package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;

import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.CommonRealmObjcet;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import io.realm.Realm;

/**
 * Created by wooyoungki on 2017. 1. 21..
 */

public class RegisterModel {

    private Realm realm;

    void registerFrequentlyUsedAccount(final String name, final String accountNumber, final String bank) {
        realm = AppRealm.get().DylRealm();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PersonRealmObject personRealmObject = realm.createObject(PersonRealmObject.class,CommonRealmObjcet.getNextKey(realm,PersonRealmObject.class));
                personRealmObject.setName(name);
//                personRealmObject.setId(CommonRealmObjcet.getNextKey(realm,PersonRealmObject.class));

                AccountRealmObject accountRealmObject = realm.createObject(AccountRealmObject.class,CommonRealmObjcet.getNextKey(realm, AccountRealmObject.class));
                accountRealmObject.setAccountNumber(accountNumber);
                accountRealmObject.setBankType(bank);
                accountRealmObject.setFavorite(false);
//                accountRealmObject.setId(CommonRealmObjcet.getNextKey(realm, AccountRealmObject.class));
                personRealmObject.getAccountList().add(accountRealmObject);
            }
        });
    }
}
