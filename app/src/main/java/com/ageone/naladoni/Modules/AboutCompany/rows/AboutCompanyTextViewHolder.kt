package com.ageone.naladoni.Modules.AboutCompany.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class AboutCompanyTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }

}

fun AboutCompanyTextViewHolder.renderUI() {

    constraintLayout.subviews(
        textView,
        textViewDescribe
    )

    textView
        .constrainTopToTopOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout,8)

    textViewDescribe
        .fillHorizontally(8)
        .constrainTopToBottomOf(textView)
        .constrainLeftToLeftOf(constraintLayout,8)
        .constrainBottomToBottomOf(constraintLayout,11)

}

fun AboutCompanyTextViewHolder.initialize(describe: String, title: String) {
    textViewDescribe.text = describe
    textView.text = title

}
