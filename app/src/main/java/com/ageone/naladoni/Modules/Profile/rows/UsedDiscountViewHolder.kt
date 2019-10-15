package com.ageone.naladoni.Modules.Profile.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.Base.View.BaseView
import yummypets.com.stevia.*

class UsedDiscountViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val back by lazy {
        val view = BaseView()
        view.backgroundColor = Color.WHITE
        view.cornerRadius = 8.dp
        view.initialize()
        view.elevation = 3F.dp
        view
    }

    val textViewСounter by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#F06F28")
        textView.textSize = 17F
        textView.gravity = Gravity.END
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#333333")
        textView.textSize = 15F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.setLines(3)
        textView
    }


    init {

        renderUI()
    }
}

fun UsedDiscountViewHolder.renderUI() {

    constraintLayout.subviews(
        back.subviews(
            textViewСounter,
            textView
        )
    )

    back
        .height(70)
        .fillHorizontally(8)
        .constrainTopToTopOf(constraintLayout, 8)
        .constrainBottomToBottomOf(constraintLayout, 8)

    textViewСounter
        .fillHorizontally()
        .constrainTopToTopOf(back)
        .constrainBottomToBottomOf(back)
        .constrainRightToRightOf(back, 18)
    textView
        .constrainTopToTopOf(back,15)
        .constrainLeftToLeftOf(back, 19)
        .width(250)
}

fun UsedDiscountViewHolder.initialize(counter:String, text: String) {
    textViewСounter.text=counter
    textView.text = text

}