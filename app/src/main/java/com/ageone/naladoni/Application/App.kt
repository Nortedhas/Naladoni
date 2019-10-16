package com.ageone.naladoni.Application

import android.app.Application
import android.content.Intent
import android.util.Log
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Router.Router
import com.ageone.naladoni.BuildConfig
import com.ageone.naladoni.External.Base.Activity.BaseActivity
import com.ageone.naladoni.External.Extensions.Application.FTActivityLifecycleCallbacks
import com.ageone.naladoni.External.HTTP.API.API
import com.ageone.naladoni.Internal.Utilities.Utils
import com.ageone.naladoni.Models.RxData
import com.ageone.naladoni.Network.Socket.WebSocket
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.android.gms.maps.MapView
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import net.alexandroid.shpref.ShPref
import timber.log.Timber

val router = Router()
val coordinator = FlowCoordinator()

val utils = Utils()
val api = API()
//val database = DataBase
val rxData = RxData()
var webSocket = WebSocket()
var intent = Intent()

val currentActivity: BaseActivity?
    get() = App.instance?.mFTActivityLifecycleCallbacks?.currentActivity as BaseActivity

val mapView by lazy {
    val mapView = MapView(currentActivity)
    mapView
}

val mapViewHowGo by lazy {
    val mapView = MapView(currentActivity)
    mapView
}
// var mapViewHowGo: MapView? = null



class App: Application()  {

    init {
        instance = this
    }

    val mFTActivityLifecycleCallbacks = FTActivityLifecycleCallbacks()

    override fun onCreate() {
        super.onCreate()

        // MARK: SharePreferences

        ShPref.init(this, ShPref.APPLY)

        // MARK: Timber
        if (BuildConfig.DEBUG) {
            val deviceManufacturer = android.os.Build.MANUFACTURER
            if (deviceManufacturer.toLowerCase().contains("huawei")) {
                Timber.plant(HuaweiTree())
            } else {
                Timber.plant(Timber.DebugTree())
            }
        }

        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
//                    Timber.i("Internet are allowed")
                    utils.isNetworkReachable = isConnectedToInternet
            }

        Realm.init(this)

        registerActivityLifecycleCallbacks(mFTActivityLifecycleCallbacks)
    }

    companion object {

        var instance: App? = null

    }
}

class HuaweiTree : Timber.DebugTree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        var priority = priority
        if (priority == Log.VERBOSE || priority == Log.DEBUG)
            priority = Log.INFO
        super.log(priority, tag, message, t)
    }
}

class MyFirebaseMessagingService: FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Timber.i("${remoteMessage?.data}")

    }

    override fun onNewToken(token: String?) {
        Timber.i("$token")
    }
}