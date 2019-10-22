package com.ageone.naladoni.External.Base.Map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.Models.User.user
import com.github.kittinunf.fuel.Fuel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.google.maps.android.PolyUtil
import org.json.JSONArray
import org.json.JSONObject
import timber.log.Timber
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin


fun GoogleMap.setMyLocation(buttonLocation: BaseImageView) {
    if (user.permission.geo) {
        isMyLocationEnabled = true
        uiSettings.isMyLocationButtonEnabled = false
    }

    buttonLocation.setOnClickListener {
        moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))
    }
}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    return ContextCompat.getDrawable(context, vectorResId)?.run {
        setBounds(0, 0, intrinsicWidth, intrinsicHeight)
        val bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
        draw(Canvas(bitmap))
        BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}

fun GoogleMap.drawPolyline(
    route: Route,
    color: Int = Color.RED,
    width: Float = 3F
) {
    for (i in 0 until route.path.size) {
        addPolyline(
            PolylineOptions()
                .addAll(route.path[i])
                .color(color)
                .width(width)
        )
    }
    moveCamera(CameraUpdateFactory.newLatLngBounds(LatLngBounds(route.boundsSouthwest, route.boundsNortheast), 20))
}

fun loadRoutePath(from: LatLng, to: LatLng, wayPoints: Array<LatLng>, completion: (Route) -> Unit) {
    val origin = "${from.latitude},${from.longitude}"
    val destination = "${to.latitude},${to.longitude}"
    var wayPointsRoute = ""
    if (wayPoints.isNotEmpty()) {
        wayPointsRoute = "&wayPoints="
        wayPoints.forEach {waypoint ->
            wayPointsRoute += "via:${waypoint.latitude},${waypoint.longitude}%7C"
        }
        wayPointsRoute = wayPointsRoute.dropLast(3)
    }
    val url = "https://maps.googleapis.com/maps/api/directions/json?origin=$origin" +
            "&destination=$destination$wayPointsRoute&mode=driving&key=${utils.googleApiKey}"

    Fuel.post(url)
        .responseString { request, response, result ->
            result.fold({ result ->
                val jsonObject = JSONObject(result)
                Timber.i("\nAPI Google Request Path:\n $request\n \n $response")

                var routeForParse = jsonObject.optJSONArray("routes")[0]/* as JSONObject*/
                var route = Route()

                val error = jsonObject.optString("error_message", "")
                if (error != "") {
                    Timber.e("$error")
                } else if (routeForParse is JSONObject){

                    // MARK: Bounds

                    route.boundsNortheast = LatLng(
                        routeForParse.optJSONObject("bounds")?.optJSONObject("northeast")?.optDouble("lat") ?: .0,
                        routeForParse.optJSONObject("bounds")?.optJSONObject("northeast")?.optDouble("lng") ?: .0
                    )

                    route.boundsSouthwest = LatLng(
                        routeForParse.optJSONObject("bounds")?.optJSONObject("southwest")?.optDouble("lat") ?: .0,
                        routeForParse.optJSONObject("bounds")?.optJSONObject("southwest")?.optDouble("lng") ?: .0
                    )

                    // MARK: Path

                    routeForParse.optJSONArray("legs")?.optJSONObject(0)
                        ?.optJSONArray("steps")?.let { steps ->
                            for (i in 0 until steps.length()) {
                                steps.optJSONObject(i)?.optJSONObject("polyline")
                                    ?.getString("points")?.let { points ->
                                        route.path.add(PolyUtil.decode(points))
                                    }
                            }
                    }

                    routeForParse.optJSONArray("legs")?.let{ legs ->
                        for (i in 0 until legs.length()) {
                            legs.optJSONObject(i)?.let { leg ->
                                route.distance += leg.optJSONObject("distance")?.optInt("value") ?: 0
                                route.duration += leg.optJSONObject("duration")?.optInt("value") ?: 0
                            }

                        }

                    }

                    completion.invoke(route)
                }

            },{ error ->
                Timber.e("${error.response.responseMessage}")
            })

    }
}


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
        .optJSONObject("location").optDouble("lat", .0)//]json["results"][0]["geometry"]["location"]["lat"].doubleValue

    address.lng = json.optJSONArray("results")
        .optJSONObject(0).optJSONObject("geometry")
        .optJSONObject("location").optDouble("lng", .0)//json["results"][0]["geometry"]["location"]["lng"].doubleValue

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

data class Route(
    var path: MutableList<List<LatLng>> = ArrayList(),
    var boundsNortheast: LatLng = startLocation,
    var boundsSouthwest: LatLng = startLocation,
    var distance: Int = 0,
    var duration: Int = 0
)

fun distance(lat1: Double, lon1: Double, lat2: Double, lon2: Double, unit: String): Double {
    if (lat1 == lat2 && lon1 == lon2) {
        return 0.0
    } else {
        val theta = lon1 - lon2
        var dist = sin(Math.toRadians(lat1)) * sin(Math.toRadians(lat2)) + cos(
            Math.toRadians(lat1)
        ) * cos(Math.toRadians(lat2)) * cos(Math.toRadians(theta))
        dist = acos(dist)
        dist = Math.toDegrees(dist)
        dist *= 60.0 * 1.1515
        if (unit === "K") {
            dist *= 1.609344
        } else if (unit === "N") {
            dist *= 0.8684
        }
        return dist
    }
}

