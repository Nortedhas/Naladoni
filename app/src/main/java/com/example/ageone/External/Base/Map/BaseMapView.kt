package com.example.ageone.External.Base.Map

import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Extensions.Activity.startLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap


fun GoogleMap.setMyLocation(buttonLocation: BaseImageView) {
    buttonLocation.setOnClickListener {
        moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))
    }
}