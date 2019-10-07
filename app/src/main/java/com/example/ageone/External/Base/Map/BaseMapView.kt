package com.example.ageone.External.Base.Map

import android.widget.ImageView
import android.widget.RelativeLayout
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Extensions.Activity.isLocationGranted
import com.example.ageone.External.Extensions.Activity.locationBase
import com.example.ageone.External.Extensions.Activity.startLocation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import yummypets.com.stevia.dp

fun ImageView.setLocationButtonInMap(left: Int, top: Int, right: Int, bottom: Int) {
    val layoutParams = layoutParams as RelativeLayout.LayoutParams

    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0)
    layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)
    layoutParams.setMargins(left.dp, top.dp, right.dp, bottom.dp)
}

fun GoogleMap.setMyLocation(buttonLocation: BaseImageView) {
//    isMyLocationEnabled = true

    buttonLocation.setOnClickListener {
        /*val loc = if (isLocationGranted) {
            LatLng(
                location.latitude,
                location.longitude
            )
        } else {
            locationBase
        }*/
        moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))
    }
}