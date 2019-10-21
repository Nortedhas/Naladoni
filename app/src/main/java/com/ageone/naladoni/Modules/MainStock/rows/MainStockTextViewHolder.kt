package com.ageone.naladoni.Modules.MainStock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
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
        .constrainBottomToBottomOf(constraintLayout,6)
}

fun MainStockTextViewHolder.initialize(position: Int,declaration:String) {

     var text:String = ""

    if(position == 1){
        text = "Акция: "
    }else{
        text = "Даты проведения:"
    }
    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#f2842d")),
        0,  text.length , Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannableContent
}