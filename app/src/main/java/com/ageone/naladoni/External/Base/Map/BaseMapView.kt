package com.ageone.naladoni.External.Base.Map

import android.graphics.Color
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.External.Libraries.GoogleMap.Request.Route
import com.ageone.naladoni.Models.User.user
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.PolylineOptions


fun GoogleMap.setMyLocation(buttonLocation: BaseImageView) {
    if (user.permission.geo) {
        isMyLocationEnabled = true
        uiSettings.isMyLocationButtonEnabled = false
    }

    buttonLocation.setOnClickListener {
        moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))
    }
}

//TODO: replace in base


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

