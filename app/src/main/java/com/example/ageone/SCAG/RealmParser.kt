// MARK: Parser

package com.example.ageone.SCAG

import com.example.ageone.External.Extensions.Realm.add
import org.json.JSONObject

class Parser {
fun parseAnyObject(json: JSONObject, type: DataBase) {
	json.optJSONArray(type.name)?.let{array ->
		for (i in 0 until array.length()) {
			val json = array[i] as JSONObject
			val obj = when (type) {
				DataBase.Announce -> {
					json.parseAnnounce()
				}
				DataBase.Audio -> {
					json.parseAudio()
				}
				DataBase.Chackra -> {
					json.parseChackra()
				}
				DataBase.Document -> {
					json.parseDocument()
				}
				DataBase.Image -> {
					json.parseImage()
				}
				DataBase.Order -> {
					json.parseOrder()
				}
				DataBase.Product -> {
					json.parseProduct()
				}
				DataBase.ProductSet -> {
					json.parseProductSet()
				}
				DataBase.Purpose -> {
					json.parsePurpose()
				}
				DataBase.Rune -> {
					json.parseRune()
				}
				DataBase.User -> {
					json.parseUser()
				}
				}
			add(obj)
		}
	}
}

}

// MARK: Parse JSON to Realm

fun JSONObject.parseAnnounce(): Announce {
	val some = Announce()
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.txtInfo = optString("txtInfo", "")
	some.link = optString("link", "")
	some.created = optInt("created", 0)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.isExist = optBoolean("isExist", false)
	some.__type = optString("__type", "")
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseAudio(): Audio {
	val some = Audio()
	some.created = optInt("created", 0)
	some.file = optString("file", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.sourceName = optString("sourceName", "")
	return some
}

fun JSONObject.parseChackra(): Chackra {
	val some = Chackra()
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.serialNum = optInt("serialNum", 0)
	return some
}

fun JSONObject.parseDocument(): Document {
	val some = Document()
	some.updated = optInt("updated", 0)
	some.hashId = optString("hashId", "")
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.__type = optString("__type", "")
	some.txttext = optString("txttext", "")
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseImage(): Image {
	val some = Image()
	some.preview = optString("preview", "")
	some.created = optInt("created", 0)
	some.isExist = optBoolean("isExist", false)
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.original = optString("original", "")
	return some
}

fun JSONObject.parseOrder(): Order {
	val some = Order()
	optJSONObject("productSet")?.let { productSet ->
		some.productSet = productSet.parseProductSet()
	}
	optJSONObject("product")?.let { product ->
		some.product = product.parseProduct()
	}
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.userHashId = optString("userHashId", "")
	some.price = optInt("price", 0)
	some.created = optInt("created", 0)
	some.status = optString("status", "")
	some.isExist = optBoolean("isExist", false)
	some.__type = optString("__type", "")
	some.orderNum = optString("orderNum", "")
	some.avaliableTime = optInt("avaliableTime", 0)
	return some
}

fun JSONObject.parseProduct(): Product {
	val some = Product()
	some.__duration = optString("__duration", "")
	some.isQuickStart = optBoolean("isQuickStart", false)
	some.txtInfo = optString("txtInfo", "")
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.isIntro = optBoolean("isIntro", false)
	optJSONObject("purpose")?.let { purposes ->
 		for (i in 0 until purposes.length()) {
 			some.purpose.add(
				purposes.optJSONObject("$i")?.let { purpose ->
					purpose.parsePurpose()
				}
			)
		}
	}
	some.hashId = optString("hashId", "")
	some.isExist = optBoolean("isExist", false)
	optJSONObject("audio")?.let { audio ->
		some.audio = audio.parseAudio()
	}
	some.created = optInt("created", 0)
	some.updated = optInt("updated", 0)
	some.isPopular = optBoolean("isPopular", false)
	some.isFree = optBoolean("isFree", false)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parseProductSet(): ProductSet {
	val some = ProductSet()
	some.hashId = optString("hashId", "")
	optJSONObject("rune")?.let { runes ->
 		for (i in 0 until runes.length()) {
 			some.rune.add(
				runes.optJSONObject("$i")?.let { rune ->
					rune.parseRune()
				}
			)
		}
	}
	some.updated = optInt("updated", 0)
	optJSONObject("tracks")?.let { trackss ->
 		for (i in 0 until trackss.length()) {
 			some.tracks.add(
				trackss.optJSONObject("$i")?.let { tracks ->
					tracks.parseProduct()
				}
			)
		}
	}
	some.txtInfo = optString("txtInfo", "")
	optJSONObject("intro")?.let { intro ->
		some.intro = intro.parseProduct()
	}
	some.created = optInt("created", 0)
	optJSONObject("image")?.let { image ->
		some.image = image.parseImage()
	}
	some.isExist = optBoolean("isExist", false)
	some.isFree = optBoolean("isFree", false)
	some.name = optString("name", "")
	return some
}

fun JSONObject.parsePurpose(): Purpose {
	val some = Purpose()
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.serialNum = optInt("serialNum", 0)
	return some
}

fun JSONObject.parseRune(): Rune {
	val some = Rune()
	some.created = optInt("created", 0)
	some.name = optString("name", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.isExist = optBoolean("isExist", false)
	some.serialNum = optInt("serialNum", 0)
	return some
}

fun JSONObject.parseUser(): User {
	val some = User()
	some.email = optString("email", "")
	some.hashId = optString("hashId", "")
	some.updated = optInt("updated", 0)
	some.fcmToken = optString("fcmToken", "")
	some.deviceId = optString("deviceId", "")
	some.role = optString("role", "")
	some.vipAccessTo = optInt("vipAccessTo", 0)
	some.created = optInt("created", 0)
	some.phone = optString("phone", "")
	some.isExist = optBoolean("isExist", false)
	some.name = optString("name", "")
	return some
}