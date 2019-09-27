package com.example.ageone.Modules.Map.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class MapDiscountCardsViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val back by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.elevation = 6F.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }
    val imageLock by lazy {
        val image = BaseImageView()
        image.setBackgroundResource(R.drawable.pic_groupfood)
        image.elevation = 5F.dp
        image
    }

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 17F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 13F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.setLines(2)
        textView
    }

    val buttonUse by lazy {
        val button = BaseButton()
        button.textSize = 11F
        button.textColor = Color.parseColor("#FFFAFA")
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.parseColor("#F37E25")
        button.cornerRadius = 2.dp
        button.gradient = Color.parseColor("#F06F28")
    	button.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        button.text = "Использовать"
        button.initialize()
        button
    }

    init {
        renderUI()
    }

}

fun MapDiscountCardsViewHolder.renderUI() {

    constraintLayout.subviews(
        back.subviews(
            imageLock,
            textViewTitle,
            textViewDescribe,
            buttonUse
        )
    )

    back
        .constrainLeftToLeftOf(constraintLayout, 8)
        .constrainRightToRightOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 16)
        .constrainTopToTopOf(constraintLayout, 8)
        .width(283)

    imageLock
        .constrainLeftToLeftOf(back, 8)
        .constrainTopToTopOf(back, 8)
        .width(44)
        .height(44)

    textViewTitle
        .constrainTopToTopOf(back, 8)
        .constrainLeftToRightOf(imageLock, 8)
//        .constrainRightToRightOf(back, 8)

    textViewDescribe
        .constrainTopToBottomOf(textViewTitle, 4)
        .constrainLeftToRightOf(imageLock, 8)
//        .constrainRightToRightOf(back, 8)

    buttonUse
        .constrainTopToBottomOf(textViewDescribe, 4)
        .constrainBottomToBottomOf(back, 8)
        .fillHorizontally(5)

}

fun MapDiscountCardsViewHolder.initialize(title: String, describe: String,icon: Int) {

    textViewTitle.text = title
    textViewDescribe.text = describe
    imageLock.setBackgroundResource(icon)

}
