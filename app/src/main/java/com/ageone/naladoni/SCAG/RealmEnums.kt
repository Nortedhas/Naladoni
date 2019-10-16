package com.ageone.naladoni.SCAG

class Enums {

	// MARK: Enum AnnounceType

	enum class AnnounceType {
		video, event
	}

	// MARK: Enum OrderType

	enum class OrderType {
		vipaccess12m, product48h, productset1m, vipaccess6m, vipaccess3m, product1m, product12m, productset12m
	}

	// MARK: Enum UserType

	enum class UserType {
		clint, admin
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		food, flowers
	}

	// MARK: Enum PaymentType

	enum class PaymentType {
		cardtocourier, cash, applepay, card
	}

	// MARK: Enum ProductType

	enum class ProductType {
		from7to25, lessthen7, from25to45
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		regular, faq
	}

}