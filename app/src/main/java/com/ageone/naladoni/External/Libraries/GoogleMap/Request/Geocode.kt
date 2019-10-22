package com.ageone.naladoni.External.Libraries.GoogleMap.Request

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Libraries.GoogleMap.Address
import com.ageone.naladoni.External.Libraries.GoogleMap.ComponentType
import com.ageone.naladoni.External.Libraries.GoogleMap.getAddressComponentByType
import com.github.kittinunf.fuel.Fuel
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber

fun geodecodeByCoordinates(latitude: Double, longitude: Double, completion: (Address) -> Unit) {

    val url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=$latitude,$longitude&key=${utils.googleApiKey}"

    Fuel.post(url)
        .responseString { request, response, result ->
            result.fold({ result ->
                val jsonObject = JSONObject(result)
                Timber.i("\nAPI Google Request:\n $request\n \n $response")

                val error = jsonObject.optString("error", "")
                if (error != "") {
                    Timber.e("$error")
                } else {
                    completion.invoke(parseGoogleMapResultsFromJson(jsonObject))
                }

            },{ error ->
                Timber.e("${error.response.responseMessage}")
            })
        }
}

fun parseGoogleMapResultsFromJson(json: JSONObject): Address {
    var address = Address()

    address.lat = json.optJSONArray("results")
        .optJSONObject(0).optJSONObject("geometry")
        .optJSONObject("location").optDouble("lat", .0)

    address.lng = json.optJSONArray("results")
        .optJSONObject(0).optJSONObject("geometry")
        .optJSONObject("location").optDouble("lng", .0)

    (json["results"] as JSONArray).optJSONObject(0).optJSONArray("address_components")?.let{ adressComponents ->
        for (i in 0 until adressComponents.length()) {
            val addressObject = adressComponents[i] as JSONObject
            val type = addressObject.optJSONArray("types").optString(0)
            var value = addressObject.optString("short_name")
            if (value.isNullOrBlank()) {
                value = addressObject.optString("long_name")
            }

            when (getAddressComponentByType(type)) {
                ComponentType.home -> {
                    address.home = value
                }
                ComponentType.postalCode -> {
                    address.postalCode = value
                }
                ComponentType.street -> {
                    address.street = value
                }
                ComponentType.region -> {
                    address.region = value
                }
                ComponentType.city -> {
                    address.city = value
                }
                ComponentType.country -> {
                    address.country = value
                }
                ComponentType.none -> {
                    Timber.e("Cant parce $type from Google Map Address Component")
                }
            }

        }
    }

    return address
}
