package com.example.ageone.Modules.City.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.UIComponents.ViewHolders.renderUI
import yummypets.com.stevia.dp
import yummypets.com.stevia.subviews


class SpinnerViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {




    val spinnertextView by lazy {
        val textView = BaseTextView()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textView.layoutParams = params


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
    )
}