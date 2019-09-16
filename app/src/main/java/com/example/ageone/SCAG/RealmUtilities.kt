// MARK: Realm Utiles

package com.example.ageone.SCAG

import io.realm.Realm
import io.realm.RealmResults
import timber.log.Timber

object RealmUtilities {
	val announce = AnnounceUtiles()
	val audio = AudioUtiles()
	val chackra = ChackraUtiles()
	val document = DocumentUtiles()
	val image = ImageUtiles()
	val order = OrderUtiles()
	val product = ProductUtiles()
	val productset = ProductSetUtiles()
	val purpose = PurposeUtiles()
	val rune = RuneUtiles()
	val user = UserUtiles()
}

class AnnounceUtiles {

	fun getObjectById(id: String): Announce? =
	try {
		Realm.getDefaultInstance()
			.where(Announce::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Announce> =
	try {
		Realm.getDefaultInstance()
			.where(Announce::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Announce::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class AudioUtiles {

	fun getObjectById(id: String): Audio? =
	try {
		Realm.getDefaultInstance()
			.where(Audio::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Audio> =
	try {
		Realm.getDefaultInstance()
			.where(Audio::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Audio::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ChackraUtiles {

	fun getObjectById(id: String): Chackra? =
	try {
		Realm.getDefaultInstance()
			.where(Chackra::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Chackra> =
	try {
		Realm.getDefaultInstance()
			.where(Chackra::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Chackra::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class DocumentUtiles {

	fun getObjectById(id: String): Document? =
	try {
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Document> =
	try {
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Document::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ImageUtiles {

	fun getObjectById(id: String): Image? =
	try {
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Image> =
	try {
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Image::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class OrderUtiles {

	fun getObjectById(id: String): Order? =
	try {
		Realm.getDefaultInstance()
			.where(Order::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Order> =
	try {
		Realm.getDefaultInstance()
			.where(Order::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Order::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ProductUtiles {

	fun getObjectById(id: String): Product? =
	try {
		Realm.getDefaultInstance()
			.where(Product::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Product> =
	try {
		Realm.getDefaultInstance()
			.where(Product::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Product::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class ProductSetUtiles {

	fun getObjectById(id: String): ProductSet? =
	try {
		Realm.getDefaultInstance()
			.where(ProductSet::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<ProductSet> =
	try {
		Realm.getDefaultInstance()
			.where(ProductSet::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(ProductSet::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class PurposeUtiles {

	fun getObjectById(id: String): Purpose? =
	try {
		Realm.getDefaultInstance()
			.where(Purpose::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Purpose> =
	try {
		Realm.getDefaultInstance()
			.where(Purpose::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Purpose::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class RuneUtiles {

	fun getObjectById(id: String): Rune? =
	try {
		Realm.getDefaultInstance()
			.where(Rune::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<Rune> =
	try {
		Realm.getDefaultInstance()
			.where(Rune::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(Rune::class.java)
		.alwaysFalse()
		.findAll()
	}

}

class UserUtiles {

	fun getObjectById(id: String): User? =
	try {
		Realm.getDefaultInstance()
			.where(User::class.java)
		.equalTo("primaryKey", id)
		.equalTo("isExist", true)
		.findFirst()
	} catch (e: Exception) {
		Timber.e("$e")
		null
	}

	fun getAllObjects(): RealmResults<User> =
	try {
		Realm.getDefaultInstance()
			.where(User::class.java)
		.equalTo("isExist", true)
		.findAll()
	} catch (e: Exception) {
		Timber.e("$e")
		Realm.getDefaultInstance()
			.where(User::class.java)
		.alwaysFalse()
		.findAll()
	}

}

