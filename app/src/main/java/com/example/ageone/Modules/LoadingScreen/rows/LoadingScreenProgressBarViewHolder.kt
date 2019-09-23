package com.example.ageone.Modules.LoadingScreen.rows

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.widget.ProgressBar
import com.example.ageone.Application.currentActivity

class LoadingScreenProgressBarViewHolder: ProgressBar(currentActivity) {
    var cornerRadius: Int? = null
    var backgroundColor: Int? = null
    var orientation: GradientDrawable.Orientation? = null
    fun initialize() {
    }
}