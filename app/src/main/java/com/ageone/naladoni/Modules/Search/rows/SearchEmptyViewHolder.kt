package com.ageone.naladoni.Modules.Search.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.R
import yummypets.com.stevia.*

class SearchEmptyViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val image by lazy {
        val image = BaseImageView()
        image.setBackgroundResource(R.drawable.pic_logo_empty)
        image
    }

    val describe by lazy {
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
        describe
    )

    image
        .constrainTopToTopOf(constraintLayout, 150)
        .constrainRightToRightOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout)
        .width(86)
        .height(96)

    describe
        .constrainTopToBottomOf(image, 20)
        .fillHorizontally(72)

}

fun SearchEmptyViewHolder.initialize(describe_text: String) {
    describe.text = describe_text
}
