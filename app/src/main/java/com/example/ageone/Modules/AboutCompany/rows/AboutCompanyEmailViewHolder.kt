package com.example.ageone.Modules.AboutCompany.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class AboutCompanyEmailViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textViewLogin = BaseTextView()
        textViewLogin.textColor = Color.rgb(0x8A,0x8A,0x8F)
        textViewLogin.textSize = 15F
        textViewLogin.gravity = Gravity.CENTER
        textViewLogin.typeface = Typeface.DEFAULT
        textViewLogin.setBackgroundColor(Color.TRANSPARENT)
        textViewLogin
    }

    init {
        renderUI()
    }

}

fun AboutCompanyEmailViewHolder.renderUI() {

    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 11)
        .fillHorizontally(20)
}


fun AboutCompanyEmailViewHolder.initialize() {
    val text = "или напишите нам на почту: "
    val declaration = "deal@naladoni.com "

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#f27727")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    spannableContent.setSpan(
        UnderlineSpan(),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textView.text = spannableContent

}