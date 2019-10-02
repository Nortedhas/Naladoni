package com.example.ageone.Application

import android.Manifest
import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.Models.User.user
import com.example.ageone.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.swarmnyc.promisekt.Promise
import com.vk.api.sdk.utils.VKUtils
import timber.log.Timber


var currentLocation: Location? = null
class AppActivity: BaseActivity() {
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val LOCATION_REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        mapView?.onCreate(savedInstanceState)

        //fullscreen flags
        window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setDisplaySize()

//        FuelManager.instance.basePath = DataBase.url

        verifyStoragePermissions(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)

        }
        fetchLastLocation()


        val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)
        Timber.i("PACKAGE: ${fingerprints?.toList()}")

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

    private fun setDisplaySize() {
        val displayMetrics = resources.displayMetrics
        utils.variable.displayWidth = (displayMetrics.widthPixels / displayMetrics.density).toInt()
        utils.variable.displayHeight = (displayMetrics.heightPixels / displayMetrics.density).toInt()

        Timber.i("width = ${utils.variable.displayWidth}")

        // Calculate ActionBar height
        val tv = TypedValue()
        if (theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            utils.variable.actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics) / 3
        }
    }

    override fun onBackPressed() {
        Timber.i("viewBack")
        router.onBackPressed()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResult: IntArray
    ) {
        when (requestCode) {
            LOCATION_REQUEST_CODE -> if (grantResult.isNotEmpty() && grantResult[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLastLocation()
            } else {
                Toast.makeText(this, "Location permission missing", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun fetchLastLocation(){
        val task = fusedLocationProviderClient?.lastLocation

        task?.addOnSuccessListener{ location ->
            if (location != null) {
                currentLocation = location
                Toast.makeText(
                    this,
                    "${currentLocation?.latitude} ${currentLocation?.longitude}",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "No Location recorded", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

fun Activity.hideKeyboard() {
    // Check if no view has
    val view = currentFocus
    view?.let { v ->
        //hide keyboard
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(v.windowToken, 0)
    }
}

fun Activity.copyToClipboard(text: CharSequence){
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", text)
    clipboard.primaryClip = clip
}

fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}

// Storage Permissions
private val REQUEST_EXTERNAL_STORAGE = 1
private val PERMISSIONS_STORAGE = arrayOf<String>(
    Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE
)

/**
 * Checks if the app has permission to write to device storage
 *
 * If the app does not has permission then the user will be prompted to grant permissions
 *
 * @param activity
 */
fun verifyStoragePermissions(activity: Activity) {
    // Check if we have write permission
    val permission =
        ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    if (permission != PackageManager.PERMISSION_GRANTED) {
        // We don't have permission so prompt the user
        ActivityCompat.requestPermissions(
            activity,
            PERMISSIONS_STORAGE,
            REQUEST_EXTERNAL_STORAGE
        )
    }
}