// MARK: DataBase

package com.example.ageone.SCAG

import com.example.ageone.Application.utils

enum class DataBase {

    Announce, Audio, Chackra, Document, Image, Order, Product, ProductSet, Purpose, Rune, User;

	companion object DataObjects {
		var url: String = "http://45.141.102.83"
		val headers
				get() = mutableMapOf("x-access-token" to utils.variable.token)
	}
}