package com.example.ageone.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.R
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*


class ServiceInformationViewHolder(val constraintLayout: ConstraintLayout) :
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

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#F06F28")
        textView.textSize = 15F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.setLines(3)
        textView
    }


    init {

        renderUI()
    }
}

fun ServiceInformationViewHolder.renderUI() {

    constraintLayout.subviews(
        back.subviews(
            textView,
            image
        )
    )

    back
        .height(70)
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainBottomToBottomOf(constraintLayout, 8)

    textView
        .constrainTopToTopOf(back,26)
        .constrainLeftToLeftOf(back, 19)

    image
        .height(20)
        .width(12)
        .constrainRightToRightOf(back, 16)
        .constrainBottomToBottomOf(back)
        .constrainTopToTopOf(back)
}

fun ServiceInformationViewHolder.initialize( text: String) {
    textView.text = text

}