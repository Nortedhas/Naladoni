package com.example.ageone.Modules.RegistrationSMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.subviews
import yummypets.com.stevia.textColor

class RegistrationSMSTextViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#8A8A8F")
        textView.textSize = 15F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }


    init {

        renderUI()
    }

}

fun RegistrationSMSTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)
}

fun RegistrationSMSTextViewHolder.initialize(text: String) {
    val spannableContent = SpannableString(text + "0:39")
    spannableContent.setSpan(ForegroundColorSpan(Color.parseColor("#707ABA")),
        text.length,  text.length + 4, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    textView.text = spannableContent
}