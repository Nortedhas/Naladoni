package com.ageone.naladoni.External.Base.Map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import androidx.core.content.ContextCompat
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.External.Libraries.GoogleMap.Route
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

