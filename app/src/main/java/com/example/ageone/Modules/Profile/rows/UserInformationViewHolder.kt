package com.example.ageone.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class UserInformationViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val back by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view.elevation = 3F.dp
        view
    }


    val image by lazy {
        val base = BaseView()
        base.setBackgroundResource(R.drawable.ic_arrow)
        base
    }

    val imagePhoto by lazy {
        val base = BaseView()
        base.setBackgroundResource(R.drawable.pic_profile)
        base
    }

    val textViewName by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#333333")
        textView.textSize = 17F
        textView.typeface = Typeface.DEFAULT_BOLD
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
        back.subviews(
            image,
            textViewName,
            imagePhoto
        )

    )


    back
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 8)

    imagePhoto
        .height(74)
        .width(74)
        .constrainLeftToLeftOf(back)
        .constrainRightToRightOf(back)
        .constrainTopToTopOf(back, 8)

    textViewName
        .constrainTopToBottomOf(imagePhoto,10)
        .constrainBottomToBottomOf(back,10)

    image
        .height(20)
        .width(12)
        .constrainRightToRightOf(back)
        .constrainTopToTopOf(back)
        .constrainBottomToBottomOf(back)

}

fun UserInformationViewHolder.initialize(text: String) {

    textViewName.text = ("Я: " + text)

}