package com.omjoonkim.doyouremember.realm;


import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class AppRealm {

	private static AppRealm instance;
	private Context context;
	private RealmConfiguration DylRealm;

	public AppRealm() {

	}

	public void init( Context context ) {

		this.context = context;
		Realm.init( context );
		DylRealm( context );
	}

	private void DylRealm( Context context ) {

		DylRealm = new RealmConfiguration.Builder()
				//.schemaVersion( DoYouRememberMigration.SCHEMA_VERSION )
				//.deleteRealmIfMigrationNeeded()
				//.migration( DoYouRememberMigration.getInstance() )
//				.modules( DoYouRememberMigration.getInstance() )
				.build();
		Realm.setDefaultConfiguration( DylRealm );
	}

	public Realm DylRealm() {

		return Realm.getInstance( DylRealm );
	}

	public static AppRealm get() {  //Todo 이것만 Static 으로 한이유가 있는가?

		if ( null == instance ) {
			instance = new AppRealm();
		}

		return instance;
	}
}
