package com.example.ageone.Application

import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.External.Extensions.Activity.*
import com.example.ageone.Models.User.user
import com.example.ageone.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.swarmnyc.promisekt.Promise
import timber.log.Timber

class AppActivity: BaseActivity() {
    var fusedLocationProviderClient: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapViewHowGo.onCreate(savedInstanceState)

        setFullScreen()
        setDisplaySize()

        addStoragePermissions()
        addLocationPermissions()
        verifyPermissions {
            isLocationGranted = true
            fetchLastLocation()
        }

//        FuelManager.instance.basePath = DataBase.url
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        user.isAuthorized = false //TODO: change after add registration
        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {
            /*api.handshake {
                Timber.i("Handshake out")
                coordinator.start()

                FirebaseInstanceId.getInstance().instanceId
                    .addOnCompleteListener(OnCompleteListener { task ->
                        if (!task.isSuccessful) {
                            Timber.i("fail")
                            return@OnCompleteListener
                        }

                        // Get new Instance ID UserHandshake
                        val token = task.result?.token ?: ""
//                        DataBase.User.update(user.hashId, mapOf("fcmToken" to token))
                    })
            }*/
            val googleApiAvailability = GoogleApiAvailability.getInstance()
            when (val result = googleApiAvailability.isGooglePlayServicesAvailable(this)) {
                ConnectionResult.SUCCESS -> {
                    coordinator.start()
                }
                else -> {
                    googleApiAvailability.showErrorNotification(this, result)
                }
            }

        }

        setContentView(router.layout)
    }

    override fun onBackPressed() {
        Timber.i("viewBack")
        router.onBackPressed()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
        mapViewHowGo.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        mapViewHowGo.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        mapViewHowGo.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        mapViewHowGo.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResult: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResult.isNotEmpty() && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                    isLocationGranted = true
                    fetchLastLocation()
                } else {
                    Toast.makeText(this, "Location permission missing", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


}