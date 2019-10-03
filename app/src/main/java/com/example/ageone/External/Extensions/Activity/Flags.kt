package com.example.ageone.External.Extensions.Activity

import android.app.Activity
import android.view.View

fun Activity.setFullScreen() {
    //fullscreen flags
    window.decorView.systemUiVisibility =
        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
}