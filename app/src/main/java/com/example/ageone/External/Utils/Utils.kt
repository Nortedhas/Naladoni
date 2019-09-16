package com.example.ageone.External.Utils

import android.graphics.Color

object Tools {
    fun hex(hex: String): Int {
        return Color.parseColor("#$hex")
    }

    fun getClassName(name: String): String {
        return name.split("{")[0]
    }
}

object Variable {
    var statusBarHeight = 0
    var displayWidth = 0
    var displayHeight = 0
    var actionBarHeight = 0
    var token = ""
    var vkSdkTokenUser = ""
}