package com.ageone.naladoni.Modules.MainStock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.Base.View.BaseView
import com.ageone.naladoni.External.Libraries.Glide.addImageFromGlide
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
    val ImageBackground by lazy {
        val Image = BaseView()
        Image.backgroundColor = Color.parseColor("#F37E25")
        Image.cornerRadius = 40.dp
        Image.initialize()
        Image
    }
    val imageIconView by lazy {
        val imageView = BaseImageView()
        imageView
    }
    val BoxView by lazy {
        val imageView = BaseView()
        imageView.backgroundColor = Color.WHITE
        imageView.initialize()
        imageView
    }

    val textViewTimeDescribe by lazy {
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
        BoxView,
        view.subviews(
            textViewLogo,
            textViewTimeDescribe,
            textViewTitle
        ),
        ImageBackground.subviews(
            imageIconView
        )
    )

    ImageBackground
        .height(74)
        .width(74)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
        .constrainCenterYToTopOf(view)

    imageIconView
        .height(32)
        .width(32)
        .constrainTopToTopOf(ImageBackground)
        .constrainBottomToBottomOf(ImageBackground)
        .constrainLeftToLeftOf(ImageBackground)
        .constrainRightToRightOf(ImageBackground)

    BoxView
        .height(60)
        .fillHorizontally()
        .constrainCenterYToBottomOf(view)

    view
        .height(120)
        .fillHorizontally()
        .constrainBottomToBottomOf(constraintLayout)

    textViewLogo
        .constrainTopToTopOf(view, 46)
        .fillHorizontally()

    textViewTitle
        .constrainTopToBottomOf(textViewLogo, 9)
        .fillHorizontally(90)

    textViewTimeDescribe
        .constrainTopToBottomOf(textViewTitle)
        .fillHorizontally(10)

}

fun MainStockDescribeViewHolder.initialize(textLogo: String, imageLogo: Int,
                                           timeDescribe: String) {

    textViewLogo.text = textLogo
    textViewTimeDescribe.text = timeDescribe
    textViewTitle.text = "Время работы: "
    addImageFromGlide(imageIconView, imageLogo,0)

}
