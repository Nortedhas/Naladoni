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
import java.text.SimpleDateFormat
import java.util.*

class MainStockDataTextViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.parseColor("#333333")
        textViewLogin.textSize = 15F
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin
    }


    val format = SimpleDateFormat("dd.MM.yyyy")

    init {
        renderUI()
    }

}

fun MainStockDataTextViewHolder.renderUI() {

    constraintLayout.backgroundColor = Color.WHITE

    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 9)
        .fillHorizontally(16)
        .constrainBottomToBottomOf(constraintLayout,6)
}

fun MainStockDataTextViewHolder.initialize(timeFrom: Int, timeTo: Int) {
    val text:String = "Даты проведения: "
    val declaration = "с ${format.format(Date(timeFrom.toLong()))} до ${format.format(Date(timeTo.toLong()))}"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#f2842d")),
        0,  text.length , Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannableContent
}