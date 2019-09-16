package com.example.ageone.SCAG

import com.example.ageone.Application.utils
import com.example.ageone.Models.User.user
import org.json.JSONObject

fun Parser.config(json: JSONObject) {
	json.optJSONObject("User").let { userJson ->
		utils.config.productSet1M = userJson.optInt("productSet1M", 0)
		utils.config.vipAccess6M = userJson.optInt("vipAccess6M", 0)
		utils.config.vipAccess3M = userJson.optInt("vipAccess3M", 0)
		utils.config.secondsIn3M = userJson.optInt("secondsIn3M", 0)
		utils.config.secondsIn1M = userJson.optInt("secondsIn1M", 0)
		utils.config.product12M = userJson.optInt("product12M", 0)
		utils.config.name = userJson.optString("name", "")
		utils.config.vipAccess12M = userJson.optInt("vipAccess12M", 0)
		utils.config.secondsIn12M = userJson.optInt("secondsIn12M", 0)
		utils.config.productSet12M = userJson.optInt("productSet12M", 0)
		utils.config.product48H = userJson.optInt("product48H", 0)
		utils.config.secondsIn6M = userJson.optInt("secondsIn6M", 0)
		utils.config.secondsIn48H = userJson.optInt("secondsIn48H", 0)
		utils.config.product1M = userJson.optInt("product1M", 0)
	}
}