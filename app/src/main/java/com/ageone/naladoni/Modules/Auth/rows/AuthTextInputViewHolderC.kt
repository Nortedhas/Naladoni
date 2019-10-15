package com.ageone.naladoni.Modules.Auth.rows

import android.graphics.Color
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class InputViewHolderC(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val textInputL by lazy {
        val textInput = BaseTextInputLayout()

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        params.marginStart = (-2).dp
        params.updateMargins(left = (-2).dp)
        textInput.layoutParams = params


        textInput.boxStrokeColor = Color.parseColor("#f2842d")
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
        textInput.setInactiveUnderlineColor(Color.parseColor("#f2842d"))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 5F.dp
        }
        textInput
    }
    init {

        renderUI()
    }

}
fun InputViewHolderC.renderUI() {
    constraintLayout.subviews(
        textInputL
    )

    textInputL
        .constrainTopToTopOf(constraintLayout, 10)
        .fillHorizontally(18)
}

fun InputViewHolderC.initialize(hint: String, type: InputEditTextType) {
    textInputL.hint = hint
    textInputL.defineType(type)
}