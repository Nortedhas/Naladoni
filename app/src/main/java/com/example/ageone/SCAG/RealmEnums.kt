package com.example.ageone.SCAG

class Enums {

	// MARK: Enum OrderType

	enum class OrderType {
		vipAccess12M, product48H, vipAccess6M, product12M, vipAccess3M, product1M, productSet12M, productSet1M
	}

	// MARK: Enum UserType

	enum class UserType {
		Clint, Admin
	}

	// MARK: Enum AnnounceType

	enum class AnnounceType {
		Video, Event
	}

	// MARK: Enum ProductType

	enum class ProductType {
		From7To25, From25To45, LessThen7
	}

	// MARK: Enum DocumentType

	enum class DocumentType {
		Regular, FAQ
	}

	// MARK: Enum CategoryType

	enum class CategoryType {
		Flowers, Food
	}

	// MARK: Enum PaymentType

	enum class PaymentType {
		Card, ApplePay, Cash, CardToCourier
	}

}