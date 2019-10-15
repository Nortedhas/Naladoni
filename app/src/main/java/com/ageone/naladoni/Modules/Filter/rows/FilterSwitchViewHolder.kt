package com.ageone.naladoni.Modules.Filter.rows

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.Switch.BaseSwitch
import com.ageone.naladoni.External.Base.View.BaseView
import yummypets.com.stevia.*


class FilterSwitchViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

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

    val switch by lazy {
        val switch = BaseSwitch()
        switch.textSize = 17F
        switch.textColor = Color.WHITE
        switch.typeface = Typeface.DEFAULT_BOLD
        switch.elevation = 3F.dp
        switch.text = ""
        switch.thumbColors = intArrayOf(Color.WHITE, Color.parseColor("#781EB0"))
        switch.trackColors = intArrayOf(Color.parseColor("#C74600"), Color.WHITE)
        switch.initialize()
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

fun FilterSwitchViewHolder.initialize(text: String,visible:Boolean) {
    switch.text = text

    if(visible){
        linetop.constrainTopToTopOf(constraintLayout)
        linetop.visibility = View.INVISIBLE

    }
}
