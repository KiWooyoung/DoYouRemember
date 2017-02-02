package com.omjoonkim.doyouremember.realm;


import io.realm.Realm;
import io.realm.RealmObject;


public class CommonRealmObjcet extends RealmObject {
    //Todo 쓰는 이유?
    protected long id = -1;

    //Todo 이거 id값 증가 시키는거 같은데 어떻게 쓸려고 만든건지 물어보기.
    public CommonRealmObjcet add(Realm realm) {

        if (this.isValid()) return this;
        Number id = realm.where(this.getClass()).max("id");
        if (null == id)
            this.id = 0;
        else
            this.id = id.longValue() + 1;
        return realm.copyToRealm(this);
    }

    public static int getNextKey(Realm realm, Class clazz ) {
        try {
            return realm.where(clazz).max("id").intValue() + 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            return 0;
        }
    }
}
