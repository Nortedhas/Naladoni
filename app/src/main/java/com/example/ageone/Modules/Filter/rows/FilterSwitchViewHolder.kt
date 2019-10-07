package com.example.ageone.Modules.Filter.rows

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*


class FilterSwitchViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {
    val linetop by lazy {
        val view = BaseView()
        view.backgroundColor = parseColor("#B74200")
        view.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        view.initialize()
        view
    }
    val linebuttom by lazy {
        val view = BaseView()
        view.backgroundColor = parseColor("#B74200")
        view.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        view.initialize()
        view
    }

    var states =
        arrayOf(intArrayOf(-android.R.attr.state_checked), intArrayOf(android.R.attr.state_checked))

    var thumbColors = intArrayOf(Color.WHITE, Color.parseColor("#781EB0"))

    var trackColors = intArrayOf(Color.parseColor("#C74600"), Color.WHITE)

    val switch by lazy {
        val switch = SwitchCompat(currentActivity)
        switch.textSize = 17F
        switch.textColor = Color.WHITE
        switch.typeface = Typeface.DEFAULT_BOLD
        switch.elevation = 3F.dp
        switch.text = ""

        DrawableCompat.setTintList(
            DrawableCompat.wrap(switch.thumbDrawable),
            ColorStateList(states, thumbColors)
        )
        DrawableCompat.setTintList(
            DrawableCompat.wrap(switch.trackDrawable),
            ColorStateList(states, trackColors)
        )

        switch
    }

init {

    renderUI()
}

}

fun FilterSwitchViewHolder.renderUI() {
    constraintLayout.subviews(
        linetop,
        linebuttom,
        switch
    )
    linetop
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout,16)
        .height(1)
    linebuttom
        .fillHorizontally()
        .constrainTopToBottomOf(switch,10)
        .height(1)
    switch
        .constrainTopToBottomOf(linetop,10)
        .fillHorizontally(8)


}

fun FilterSwitchViewHolder.initialize(text: String) {
    switch.text = text

}
