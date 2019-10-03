package com.example.ageone.External.Extensions.Activity

import android.location.Location
import android.widget.Toast
import com.example.ageone.Application.AppActivity
import com.google.android.gms.maps.model.LatLng

val positionBase = LatLng(56.838607, 60.605514)
var currentLocation: Location? = null

fun AppActivity.fetchLastLocation(){
    val task = fusedLocationProviderClient?.lastLocation

    task?.addOnSuccessListener{ location ->
        if (location != null) {
            currentLocation = location
        } else {
            Toast.makeText(this, "No Location recorded", Toast.LENGTH_SHORT).show()
        }
    }
}