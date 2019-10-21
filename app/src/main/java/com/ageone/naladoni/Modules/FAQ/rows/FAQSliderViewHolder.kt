package com.ageone.naladoni.Modules.FAQ.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class FAQSliderViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.textColor = Color.parseColor("#929292")
        textView.textSize = 14F
        textView
    }
    val imageView by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.ic_go)
        imageView
    }
    init {

        renderUI()
    }

}

fun FAQSliderViewHolder.renderUI() {
    constraintLayout.subviews(
        textView,
        imageView

    )

    imageView
        .constrainTopToTopOf(constraintLayout)
        .width((utils.variable.displayWidth - 44))
        .height((utils.variable.displayWidth - 44))
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    textView
        .constrainTopToBottomOf(imageView, 20)
        .constrainBottomToBottomOf(constraintLayout,20)
        .width((utils.variable.displayWidth - 76))
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

}


fun FAQSliderViewHolder.initialize(textTitle:String, imageViewTitle: Int) {

    textView.text = textTitle
    imageView.setBackgroundResource(imageViewTitle)

}