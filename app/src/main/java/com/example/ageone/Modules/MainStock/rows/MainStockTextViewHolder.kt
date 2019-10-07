package com.example.ageone.Modules.MainStock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class MainStockTextViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.parseColor("#333333")
        textViewLogin.textSize = 15F
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin
    }

    init {
        renderUI()
    }

}

fun MainStockTextViewHolder.renderUI() {

    constraintLayout.backgroundColor = Color.WHITE

    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 8)
        .fillHorizontally(16)
        .constrainBottomToBottomOf(constraintLayout,12)
}

fun MainStockTextViewHolder.initialize(text:String,declaration:String) {

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#f2842d")),
        0,  text.length , Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannableContent
}