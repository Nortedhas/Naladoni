package com.example.ageone.Application

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.ageone.Application.Coordinator.Flow.FlowAuth
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.External.Base.Activity.BaseActivity
import com.example.ageone.External.HTTP.update
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.Models.User.user
import com.example.ageone.Models.VKUser
import com.example.ageone.SCAG.DataBase
import com.github.kittinunf.fuel.core.FuelManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.swarmnyc.promisekt.Promise
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.exceptions.VKApiExecutionException
import com.vk.api.sdk.requests.VKRequest
import org.json.JSONObject
import timber.log.Timber
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.vk.api.sdk.utils.VKUtils


class AppActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        //only vertical mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // for launchScreen
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)

        //fullscreen flags
        window.decorView.systemUiVisibility =
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setDisplaySize()

        FuelManager.instance.basePath = DataBase.url

        verifyStoragePermissions(this)
        val fingerprints = VKUtils.getCertificateFingerprint(this, this.packageName)
        Timber.i("PACKAGE: ${fingerprints?.toList()}")

        //TODO just do this
        coordinator.setLaunchScreen()
        Promise<Unit> { resolve, _ ->

            router.layout.setOnApplyWindowInsetsListener { _, insets ->
                utils.variable.statusBarHeight = insets.systemWindowInsetTop

                resolve(Unit)
                insets
            }

        }.then {
            api.handshake {
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
                        DataBase.User.update(user.hashId, mapOf("fcmToken" to token))
                    })
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
        Timber.i("back")
        router.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object: VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                // User passed authorization
                Timber.i("Token: ${token.accessToken}")
                utils.variable.vkSdkTokenUser = token.accessToken
                Timber.i("${VKUsersRequest()}")
                VK.execute(VKUsersRequest(), object: VKApiCallback<VKUser> {
                    override fun success(result: VKUser) {
                        Timber.i("Result $result")
                        user.data.email = token.email ?: ""
                        user.data.name = "${result.firstName} ${result.lastName}"
                        if (currentFlow is FlowAuth) {
//                            (currentFlow as FlowAuth).runModuleRegistration()
                        }
                    }
                    override fun fail(error: VKApiExecutionException) {
                    }
                })

            }

            override fun onLoginFailed(errorCode: Int) {
                // User didn't pass authorization
                alertManager.single("Ошибка авторизации", "Ошибка авторизации через vk", null) { _, _ ->
                }
            }
        }
        if (data == null || !VK.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data)
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

fun AppActivity.setStatusBarColor(color: Int) {
    window.statusBarColor = color
}

class VKUsersRequest: VKRequest<VKUser> {
    constructor(): super("account.getProfileInfo") {
        addParam("access_token", utils.variable.vkSdkTokenUser)
    }

    override fun parse(r: JSONObject): VKUser {
        var result = VKUser()
        try {
             result = VKUser.parse(r.getJSONObject("response"))
        }
        catch (e: Exception) {
            Timber.e("Error parsing VK user: $e")
        }
        return result
    }
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