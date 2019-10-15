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

        textInput.boxStrokeColor = Color.parseColor("#F27D25")
        textInput.setInactiveUnderlineColor(Color.rgb(242, 125, 37))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#333333")
            editText.textSize = 20F
            editText.maxLines = 1
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