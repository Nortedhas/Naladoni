package com.ageone.naladoni.SCAG

import com.ageone.naladoni.Models.User.user
import org.json.JSONObject

fun Parser.userData(json: JSONObject) {
	json.optJSONObject("User")?.let { userJson ->//todo: add ?
		user.hashId = userJson.optString("hashId", "")
		user.data.name = userJson.optString("name", "")
		user.data.phone = userJson.optString("phone", "")
		user.data.giftsTakenNum = userJson.optInt("giftsTakenNum", 0)
		user.data.role = userJson.optString("role", "")
	}
}