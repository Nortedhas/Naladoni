package com.ageone.naladoni.External.Libraries.GoogleMap.Request

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.github.kittinunf.fuel.Fuel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import org.json.JSONObject
import timber.log.Timber

data class Route(
    var path: MutableList<List<LatLng>> = ArrayList(),
    var boundsNortheast: LatLng = startLocation,
    var boundsSouthwest: LatLng = startLocation,
    var distance: Int = 0,
    var duration: Int = 0
)

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
