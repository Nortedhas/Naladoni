package com.ageone.naladoni.Modules.Filter.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.Base.View.BaseView
import com.ageone.naladoni.External.Libraries.Glide.addImageFromGlide
import com.ageone.naladoni.R
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
        .fillHorizontally(13)
        .fillVertically(13)

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
