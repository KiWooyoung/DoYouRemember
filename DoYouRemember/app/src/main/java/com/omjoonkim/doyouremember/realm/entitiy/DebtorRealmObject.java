package com.omjoonkim.doyouremember.realm.entitiy;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

public class DebtorRealmObject extends RealmObject {

    @PrimaryKey
    private long id;

    @Index
    private String name;

    private long price;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }
}
