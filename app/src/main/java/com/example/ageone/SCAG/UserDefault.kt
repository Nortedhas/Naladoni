package com.example.ageone.SCAG
import io.realm.Realm
import net.alexandroid.shpref.ShPref


object UserData {

	var phone: String
		get() = ShPref.getString("userDataPhone", "")
		set(value) = ShPref.put("userDataPhone", value)

	var role: String
		get() = ShPref.getString("userDataRole", "")
		set(value) = ShPref.put("userDataRole", value)

	var name: String
		get() = ShPref.getString("userDataName", "")
		set(value) = ShPref.put("userDataName", value)

	var vipAccessTo: Int
		get() = ShPref.getInt("userDataVipAccessTo", 0)
		set(value) = ShPref.put("userDataVipAccessTo", value)

	var email: String
		get() = ShPref.getString("userDataEmail", "")
		set(value) = ShPref.put("userDataEmail", value)


}