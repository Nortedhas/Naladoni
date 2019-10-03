package com.example.ageone.Modules.City.rows

import android.graphics.Color
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.EditText.BaseEditText
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class CityEditTextViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val editText by lazy {
        val editText = BaseEditText()
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        editText.layoutParams = params
        editText.textSize = 15F
        editText.textColor = Color.parseColor("#333333")
        editText
    }

    init {
        renderUI()
    }

}

fun CityEditTextViewHolder.renderUI() {
    constraintLayout.subviews(
        editText
    )

    editText
        .constrainTopToTopOf(constraintLayout,(utils.variable.displayWidth - 48) / 3)
        .fillHorizontally(16)
}

fun CityEditTextViewHolder.initialize(text: String) {
    editText.setText(text)
}