package com.omjoonkim.doyouremember.common;


import android.app.Application;

import com.facebook.stetho.Stetho;
import com.omjoonkim.doyouremember.realm.AppRealm;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;


public class AppApplication extends Application {

	@Override
	public void onCreate() {

		super.onCreate();
		AppRealm.get().init( this );

		Stetho.initialize(
				Stetho.newInitializerBuilder(this)
						.enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
						.enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
						.build());

	}
}
