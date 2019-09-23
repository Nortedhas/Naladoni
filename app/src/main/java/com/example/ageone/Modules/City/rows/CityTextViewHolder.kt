package com.example.ageone.Modules.City.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.Modules.SMS.rows.RegistrationSMSTextViewHolder
import yummypets.com.stevia.*

class CityViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

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

fun CityViewHolder.renderUI() {
    constraintLayout.subviews(
        textView
    )

    textView
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(16)
}

fun CityViewHolder.initialize(text: String) {
    val spannableContent = SpannableString(text)
    ForegroundColorSpan(Color.parseColor("#f2842d"))
    textView.text = spannableContent
}
