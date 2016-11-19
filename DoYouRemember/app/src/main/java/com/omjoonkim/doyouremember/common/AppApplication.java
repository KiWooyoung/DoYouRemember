package com.omjoonkim.doyouremember.common;


import android.app.Application;

import com.omjoonkim.doyouremember.realm.AppRealm;


public class AppApplication extends Application {

	@Override
	public void onCreate() {

		super.onCreate();
		AppRealm.get().init( this );

	}
}
