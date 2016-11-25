package com.omjoonkim.doyouremember.realm;


import io.realm.Realm;
import io.realm.RealmObject;


public class CommonRealmObjcet extends RealmObject {

	protected long id = -1;

	public CommonRealmObjcet add( Realm realm ) {

		if ( this.isValid() ) return this;
		Number id = realm.where( this.getClass() ).max( "id" );
		if ( null == id )
			this.id = 0;
		else
			this.id = id.longValue() + 1;
		return realm.copyToRealm( this );
	}
}
