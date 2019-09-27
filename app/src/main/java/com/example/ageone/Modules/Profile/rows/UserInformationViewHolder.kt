package com.example.ageone.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.R
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class UserInformationViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val viewBack by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view.elevation = 3F.dp
        view
    }


    val viewArrow by lazy {
        val view = BaseView()
        view.setBackgroundResource(R.drawable.ic_arrow)
        view
    }

    val viewPhoto by lazy {
        val view = BaseView()
        view.setBackgroundResource(R.drawable.pic_profile)
        view
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
        viewBack.subviews(
            viewArrow,
            textViewName,
            viewPhoto
        )

    )


    viewBack
        .height(120)
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 8)

    viewPhoto
        .height(74)
        .width(74)
        .constrainLeftToLeftOf(viewBack)
        .constrainRightToRightOf(viewBack)
        .constrainTopToTopOf(viewBack,5)

    textViewName
        .fillHorizontally(90)
        .constrainTopToBottomOf(viewPhoto,8)

    viewArrow
        .height(20)
        .width(12)
        .constrainRightToRightOf(viewBack,18)
        .constrainTopToTopOf(viewBack)
        .constrainBottomToBottomOf(viewBack)

}

fun UserInformationViewHolder.initialize(text: String) {

    textViewName.text = ("Я: " + text)

}