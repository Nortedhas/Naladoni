package com.ageone.naladoni.Modules.SMS.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.subviews
import yummypets.com.stevia.textColor
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule

class RegistrationSMSTextViewHolder(val constraintLayout: ConstraintLayout, val timer: Timer?): BaseViewHolder(constraintLayout) {

    val textView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#8A8A8F")
        textView.textSize = 15F
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    var timeBeforeRedirect = 60000L
    val time = SimpleDateFormat("mm:ss")

    init {

        renderUI()
    }

    fun setTime(timeBeforeRedirect: Long) {
        val text = "Если Вы не получили смс, запросить код повторно можно через "
        val spannableContent = SpannableString(text + time.format(Date(timeBeforeRedirect)))
        spannableContent.setSpan(ForegroundColorSpan(Color.parseColor("#f2842d")),
            text.length,  text.length + 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        textView.text = spannableContent

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

fun RegistrationSMSTextViewHolder.initialize(completion: (()->(Unit))) {

    timer?.schedule(0, 1000){
        timeBeforeRedirect -= 1000L
        currentActivity?.runOnUiThread {
            if (timeBeforeRedirect == 0L) {
                timer.cancel()
                completion.invoke()
            } else {
                setTime(timeBeforeRedirect)
            }
        }
    }

}