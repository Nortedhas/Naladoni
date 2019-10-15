package com.ageone.naladoni.Models

import com.ageone.naladoni.External.RxBus.RxBus
import com.ageone.naladoni.External.RxBus.RxEvent
import timber.log.Timber
import kotlin.properties.Delegates


class RxData {
    var selectedFilter: Int by Delegates.observable(-1) { property, oldValue, newValue ->
        Timber.i("Change filter - New value: $newValue")
        RxBus.publish(RxEvent.EventChangeFilterIndex())
    }
}

