package com.ageone.naladoni.External.Extensions.Realm

import com.ageone.naladoni.SCAG.Stock
import io.realm.Realm
import io.realm.RealmObject
import io.realm.RealmResults
import timber.log.Timber

fun add(some: RealmObject): Boolean {
    return try {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        realm.copyToRealmOrUpdate(some)
        realm.commitTransaction()
        true
    } catch (e: Exception) {
        Timber.e("$e")
        false
    }
}

fun deleteAll(some: RealmResults<out RealmObject>): Boolean {
    return try {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        some.deleteAllFromRealm()
        realm.commitTransaction()
        true
    } catch (e: Exception) {
        Timber.e("$e")
        false
    }
}