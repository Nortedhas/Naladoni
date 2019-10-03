package com.example.ageone.External.Extensions.Activity

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.ageone.Application.AppActivity
import timber.log.Timber

fun Activity.hasPermissions(vararg permissions: String): Boolean = permissions.all {
    ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
}

// STORAGE
const val EXTERNAL_STORAGE_REQUEST_CODE = 1
private val PERMISSIONS_STORAGE = arrayOf(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

fun Activity.verifyStoragePermissions() {
    val permission =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    if (permission != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            this,
            PERMISSIONS_STORAGE,
            EXTERNAL_STORAGE_REQUEST_CODE
        )
    }
}

// LOCATION
const val LOCATION_PERMISSION_REQUEST_CODE = 2
private val PERMISSIONS_LOCATION = arrayOf(
    Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION
)
var isLocationGranted = false

fun AppActivity.verifyLocationPermissions() {
    val permission =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

    if (permission != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            this,
            PERMISSIONS_LOCATION,
            LOCATION_PERMISSION_REQUEST_CODE
        )
    } else {
        Timber.i("Location permission are OK")
        isLocationGranted = true
        fetchLastLocation()
    }
}

fun AppActivity.verifyLocationAndStoragePermissions() {
    val permission =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

    if (permission != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            this,
            PERMISSIONS_LOCATION + PERMISSIONS_STORAGE,
            LOCATION_PERMISSION_REQUEST_CODE
        )
    } else {
        Timber.i("Location permission are OK")
        isLocationGranted = true
        fetchLastLocation()
    }
}