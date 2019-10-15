package com.ageone.naladoni.Modules.AboutCompany.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.Base.View.BaseView
import com.ageone.naladoni.R
import yummypets.com.stevia.*

class AboutCompanyLogoViewHolder(val constraintLayout: ConstraintLayout) :
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
        textView.textColor = Color.parseColor("#AFAFB4")
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

fun AboutCompanyLogoViewHolder.renderUI() {
    constraintLayout.subviews(
        card.subviews(
            imageViewLogo,
            textViewDescribe,
            textView


        )
    )
    card
        .fillHorizontally(60)


    imageViewLogo
        .width(44)
        .height(48)
        .constrainLeftToLeftOf(card)
        .constrainBottomToBottomOf(card)
        .constrainTopToTopOf(card,18)
    textView
        .constrainLeftToRightOf(imageViewLogo, 12)
        .constrainTopToTopOf(imageViewLogo, 5)
    textViewDescribe
        .constrainTopToBottomOf(textView)
        .constrainLeftToLeftOf(textView)


}

fun AboutCompanyLogoViewHolder.initialize(describe: String, logo: String) {
    textViewDescribe.text = describe
    textView.text = logo
}
