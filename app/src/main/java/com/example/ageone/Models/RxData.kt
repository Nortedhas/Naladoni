package com.example.ageone.Models

import android.content.DialogInterface
import com.example.ageone.Application.api
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.SCAG.Enums
import com.example.ageone.SCAG.Product
import com.example.ageone.SCAG.UserData.vipAccessTo
import org.json.JSONObject
import timber.log.Timber
import kotlin.properties.Delegates

class RxData {
    fun isVip() : Boolean =
        vipAccessTo > (System.currentTimeMillis() / 1000)

    fun payVariants(isSet: Boolean) =
        if (isSet)
            arrayOf(//TODO get prices
                "На месяц (299 р.)",
                "На год (1 299 р.)")
        else
            arrayOf(//TODO get prices
                "На 48 часов (99 р.)",
                "На месяц (299 р.)",
                "На год (1 299 р.)")

    fun createPayVariantCallback(hashId: String, isSet: Boolean, completion: (JSONObject) -> Unit): (DialogInterface, Int) -> (Unit) {

        return { dialog, which ->
            if (isSet) {
                api.createOrder(hashId, "",
                    when (which) {
                    0 -> {
                        Timber.i("899 pay")
                        Enums.OrderType.productSet1M
                    }

                    else -> {
                        Timber.i("1899 pay")
                        Enums.OrderType.productSet12M
                    }
                },
                    completion)

            } else {
                api.createOrder("", hashId,
                when (which) {
                    0 -> {
                        Timber.i("99 pay")
                        Enums.OrderType.product48H
                    }

                    1 -> {
                        Timber.i("299 pay")
                        Enums.OrderType.product1M
                    }

                    else -> {
                        Timber.i("1299 pay")
                        Enums.OrderType.product12M
                    }
                },
                    completion)
            }

        }
    }

    // MARK: selected in Pleer meditation

    var currentMeditation: Product? = null

    // MARK: playing meditation in background

    var isMeditationEnd: Boolean by Delegates.observable(false) { property, oldValue, newValue ->
        if (newValue) {
            RxBus.publish(RxEvent.EventMediaPlayerEnd())
        }
    }

    var duration: Long by Delegates.observable(0L) { property, oldValue, newValue ->
        RxBus.publish(RxEvent.EventChangeDuration(newValue))
    }

    var currentTime: Long by Delegates.observable(0L) { property, oldValue, newValue ->
        RxBus.publish(RxEvent.EventChangeCurrentTime(newValue))
    }

    var volumeBackground: Float = 0F
    var currentBackground: Int = 0
}

