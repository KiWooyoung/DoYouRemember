package com.omjoonkim.doyouremember.app.myaccount;

import com.omjoonkim.doyouremember.R;
import com.omjoonkim.doyouremember.app.myaccount.adapter.MyAccountAdapter;
import com.omjoonkim.doyouremember.realm.AppRealm;
import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;


public class MyAccountModel {

    private List<MyAccountAdapter.ItemView> items;
    private Realm realm;
    MyAccountPresenterImpl presenter = null;

    public void loadData(MyAccountPresenterImpl presenter) {

        realm = AppRealm.get().DylRealm();
        items = new ArrayList<>();
        this.presenter = presenter;

        RealmResults<AccountRealmObject> result = realm.where(AccountRealmObject.class)
                .equalTo("isMine", true)
                .findAll();

        result = result.sort("bankType", Sort.ASCENDING);

        for (int i = 0; i < result.size(); i++) {

            int blankStar = R.drawable.bookmark_blank_star;
            int blueStar = R.drawable.bookmark_blue_star;

            if (result.get(i).isFavorite()) {
                items.add(new MyAccountAdapter.ItemView(result.get(i).getBankType() + " "
                        + result.get(i).getAccountNumber(), blueStar));
            } else {
                items.add(new MyAccountAdapter.ItemView(result.get(i).getBankType() + " "
                        + result.get(i).getAccountNumber(), blankStar));
            }
        }

        presenter.setView(items);
        realm.close();
    }

    public void updateFavoriteMyAccount(final String favoriteMyAccount) {

        realm = AppRealm.get().DylRealm();
        final RealmResults<AccountRealmObject> result = realm.where(AccountRealmObject.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AccountRealmObject accountRealmObject = result.where()
                        .equalTo("accountNumber", favoriteMyAccount)
                        .findFirst();
                RealmResults<AccountRealmObject> setNormalAccountRealmObject = realm.where(AccountRealmObject.class).findAll();

                if (accountRealmObject.isFavorite()) {
                    accountRealmObject.setFavorite(false);
                } else {
                    for (int i = 0; i < setNormalAccountRealmObject.size(); i++) {
                        setNormalAccountRealmObject.get(i).setFavorite(false);
                    }
                    accountRealmObject.setFavorite(true);
                }
            }
        });

        realm.close();
        loadData(presenter);
    }

    public String setMainAccountBoxData() {

        realm = AppRealm.get().DylRealm();
        String myAccount;
        RealmResults<AccountRealmObject> accountRealmObjects = realm.where(AccountRealmObject.class).findAll();

        for (int i = 0; i < accountRealmObjects.size(); i++) {
            if (accountRealmObjects.get(i).isFavorite()) {
                myAccount = accountRealmObjects.get(i).getBankType() + " " +accountRealmObjects.get(i).getAccountNumber();
                return myAccount;
            }
        }

        return ""; //Todo return 하면 realm.close()는 어디서 하지?
    }

    public void deleteData(final String accountNumber) {

        realm = AppRealm.get().DylRealm();

        final RealmResults<PersonRealmObject> result = realm.where(PersonRealmObject.class).findAll();
        final RealmResults<AccountRealmObject> result2 = realm.where(AccountRealmObject.class).findAll();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PersonRealmObject personRealmObject = result.where().equalTo("accountList.accountNumber", accountNumber).findFirst();
                AccountRealmObject accountRealmObject = result2.where().equalTo("accountNumber", accountNumber).findFirst();

                if (personRealmObject != null) {
                    personRealmObject.deleteFromRealm();
                }
                if (accountRealmObject != null) {
                    accountRealmObject.deleteFromRealm();
                }
            }
        });

        realm.close();
    }
}
