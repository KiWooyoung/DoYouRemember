package com.omjoonkim.doyouremember.realm;


import com.omjoonkim.doyouremember.realm.entitiy.AccountRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.NotificationRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.PersonRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.ReceiveMoneyRealmObject;
import com.omjoonkim.doyouremember.realm.entitiy.SendMoneyRealmObject;

import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.annotations.RealmModule;


@RealmModule( classes = { PersonRealmObject.class, AccountRealmObject.class, NotificationRealmObject.class, SendMoneyRealmObject.class, ReceiveMoneyRealmObject.class } )

public class DoYouRememberMigration implements RealmMigration {

	public static final int SCHEMA_VERSION = 1;

	private static DoYouRememberMigration instance;

	private DoYouRememberMigration() {

	}

	@Override
	public void migrate( DynamicRealm dynamicRealm, long oldVersion, long newVersion ) {

	}


	public static DoYouRememberMigration getInstance() {

		if ( null == instance )
			instance = new DoYouRememberMigration();

		return instance;
	}

}
