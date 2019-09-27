package com.example.ageone.Modules.AboutCompany.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import com.example.ageone.R
import yummypets.com.stevia.*

class AboutCompanyViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {
    val card by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.elevation = 5F.dp
        view.backgroundColor = Color.TRANSPARENT
        view.initialize()
        view
    }

    val imageViewLogo by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.pic_logo_naladoni)
        imageView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.setLines(3)
        textView
    }

    val textView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 18F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }

}

fun AboutCompanyViewHolder.renderUI() {
    constraintLayout.subviews(
        card.subviews(
            imageViewLogo,
            textViewDescribe,
            textView


        )
    )
    card
        .fillHorizontally(50)
        .height(50)


    imageViewLogo
        .constrainLeftToLeftOf(card)
        .constrainBottomToBottomOf(card)
        .constrainTopToTopOf(card)
    textView
        .constrainLeftToRightOf(imageViewLogo, 15)
        .constrainTopToTopOf(imageViewLogo, 2)
    textViewDescribe
        .constrainTopToBottomOf(textView, 1)
        .constrainLeftToLeftOf(textView)


}

fun AboutCompanyViewHolder.initialize(describe: String, logo: String) {
    textViewDescribe.text = describe
    textView.text = logo
}
