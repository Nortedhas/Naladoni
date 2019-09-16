package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class TitleViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textViewTitle by lazy {
        val textView = BaseTextView()

        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)

        textView
    }


    init {
        renderUI()
    }

}

fun TitleViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewTitle
    )

    textViewTitle
        .constrainTopToTopOf(constraintLayout, 24)
        .fillHorizontally(8)
}

fun TitleViewHolder.initialize(text: String, textColor: Int, textSize: Float = 21F) {
    textViewTitle.text = text
    textViewTitle.textColor = textColor
    textViewTitle.textSize = textSize
}

