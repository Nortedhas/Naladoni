package com.ageone.naladoni.External.Base.ProgressBar

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.ProgressBar
import com.ageone.naladoni.Application.currentActivity

class BaseProgressBar: ProgressBar(currentActivity) {
    var color: Int? = null

    fun initialize() {
        color?.let { color ->
            indeterminateTintList = ColorStateList.valueOf(Color.WHITE)
        }
    }
}