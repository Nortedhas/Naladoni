package com.ageone.naladoni.SCAG

import com.ageone.naladoni.Application.utils
import org.json.JSONObject

fun Parser.config(json: JSONObject) {
	json.optJSONArray("Config")?.optJSONObject(0)?.let { userJson ->//todo: change ?
		utils.config.name = userJson.optString("name", "")
	}
}