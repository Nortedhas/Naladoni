// MARK: Realm Class

package com.example.ageone.SCAG

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Announce (
	open var name: String = "",
	open var isExist: Boolean = false,
	open var link: String = "",
	open var image: Image? = null,
	open var txtInfo: String = "",
	open var updated: Int = 0,
	open var __type: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Audio (
	open var updated: Int = 0,
	open var created: Int = 0,
	open var file: String = "",
	open var sourceName: String = "",
	open var isExist: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Chackra (
	open var updated: Int = 0,
	open var created: Int = 0,
	open var name: String = "",
	open var isExist: Boolean = false,
	open var serialNum: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var name: String = "",
	open var isExist: Boolean = false,
	open var image: Image? = null,
	open var txttext: String = "",
	open var updated: Int = 0,
	open var __type: String = "",
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var updated: Int = 0,
	open var created: Int = 0,
	open var preview: String = "",
	open var original: String = "",
	open var isExist: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Order (
	open var isExist: Boolean = false,
	open var orderNum: String = "",
	open var price: Int = 0,
	open var product: Product? = null,
	open var status: String = "",
	open var updated: Int = 0,
	open var productSet: ProductSet? = null,
	open var __type: String = "",
	open var userHashId: String = "",
	open var avaliableTime: Int = 0,
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Product (
	open var audio: Audio? = null,
	open var created: Int = 0,
	open var txtInfo: String = "",
	open var isExist: Boolean = false,
	open var isFree: Boolean = false,
	open var image: Image? = null,
	open var updated: Int = 0,
	open var isPopular: Boolean = false,
	open var purpose: RealmList<Purpose> = RealmList(),
	open var name: String = "",
	open var isQuickStart: Boolean = false,
	open var isIntro: Boolean = false,
	open var __duration: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class ProductSet (
	open var intro: Product? = null,
	open var isExist: Boolean = false,
	open var image: Image? = null,
	open var isFree: Boolean = false,
	open var txtInfo: String = "",
	open var name: String = "",
	open var tracks: RealmList<Product> = RealmList(),
	open var updated: Int = 0,
	open var rune: RealmList<Rune> = RealmList(),
	open var created: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Purpose (
	open var updated: Int = 0,
	open var created: Int = 0,
	open var name: String = "",
	open var isExist: Boolean = false,
	open var serialNum: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Rune (
	open var updated: Int = 0,
	open var created: Int = 0,
	open var name: String = "",
	open var isExist: Boolean = false,
	open var serialNum: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var created: Int = 0,
	open var isExist: Boolean = false,
	open var name: String = "",
	open var deviceId: String = "",
	open var role: String = "",
	open var phone: String = "",
	open var fcmToken: String = "",
	open var updated: Int = 0,
	open var vipAccessTo: Int = 0,
	open var email: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()