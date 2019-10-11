package com.example.ageone.Models

import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import timber.log.Timber
import kotlin.properties.Delegates


class RxData {
    var selectedFilter: Int by Delegates.observable(-1) { property, oldValue, newValue ->
        Timber.i("Change filter - New value: $newValue")
        RxBus.publish(RxEvent.EventChangeFilterIndex())
    }
}

