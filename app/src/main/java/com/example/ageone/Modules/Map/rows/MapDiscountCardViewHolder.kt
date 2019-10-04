package com.example.ageone.Modules.Map.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.R
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class MapDiscountCardViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val viewBack by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.elevation = 6F.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }
    val imageViewType by lazy {
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
        textView.setLines(1)
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
        button.setPadding(0,0,0,0)
        button.minHeight = 0
        button.minWidth = 0
        button
    }

    init {
        renderUI()
    }

}

fun MapDiscountCardViewHolder.renderUI() {

    constraintLayout.subviews(
        viewBack.subviews(
            imageViewType,
            textViewTitle,
            textViewDescribe,
            buttonUse
        )
    )

    viewBack
        .constrainLeftToLeftOf(constraintLayout, 8)
        .constrainRightToRightOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 16)
        .constrainTopToTopOf(constraintLayout, 8)
        .width(95.dp)

    imageViewType
        .constrainLeftToLeftOf(viewBack, 8)
        .constrainTopToTopOf(viewBack, 8)
        .width(44)
        .height(44)

    textViewTitle
        .fillHorizontally()
        .constrainTopToTopOf(viewBack, 8)
        .constrainLeftToRightOf(imageViewType, 8)
        .constrainRightToRightOf(viewBack, 8)

    textViewDescribe
        .fillHorizontally()
        .constrainTopToBottomOf(textViewTitle, 4)
        .constrainLeftToRightOf(imageViewType, 8)
        .constrainRightToRightOf(viewBack, 8)

    buttonUse
        .constrainTopToBottomOf(textViewDescribe, 4)
        .constrainBottomToBottomOf(viewBack, 8)
        .fillHorizontally(5)

}

fun MapDiscountCardViewHolder.initialize(title: String, describe: String, icon: Int) {

    textViewTitle.text = title
    textViewDescribe.text = describe
    addImageFromGlide(imageViewType, icon)

}
