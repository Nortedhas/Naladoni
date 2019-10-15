package com.ageone.naladoni.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.Base.View.BaseView
import com.ageone.naladoni.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class UserInformationViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val view by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.elevation = 3F.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val imagePhoto by lazy {
        val imageView = BaseImageView()
        imageView.elevation = 3F.dp
        imageView
    }

    val viewArrow by lazy {
        val view = BaseImageView()
        view.setBackgroundResource(R.drawable.ic_arrow)
        view
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#333333")
        textView.textSize = 17F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.gravity = Gravity.CENTER
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.setLines(3)
        textView
    }


    init {

        renderUI()
    }
}

fun UserInformationViewHolder.renderUI() {

    constraintLayout.subviews(
        view.subviews(
            textViewName,
            viewArrow
        ),
        imagePhoto

    )

    imagePhoto
        .height(74)
        .width(74)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainCenterYToTopOf(view)

    view
        .height(80)
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout,108)
        .constrainBottomToBottomOf(constraintLayout, 8)

    textViewName
        .fillHorizontally(8)
        .constrainTopToTopOf(view, 27)

    viewArrow
        .height(20)
        .width(12)
        .constrainRightToRightOf(view, 18)
        .constrainTopToTopOf(view)
        .constrainBottomToBottomOf(view)

}

fun UserInformationViewHolder.initialize(text: String,photo: Int) {

    textViewName.text = ("Я: " + text)
    addImageFromGlide(imagePhoto, photo)


}