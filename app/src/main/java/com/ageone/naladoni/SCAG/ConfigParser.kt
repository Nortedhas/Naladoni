package com.ageone.naladoni.SCAG

import com.ageone.naladoni.Application.utils
import org.json.JSONObject

fun Parser.config(json: JSONObject) {
	(json.optJSONArray("Config")[0] as JSONObject).let { userJson ->
		utils.config.name = userJson.optString("name", "")
	}
}