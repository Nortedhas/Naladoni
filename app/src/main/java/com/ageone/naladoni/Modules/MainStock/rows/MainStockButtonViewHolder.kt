package com.ageone.naladoni.Modules.MainStock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.Button.BaseButton
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import timber.log.Timber
import yummypets.com.stevia.*

class MainStockButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {
    val button by lazy {
        val button = BaseButton()
        button.textSize = 17F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.rgb(242, 119, 39)
        button.cornerRadius = 22
        button.gradient = Color.rgb(242, 132, 45)
        button.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        button.initialize()
        button
    }

    init {
        renderUI()
    }
}

fun MainStockButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        button
    )

    button
        .constrainTopToTopOf(constraintLayout,6)
        .constrainBottomToBottomOf(constraintLayout)
        .fillHorizontally(20)
}

fun MainStockButtonViewHolder.initialize(text: String) {
    button.text = text
}