package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import timber.log.Timber
import yummypets.com.stevia.*

class ButtonViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val button by lazy {
        val button = BaseButton()
        button.textSize = 17F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.rgb(0xA8, 0xAC, 0xEB)
        button.cornerRadius = 60
        button.gradient = Color.rgb(0x8B, 0x91, 0xC7)
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
        .fillHorizontally(40)
}

fun ButtonViewHolder.initialize(text: String) {
    button.text = text
}