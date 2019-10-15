package com.ageone.naladoni.External.Base.Map

import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Extensions.Activity.isLocationGranted
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap


fun GoogleMap.setMyLocation(buttonLocation: BaseImageView) {
    if (isLocationGranted) {
        isMyLocationEnabled = true
        uiSettings.isMyLocationButtonEnabled = false
    }

    buttonLocation.setOnClickListener {
        moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))
    }
}