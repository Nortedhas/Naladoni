package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class СardViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val viewCard by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.elevation = 5F.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val imageViewLogo by lazy {
        val imageView = BaseImageView()
        imageView
    }

    val imageViewBottomFrame by lazy {
        val imageView = BaseImageView()
        imageView.setBackgroundResource(R.drawable.ic_bottom_frame)
        imageView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 13F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.setLines(3)
        textView
    }

    val textViewLogo by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDate by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 13F
        textView.textColor = Color.parseColor("#AFAFB4")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }
    init {

        renderUI()
    }

}

fun СardViewHolder.renderUI() {

    constraintLayout.subviews(
        viewCard.subviews(
            imageViewLogo,
            imageViewBottomFrame,
            textViewDescribe,
            textViewLogo,
            textViewDate
        )
    )

    viewCard
        .width((utils.variable.displayWidth - 48) / 2 )
        .constrainTopToTopOf(constraintLayout,8)
        .constrainBottomToBottomOf(constraintLayout,8)
        .constrainLeftToLeftOf(constraintLayout,8)
        .constrainRightToRightOf(constraintLayout,4)

    imageViewLogo
        .width((utils.variable.displayWidth - 48) / 2 )
        .height((utils.variable.displayWidth - 48) / 2 * .64F)
        .constrainTopToTopOf(viewCard)

    imageViewBottomFrame
        .constrainCenterYToBottomOf(imageViewLogo)

    textViewDescribe
        .constrainTopToBottomOf(imageViewLogo,16)
        .fillHorizontally(8)

    textViewLogo
        .fillHorizontally(8)
        .constrainTopToBottomOf(textViewDescribe,24)

    textViewDate
        .fillHorizontally(8)
        .constrainTopToBottomOf(textViewLogo,5)
        .constrainBottomToBottomOf(viewCard,10)

}

fun СardViewHolder.initialize(describe: String, logo: String, date: String, photo_logo: Int) {

    textViewDescribe.text = describe
    textViewLogo.text = logo
    textViewDate.text = date
    imageViewLogo.setBackgroundResource(photo_logo)

}