package com.omjoonkim.doyouremember.app.frequentlyusedaccount;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.frequentlyusedaccount.adapter.FrequentlyUesdAccountAdapter;
import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by owner on 2017-01-17.
 */

public class FrequentlyUsedAccountModel {

    private Realm realm = null;
    private List<FrequentlyUesdAccountAdapter.ItemView> items;

    public void loadData(FrequentlyUsedAccountPresenterImpl presenter) {

        items = new ArrayList<>();
        realm = AppRealm.get().DylRealm();

        RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class).findAll();

        for (int i = 0; i < result.size(); i++) {
            items.add(new FrequentlyUesdAccountAdapter.ItemView(result.get(i).getName()
                    , result.get(i).getAccountList().get(0).getBankType() + " " + result.get(i).getAccountList().get(0).getAccountNumber()
                    , R.mipmap.ic_launcher));
                    //Todo 랜덤이미지 넣기(사진받으면)
        }

        presenter.setView(items);

//        realm.close();
    }

    //Todo 쿼리할떄 primaryKey를 어떻게 써먹나? 저건 자동생성인데 , 문자열로만 비교해야하나? 중복일때는?
    public void deleteData(String info) {
        String account = info.substring(3);

        final RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class)
                .equalTo("accountList.accountNumber",account)
                .findAll();
        final RealmResults<AccountRealmObject> result2 = realm.where(AccountRealmObject.class)
                .equalTo("accountNumber", account)
                .findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PersonRealmObject personRealmObject = result.get(0);
                AccountRealmObject accountRealmObject = result2.get(0);
                personRealmObject.deleteFromRealm();
                accountRealmObject.deleteFromRealm();
            }
        });
    }
}












