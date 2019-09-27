package com.example.ageone.Modules.AboutCompany.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class TextAboutViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {
    val textView by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 21F
        textView.textColor = Color.parseColor("#333333")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textViewDescribe by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.textSize = 15F
        textView.textColor = Color.parseColor("#AFAFB4")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }

}

fun TextAboutViewHolder.renderUI() {
    constraintLayout.subviews(
        textView,
        textViewDescribe
    )
    textView
        .constrainTopToTopOf(8)
        .constrainLeftToLeftOf(constraintLayout,8)
    textViewDescribe
        .fillHorizontally(8)
        .constrainTopToBottomOf(textView,8)
        .constrainLeftToLeftOf(constraintLayout,8)




}

fun TextAboutViewHolder.initialize(describe: String, title: String) {
    textViewDescribe.text = describe
    textView.text = title

}
