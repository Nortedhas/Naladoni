// MARK: Realm Class

package com.ageone.naladoni.SCAG

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category (
	open var isExist: Boolean = false,
	open var created: Int = 0,
	open var subcategory: RealmList<Subcategory> = RealmList(),
	open var name: String = "",
	open var unselectedImage: Image? = null,
	open var serialNum: Int = 0,
	open var updated: Int = 0,
	open var selectedImage: Image? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class City (
	open var name: String = "",
	open var updated: Int = 0,
	open var created: Int = 0,
	open var location: Location? = null,
	open var isExist: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Company (
	open var password: String = "",
	open var isExist: Boolean = false,
	open var name: String = "",
	open var image: Image? = null,
	open var created: Int = 0,
	open var updated: Int = 0,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Document (
	open var isExist: Boolean = false,
	open var name: String = "",
	open var image: Image? = null,
	open var created: Int = 0,
	open var updated: Int = 0,
	open var __type: String = "",
	open var txttext: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Image (
	open var preview: String = "",
	open var updated: Int = 0,
	open var original: String = "",
	open var created: Int = 0,
	open var isExist: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Location (
	open var geoHash: String = "",
	open var longitude: Double = 0.0,
	open var isExist: Boolean = false,
	open var latitude: Double = 0.0,
	open var updated: Int = 0,
	open var created: Int = 0,
	open var address: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Stock (
	open var image: Image? = null,
	open var subcategory: RealmList<Subcategory> = RealmList(),
	open var longAbout: String = "",
	open var location: Location? = null,
	open var avaliableTo: Int = 0,
	open var created: Int = 0,
	open var code: String = "",
	open var updated: Int = 0,
	open var usesNum: Int = 0,
	open var workTimeTo: String = "",
	open var name: String = "",
	open var isExist: Boolean = false,
	open var workTimeFrom: String = "",
	open var shortAbout: String = "",
	open var city: City? = null,
	open var category: Category? = null,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class Subcategory (
	open var name: String = "",
	open var updated: Int = 0,
	open var created: Int = 0,
	open var serialNum: Int = 0,
	open var isExist: Boolean = false,
	@PrimaryKey open var hashId: String = ""
): RealmObject()

open class User (
	open var giftsTakenNum: Int = 0,
	open var phone: String = "",
	open var isExist: Boolean = false,
	open var deviceId: String = "",
	open var name: String = "",
	open var updated: Int = 0,
	open var created: Int = 0,
	open var fcmToken: String = "",
	open var role: String = "",
	@PrimaryKey open var hashId: String = ""
): RealmObject()