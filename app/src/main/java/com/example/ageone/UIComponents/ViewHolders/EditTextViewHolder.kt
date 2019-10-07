package com.example.ageone.UIComponents.ViewHolders

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.EditText.BaseEditText
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class EditTextViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

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

fun EditTextViewHolder.renderUI() {
    constraintLayout.subviews(
        editText
    )

    editText
        .constrainTopToTopOf(constraintLayout,(utils.variable.displayWidth - 48) / 3)
        .fillHorizontally(16)
}

fun EditTextViewHolder.initialize(text: String,hint: String) {
    editText.hint = hint
    editText.setText(text)
}