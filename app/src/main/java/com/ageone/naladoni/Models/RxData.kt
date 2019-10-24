package com.ageone.naladoni.Models

import com.ageone.naladoni.External.RxBus.RxBus
import com.ageone.naladoni.External.RxBus.RxEvent
import com.ageone.naladoni.SCAG.Stock
import timber.log.Timber
import kotlin.properties.Delegates


class RxData {
    var filterCategory: Int by Delegates.observable(-1) { property, oldValue, newValue ->
        Timber.i("Change filter - New value: $newValue")
        RxBus.publish(RxEvent.EventChangeCategoryIndex())
    }

    var filterSubcategory: Int by Delegates.observable(-1) { property, oldValue, newValue ->
        Timber.i("Change filter - New value: $newValue")
        RxBus.publish(RxEvent.EventChangeSubcategoryIndex())
    }

    var filterIsPopular = false
    var filterNearest = false

    var currentStock: Stock? = null

    var filteredStocks = listOf<Stock>()

    var isSetFilter = false
}

