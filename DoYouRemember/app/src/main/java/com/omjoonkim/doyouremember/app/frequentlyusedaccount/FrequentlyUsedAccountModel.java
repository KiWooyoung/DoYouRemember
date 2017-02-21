package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import android.util.Log;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;
import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by owner on 2017-01-17.
 */

class FrequentlyUsedAccountModel {

    private Realm realm = null;

    void loadData(FrequentlyUsedAccountPresenterImpl presenter ) {

        List<FrequentlyUesdAccountAdapter.ItemView> items = new ArrayList<>();
        realm = AppRealm.get().DylRealm();

        RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
                .equalTo("accountList.isMine", false)
                .findAll();
        result = result.sort("id", Sort.ASCENDING);

        for (PersonRealmObject personRealmObject : result)
            items.add(new FrequentlyUesdAccountAdapter.ItemView(personRealmObject.getId(),personRealmObject.getName()
                    , personRealmObject.getAccountList().get(0).getBankType() + " " + personRealmObject.getAccountList().get(0).getAccountNumber()
                    , R.mipmap.ic_launcher));
            //Todo 랜덤이미지 넣기(사진받으면)

        presenter.setView(items);

        realm.close();
    }

    //Todo 쿼리할떄 primaryKey를 어떻게 써먹나? 저건 자동생성인데 , 문자열로만 비교해야하나? 중복일때는?
    void deleteData(String info) {

        realm = AppRealm.get().DylRealm();

        final String account = info.substring(3);
        Log.i("MyTag", account);

//        final RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
//                .equalTo("accountList.accountNumber",account)
//                .findAll();
//        final RealmResults<AccountRealmObject> result2 = realm.where(AccountRealmObject.class)
//                .equalTo("accountNumber", account)
//                .findAll();
        final RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class).findAll();
        final RealmResults<AccountRealmObject> result2 = realm.where(AccountRealmObject.class).findAll();


        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                PersonRealmObject personRealmObject = result.where().equalTo("accountList.accountNumber", account).findFirst();
                AccountRealmObject accountRealmObject = result2.where().equalTo("accountNumber", account).findFirst();

                if (personRealmObject != null) {
                    personRealmObject.deleteFromRealm();
                }
                if (accountRealmObject != null) {
                    accountRealmObject.deleteFromRealm();
                }

//                PersonRealmObject personRealmObject = result.get(0);
//                AccountRealmObject accountRealmObject = result2.get(0);
//                personRealmObject.deleteFromRealm();
//                accountRealmObject.deleteFromRealm();

//                result.deleteAllFromRealm();
//                result2.deleteAllFromRealm();
                //Todo 계좌를 중복X 할거기 때문에 전부 삭제 해도 된다.
            }
        });

        realm.close();
    }
}












