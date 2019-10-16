package com.ageone.naladoni.Modules.FAQ.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class SliderViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.textColor = Color.parseColor("#333333")
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

fun SliderViewHolder.renderUI() {
    constraintLayout.subviews(
        textView,
        imageView

    )

    imageView
        .constrainTopToBottomOf(constraintLayout, 20)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainBottomToBottomOf(constraintLayout, 20)

    textView
        .fillHorizontally(24)
        .constrainTopToBottomOf(imageView, 50)
        .width(320)

}


fun SliderViewHolder.initialize(textTitle:String, image_view_title: Int) {

    textView.text = textTitle
    imageView.setBackgroundResource(image_view_title)

}