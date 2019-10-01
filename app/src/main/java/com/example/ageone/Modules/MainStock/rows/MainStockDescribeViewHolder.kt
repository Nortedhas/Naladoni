package com.example.ageone.Modules.MainStock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class MainStockDescribeViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {
    val view by lazy {
        val view = BaseView()
        view.cornerRadius = 30.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

    val imageIconView by lazy {
        val imageView = BaseImageView()
        imageView
    }
    val imageBoxView by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.WHITE
        imageView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 15F
        textView.gravity = Gravity.CENTER
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }
    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 15F
        textView.gravity = Gravity.CENTER
        textView.textColor = Color.parseColor("#f27727")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewLogo by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.gravity = Gravity.CENTER
        textView.textSize = 21F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }


    init {

        renderUI()
    }

}

fun MainStockDescribeViewHolder.renderUI() {
    constraintLayout.subviews(
        imageBoxView,
        view.subviews(
            textViewLogo,
            textViewDescribe,
            textViewTitle
        ),
        imageIconView
    )

    imageIconView
        .height(74)
        .width(74)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainCenterYToTopOf(view)

    view
        .height(120)
        .fillHorizontally()
        .constrainBottomToBottomOf(constraintLayout)

    textViewLogo
        .constrainTopToTopOf(view,44)
        .fillHorizontally()

    textViewTitle
        .constrainTopToTopOf(view,68)
        .fillHorizontally(90)

    textViewDescribe
        .constrainTopToBottomOf(textViewTitle,3)
        .fillHorizontally(10)

    imageBoxView
        .height(60)
        .constrainCenterYToBottomOf(view)
        .fillHorizontally()
}

fun MainStockDescribeViewHolder.initialize(text:String,describe:String,textlogo:String, logo:Int) {
    textViewLogo.text = textlogo
    textViewTitle.text= text
    textViewDescribe.text = describe
    imageIconView.setBackgroundResource(logo)

}
