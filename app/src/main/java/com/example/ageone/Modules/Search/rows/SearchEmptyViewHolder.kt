package com.example.ageone.Modules.Search.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.R
import yummypets.com.stevia.*

class SearchEmptyViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val image by lazy {
        val image = BaseImageView()
        image.setBackgroundResource(R.drawable.pic_logo_empty)
        image
    }
    val text by lazy {
        val text = BaseTextView()
        text.textColor = Color.parseColor("#F06F28")
        text.textSize = 17F
        text.typeface = Typeface.DEFAULT_BOLD
        text.gravity = Gravity.CENTER
        text
    }

    init {

        renderUI()
    }

}

fun SearchEmptyViewHolder.renderUI() {
    constraintLayout.subviews(
        image,
        text
    )
    image
        .constrainTopToTopOf(constraintLayout, 150)
        .constrainRightToRightOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout)
        .width(86)
        .height(96)

    text
        .constrainTopToBottomOf(image, 20)
        .fillHorizontally(72)

}

fun SearchEmptyViewHolder.initialize(describe: String) {
    text.text = describe

}
