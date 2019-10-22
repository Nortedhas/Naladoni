package com.ageone.naladoni.External.Libraries.GoogleMap

data class Address(
    var home: String = "",
    var postalCode: String = "",
    var street: String = "",
    var region: String = "",
    var city: String = "",
    var country: String = "",
    var lat: Double = .0,
    var lng: Double = .0
)

enum class ComponentType {
    home, postalCode, street, region, city, country, none
}

fun getAddressComponentByType(type: String): ComponentType = when(type) {

    "street_number" -> {
        ComponentType.home
    }
    "postal_code" -> {
        ComponentType.postalCode
    }
    in "street_address", "route" -> {
        ComponentType.street
    }
    in "administrative_area_level_1",
    "administrative_area_level_2",
    "administrative_area_level_3",
    "administrative_area_level_4",
    "administrative_area_level_5" -> {
        ComponentType.region
    }
    in "locality",
    "sublocality",
    "sublocality_level_1",
    "sublocality_level_2",
    "sublocality_level_3",
    "sublocality_level_4" -> {
        ComponentType.city
    }
    "country" -> {
        ComponentType.country
    }
    else -> {
        ComponentType.none
    }
}

//TODO: replace in base

