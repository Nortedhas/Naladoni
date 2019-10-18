package com.ageone.naladoni.Models.User

class UserInformation {
    var city: City? = null
}

data class City (
    var name: String = "",
    var hashId: String = ""
)

