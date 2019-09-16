package com.example.ageone.SCAG
import io.realm.Realm
import net.alexandroid.shpref.ShPref


object ConfigDefault {

	var product12M: Int
		get() = ShPref.getInt("userDataProduct12M", 0)
		set(value) = ShPref.put("userDataProduct12M", value)

	var vipAccess3M: Int
		get() = ShPref.getInt("userDataVipAccess3M", 0)
		set(value) = ShPref.put("userDataVipAccess3M", value)

	var secondsIn12M: Int
		get() = ShPref.getInt("userDataSecondsIn12M", 0)
		set(value) = ShPref.put("userDataSecondsIn12M", value)

	var vipAccess12M: Int
		get() = ShPref.getInt("userDataVipAccess12M", 0)
		set(value) = ShPref.put("userDataVipAccess12M", value)

	var productSet12M: Int
		get() = ShPref.getInt("userDataProductSet12M", 0)
		set(value) = ShPref.put("userDataProductSet12M", value)

	var secondsIn1M: Int
		get() = ShPref.getInt("userDataSecondsIn1M", 0)
		set(value) = ShPref.put("userDataSecondsIn1M", value)

	var name: String
		get() = ShPref.getString("userDataName", "")
		set(value) = ShPref.put("userDataName", value)

	var secondsIn48H: Int
		get() = ShPref.getInt("userDataSecondsIn48H", 0)
		set(value) = ShPref.put("userDataSecondsIn48H", value)

	var vipAccess6M: Int
		get() = ShPref.getInt("userDataVipAccess6M", 0)
		set(value) = ShPref.put("userDataVipAccess6M", value)

	var product1M: Int
		get() = ShPref.getInt("userDataProduct1M", 0)
		set(value) = ShPref.put("userDataProduct1M", value)

	var productSet1M: Int
		get() = ShPref.getInt("userDataProductSet1M", 0)
		set(value) = ShPref.put("userDataProductSet1M", value)

	var secondsIn6M: Int
		get() = ShPref.getInt("userDataSecondsIn6M", 0)
		set(value) = ShPref.put("userDataSecondsIn6M", value)

	var secondsIn3M: Int
		get() = ShPref.getInt("userDataSecondsIn3M", 0)
		set(value) = ShPref.put("userDataSecondsIn3M", value)

	var product48H: Int
		get() = ShPref.getInt("userDataProduct48H", 0)
		set(value) = ShPref.put("userDataProduct48H", value)


}