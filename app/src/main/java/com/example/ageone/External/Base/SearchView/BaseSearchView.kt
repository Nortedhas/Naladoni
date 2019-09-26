package com.example.ageone.External.Base.SearchView

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import com.example.ageone.Application.currentActivity


class BaseSearchView: SearchView(currentActivity) {
    var isAlwaysExpand: Boolean? = null

    var color: Int? = null

    fun initialize() {
        color?.let { color ->
            val editText =
                findViewById<EditText?>(androidx.appcompat.R.id.search_src_text)
            val searchCloseIcon =
                findViewById<ImageView?>(androidx.appcompat.R.id.search_close_btn)
            val searchInnerIcon =
                findViewById<ImageView?>(androidx.appcompat.R.id.search_mag_icon)

            editText?.setTextColor(color)
            editText?.setHintTextColor(color)

            searchCloseIcon?.setColorFilter(color)
            searchInnerIcon?.setColorFilter(color)
        }

        isAlwaysExpand?.let { isAlwaysExpand ->
            setIconifiedByDefault(!isAlwaysExpand)
        }

    }
}