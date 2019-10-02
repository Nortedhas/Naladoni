package com.example.ageone.Modules.Filter.rows

import android.graphics.Color
import android.graphics.Typeface
import android.widget.Switch
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class FilterSwitchViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val switch by lazy {
        val switch = Switch(currentActivity)
        switch.textSize = 17F
        switch.textColor = Color.WHITE
        switch.typeface = Typeface.DEFAULT_BOLD
        switch.text = ""
        switch
    }

init {

    renderUI()
}

}

fun FilterSwitchViewHolder.renderUI() {
    constraintLayout.subviews(
        switch
    )

    switch
        .constrainTopToTopOf(constraintLayout)
        .constrainBottomToBottomOf(constraintLayout)
        .fillHorizontally()


}

fun FilterSwitchViewHolder.initialize(text: String) {
    switch.text = text

}
