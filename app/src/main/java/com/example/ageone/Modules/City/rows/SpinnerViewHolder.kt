package com.example.ageone.Modules.City.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.UIComponents.ViewHolders.renderUI
import yummypets.com.stevia.subviews


class SpinnerViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textViewTitle by lazy {
        val textView = BaseTextView()

        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)

        textView
    }


    init {
        renderUI()
    }

}
fun SpinnerViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewTitle
    )
}