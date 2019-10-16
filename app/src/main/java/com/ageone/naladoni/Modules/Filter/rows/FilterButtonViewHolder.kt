package com.ageone.naladoni.Modules.Filter.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.Button.BaseButton
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class FilterButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val button by lazy {
        val button = BaseButton()
        button.textSize = 21F
        button.textColor = Color.BLACK
        button.typeface = Typeface.DEFAULT_BOLD
        button.backgroundColor = Color.WHITE
        button.cornerRadius = 22
        button.elevation = 3F.dp
        button.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        button.initialize()
        button
    }

    init {
        renderUI()
    }
}

fun FilterButtonViewHolder.renderUI() {

    constraintLayout.subviews(
        button
    )

    button
        .constrainTopToTopOf(constraintLayout, 27)
        .constrainBottomToBottomOf(constraintLayout,21)
        .fillHorizontally(17)
}

fun FilterButtonViewHolder.initialize(text: String) {
    button.text = text
}