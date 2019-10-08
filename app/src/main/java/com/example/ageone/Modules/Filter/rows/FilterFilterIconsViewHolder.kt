package com.example.ageone.Modules.Filter.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import com.example.ageone.R
import yummypets.com.stevia.*

class FilterFilterIconsViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val card by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view
    }

    val imageIcon by lazy {
        val image = BaseImageView()
        image
    }

    val imageViewSelected by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView.setImageResource(R.drawable.ic_selected_filter)
    // 	imageView.elevation = 5F.dp
        imageView
    }

    val textIcon by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#FFFFFF")
        textView.textSize = 12F
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }


}

fun FilterFilterIconsViewHolder.renderUI() {
    constraintLayout.subviews(
        card.subviews(
            imageIcon
        ),
        imageViewSelected,
        textIcon
    )

    card
        .height(67)
        .width(67)
        .constrainTopToTopOf(constraintLayout,8)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    imageIcon
        .fillHorizontally(16)
        .fillVertically(16)

    imageViewSelected
        .width(22)
        .height(22)
        .constrainLeftToLeftOf(card, 50)
        .constrainBottomToBottomOf(card, 50)

    imageViewSelected.visibility = View.GONE

    textIcon
        .fillHorizontally()
        .constrainTopToBottomOf(card,8)
        .constrainBottomToBottomOf(constraintLayout,8)

}

fun FilterFilterIconsViewHolder.initialize(text: String?, Icon: Int, isSelected: Boolean) {
    textIcon.text = text
    addImageFromGlide(imageIcon, Icon,0)
    if (isSelected) {
        imageViewSelected.visibility = View.VISIBLE
    }
}
