package com.example.ageone.External.Extensions.Activity

import android.location.Location
import android.os.Looper
import android.widget.Toast
import com.example.ageone.Application.AppActivity
import com.example.ageone.Application.currentActivity
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.RuntimeExecutionException
import timber.log.Timber

val locationBase = LatLng(56.838607, 60.605514)
var currentLocation: Location? = null

val reqSetting = LocationRequest.create().apply {
    fastestInterval = 1000
    interval = 1000
    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    smallestDisplacement = 1.0f
}

fun AppActivity.fetchLastLocation(){
    fusedLocationProviderClient?.
        lastLocation?.addOnSuccessListener{ location ->
        if (location != null) {
            currentLocation = location
        } else {
            Toast.makeText(this, "No Location recorded", Toast.LENGTH_SHORT).show()
        }
    }

    setLocationUpdates()
}

private fun AppActivity.setLocationUpdates() {

    val REQUEST_CHECK_STATE = 12300 // any suitable ID
    val builder = LocationSettingsRequest.Builder()
        .addLocationRequest(reqSetting)

    val client = LocationServices.getSettingsClient(this)

    client.checkLocationSettings(builder.build()).addOnCompleteListener { task ->
        try {
            val state: LocationSettingsStates? = task.result?.locationSettingsStates
            state?.let { state ->
                Timber.i(
                    "LocationSettings: \n" +
                            " GPS present: ${state.isGpsPresent} \n" +
                            " GPS usable: ${state.isGpsUsable} \n" +
                            " Location present: " +
                            "${state.isLocationPresent} \n" +
                            " Location usable: " +
                            "${state.isLocationUsable} \n" +
                            " Network Location present: " +
                            "${state.isNetworkLocationPresent} \n" +
                            " Network Location usable: " +
                            "${state.isNetworkLocationUsable} \n"
                )

            }
        } catch (e: RuntimeExecutionException) {
            if (e.cause is ResolvableApiException)
                (e.cause as ResolvableApiException).startResolutionForResult(
                    currentActivity,
                    REQUEST_CHECK_STATE
                )
        }
    }


    val locationUpdates = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            Timber.i("Newest $locationResult")
            Timber.i("Newest Location: ${locationResult.locations.last()}")
            Timber.i("Newest Location: ${locationResult.lastLocation}")
            currentLocation = locationResult.lastLocation
            // do something with the new location...
        }
    }

    fusedLocationProviderClient?.requestLocationUpdates(
        reqSetting,
        locationUpdates,
        Looper.getMainLooper()
    )
}

var startLocation: LatLng = locationBase
    get() {
    return if (isLocationGranted) {
            LatLng(
                currentLocation?.latitude ?: locationBase.latitude,
                currentLocation?.longitude ?: locationBase.longitude
            )
        } else {
            locationBase
        }

}