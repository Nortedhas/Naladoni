package com.ageone.naladoni.Modules.Auth.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class RegistrationTextHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

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

fun RegistrationTextHolder.renderUI() {

    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 40)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)
}

fun RegistrationTextHolder.initialize() {
    val text = "Нажимая на кнопку “Зарегистрироваться”,\nя соглашаюсь с данным\n"
    val declaration = "пользовательским соглашением"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#f27727")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    spannableContent.setSpan(
        UnderlineSpan(),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textView.text = spannableContent
}