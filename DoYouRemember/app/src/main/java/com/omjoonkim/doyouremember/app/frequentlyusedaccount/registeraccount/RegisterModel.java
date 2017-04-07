package com.omjoonkim.doyouremember.app.frequentlyusedaccount.registeraccount;

import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import java.util.Random;

import io.realm.Realm;
import io.realm.RealmResults;

public class RegisterModel {

    private Realm realm;

    void registerFrequentlyUsedAccount(final String name, final String accountNumber, final String bank) {

       /* if (realm == null)*/ //Todo 1.안드로이드에서 널체크를 하는 본질적인 이유? 2.이런 메소드 호출할때마다 인스턴스를 생성했야 하나, 아니면 생성했다 다시 죽이면 GC되서 상관없는건가 ANd 여기서 WeakReference 나 일반 Reference 차이가 생기는건가
        realm = AppRealm.get().DylRealm();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) { //Todo 자동 박싱 언박싱 공부 - 개념확실X , 2.기본키는 항상 필요햇다 맞다.
                //Todo 아래 key 추가하는거 왜저렇게 되는건지 알기. -알음
                Number num = realm.where(PersonRealmObject.class).max("id");
                long ID;
                if (num == null) {
                    ID = 1;
                } else {
                    ID = num.intValue() + 1;
                }
                //Todo 여기서 person,account에 둘다 기본키 넣어야하나?
                PersonRealmObject personRealmObject = realm.createObject(PersonRealmObject.class, ID);
                personRealmObject.setName(name);

                final Random rnd = new Random();
                String fruitNumber = "fruit" + rnd.nextInt(9);
                personRealmObject.setProfileImage(fruitNumber);

                AccountRealmObject accountRealmObject = realm.createObject(AccountRealmObject.class, ID);
                accountRealmObject.setAccountNumber(accountNumber);
                accountRealmObject.setBankType(bank);
                accountRealmObject.setMine(false);
                accountRealmObject.setFavorite(false);

                personRealmObject.getAccountList().add(accountRealmObject);//Todo 여기서 비관리 account객체 만들어서 넣는 방법 시도해보자.
            }                                                   //Todo 보내자,받자에서 조회할떄 account는 필요 없을거 같은데.
        });

        realm.close();
    }

    public void reviseFrequentlyUsedAccount(final String accountNumber, final String newName, final String newAccountNumber, final String newBankType) {

        realm = AppRealm.get().DylRealm();

        final RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class).findAll();
        final RealmResults<AccountRealmObject> result2 = realm.where(AccountRealmObject.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                PersonRealmObject personRealmObject = result.where().equalTo("accountList.accountNumber", accountNumber).findFirst();
                AccountRealmObject accountRealmObject = result2.where().equalTo("accountNumber", accountNumber).findFirst();

                personRealmObject.setName(newName);
                accountRealmObject.setAccountNumber(newAccountNumber);
                accountRealmObject.setBankType(newBankType);
            }
        });


        realm.close();
    }

    public boolean checkOverlapAccount(String accountNumber) {

        realm = AppRealm.get().DylRealm();

        RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
                .equalTo("accountList.accountNumber", accountNumber).findAll();

        //Todo realm.close(); 이거 어디서 쒀줘야할까
        return (result.size() > 0);

    }

    public boolean checkReviseOverlapAccount(String accountNumber, String newAccountNumber) {

        realm = AppRealm.get().DylRealm();

        RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
                .equalTo("accountList.accountNumber", newAccountNumber).findAll();

        if (accountNumber.equals(newAccountNumber))
            return false;

        return (result.size() > 0);
    }
}
