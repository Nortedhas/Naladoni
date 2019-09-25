package com.example.ageone.Modules.List.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class СardViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {
    val card by lazy {
        val view = BaseView()
        view.cornerRadius = 15.dp
        view.elevation = 5F.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }
    val photoLogo by lazy {
        val image = BaseImageView()
        image

    }

    val bottomframe by lazy {
        val frame = BaseImageView()
        frame.setBackgroundResource(R.drawable.ic_bottom_frame)
        frame
    }
    val textViewDescribe by lazy {
        val describe = BaseTextView()
        describe.gravity = Gravity.START
        describe.typeface = Typeface.DEFAULT
        describe.textSize = 13F
        describe.textColor = Color.parseColor("#333333")
        describe.setBackgroundColor(Color.TRANSPARENT)
        describe
    }

    val textViewLogo by lazy {
        val Logo = BaseTextView()
        Logo.gravity = Gravity.START
        Logo.typeface = Typeface.DEFAULT_BOLD
        Logo.textSize = 15F
        Logo.textColor = Color.parseColor("#333333")
        Logo.setBackgroundColor(Color.TRANSPARENT)
        Logo
    }
    val textViewDate by lazy {
        val date = BaseTextView()
        date.gravity = Gravity.START
        date.typeface = Typeface.DEFAULT
        date.textSize = 13F
        date.textColor = Color.parseColor("#AFAFB4")
        date.setBackgroundColor(Color.TRANSPARENT)
        date
    }
    init {

        renderUI()
    }

}

fun СardViewHolder.renderUI() {
    constraintLayout.subviews(
        card.subviews(
            photoLogo,
            bottomframe,
            textViewDescribe,
            textViewLogo,
            textViewDate
        )
    )
    card
        .width(162)
        .constrainTopToTopOf(constraintLayout,8)
        .constrainBottomToBottomOf(constraintLayout,8)
        .constrainLeftToLeftOf(constraintLayout,8)
    photoLogo
        .constrainTopToTopOf(card)
        .height(105)
    bottomframe
        .constrainTopToTopOf(card, 100)
    textViewDescribe
        .constrainTopToBottomOf(bottomframe,15)
        .constrainLeftToLeftOf(card,9)
    textViewLogo
        .constrainTopToBottomOf(bottomframe,35)
        .constrainLeftToLeftOf(card,9)
    textViewDate
        .constrainTopToBottomOf(textViewLogo,5)
        .constrainLeftToLeftOf(card,9)
        .constrainBottomToBottomOf(card,10)


}

fun СardViewHolder.initialize(describe: String, logo: String, date: String, photo_logo: Int) {

    textViewDescribe.text = describe
    textViewLogo.text = logo
    textViewDate.text = date
    photoLogo.setBackgroundResource(photo_logo)


}