// MARK: Parser

package com.ageone.naladoni.SCAG

import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Extensions.Realm.add
import com.ageone.naladoni.External.RxBus.RxBus
import com.ageone.naladoni.External.RxBus.RxEvent
import org.json.JSONObject

class Parser {
fun parseAnyObject(json: JSONObject, type: DataBase) {
	json.optJSONArray(type.name)?.let{array ->
		for (i in 0 until array.length()) {
			val json = array[i] as JSONObject
			val obj = when (type) {
				DataBase.Category -> {
					json.parseCategory()
				}
				DataBase.City -> {
					json.parseCity()
				}
				DataBase.Company -> {
					json.parseCompany()
				}
				DataBase.Document -> {
					json.parseDocument()
				}
				DataBase.Image -> {
					json.parseImage()
				}
				DataBase.Location -> {
					json.parseLocation()
				}
				DataBase.Stock -> {
					json.parseStock()
				}
				DataBase.Subcategory -> {
					json.parseSubcategory()
				}
				DataBase.User -> {
					json.parseUser()
				}
			}
			add(obj)
		}

		when (type) {
			DataBase.Category -> {
			}
			DataBase.City -> {
			}
			DataBase.Company -> {
			}
			DataBase.Document -> {
			}
			DataBase.Image -> {
			}
			DataBase.Location -> {
			}
			DataBase.Stock -> {
				RxBus.publish(RxEvent.EventReloadStocks())
			}
			DataBase.Subcategory -> {
			}
			DataBase.User -> {
			}
		}
	}
}

}

// MARK: Parse JSON to Realm

fun JSONObject.parseCategory(): Category {
	val some = Category()
	optJSONObject("subcategory")?.let { subcategorys ->
 		for (i in 0 until subcategorys.length()) {
 			some.subcategory.add(
				subcategorys.optJSONObject("$i")?.let { subcategory ->
					subcategory.parseSubcategory()
				}
			)
		}
	}
	optJSONObject("selectedImage")?.let { selectedImage ->
		some.selectedImage = selectedImage.parseImage()
	}
	optJSONObject("unselectedImage")?.let { unselectedImage ->
		some.unselectedImage = unselectedImage.parseImage()
	}
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	some.serialNum = optInt("serialNum", 0)
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	return some
}

fun JSONObject.parseCity(): City {
	val some = City()
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	some.created = optInt("created", 0)
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseCompany(): Company {
	val some = Company()
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	some.password = optString("password", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.__type = optString("__type", "")
	some.txttext = optString("txttext", "")
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.original = optString("original", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.preview = optString("preview", "")
	return some
}

fun JSONObject.parseLocation(): Location {
	val some = Location()
	some.longitude = optDouble("longitude", 0.0)
	some.geoHash = optString("geoHash", "")
	some.isExist = optBoolean("isExist", false)
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.address = optString("address", "")
	some.hashId = optString("hashId", "")
	some.latitude = optDouble("latitude", 0.0)
	return some
}

fun JSONObject.parseStock(): Stock {
	val some = Stock()
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	optJSONObject("city")?.let { city ->
		some.city = city.parseCity()
	}
	optJSONObject("location")?.let { location ->
		some.location = location.parseLocation()
	}
	some.hashId = optString("hashId", "")
	optJSONObject("subcategory")?.let { subcategorys ->
 		for (i in 0 until subcategorys.length()) {
 			some.subcategory.add(
				subcategorys.optJSONObject("$i")?.let { subcategory ->
					subcategory.parseSubcategory()
				}
			)
		}
	}
	some.code = optString("code", "")
	some.created = optInt("created", 0)
	optJSONObject("category")?.let { category ->
		some.category = category.parseCategory()
	}
	some.avaliableTo = optInt("avaliableTo", 0)
	some.workTimeFrom = optString("workTimeFrom", "")
	some.shortAbout = optString("shortAbout", "")
	some.workTimeTo = optString("workTimeTo", "")
	some.name = optString("name", "")
	some.usesNum = optInt("usesNum", 0)
	some.longAbout = optString("longAbout", "")
	some.isExist = optBoolean("isExist", false)
	some.updated = optInt("updated", 0)
	return some
}

fun JSONObject.parseSubcategory(): Subcategory {
	val some = Subcategory()
	some.serialNum = optInt("serialNum", 0)
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	some.deviceId = optString("deviceId", "")
	some.role = optString("role", "")
	some.fcmToken = optString("fcmToken", "")
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	some.giftsTakenNum = optInt("giftsTakenNum", 0)
	some.phone = optString("phone", "")
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	return some
}