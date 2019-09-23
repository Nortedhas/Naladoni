package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import timber.log.Timber
import yummypets.com.stevia.*

class ButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {
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

fun ButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        button
    )

    button
        .constrainTopToTopOf(constraintLayout, 32)
        .fillHorizontally(20)
}

fun ButtonViewHolder.initialize(text: String) {
    button.text = text
}