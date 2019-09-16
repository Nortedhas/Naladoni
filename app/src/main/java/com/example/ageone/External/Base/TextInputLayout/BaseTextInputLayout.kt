package com.example.ageone.External.Base.TextInputLayout

import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.EditText.limitLength
import com.example.ageone.External.Base.EditText.phoneMask
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.style

class BaseTextInputLayout: TextInputLayout(currentActivity) {

    init {
        style {
            setHintTextAppearance(R.style.MyHintText)
            setErrorTextAppearance(R.style.ErrorText)
        }

        val text = BaseTextInputEditText()
        addView(text)
    }

    fun initPassword(colorToggled: Int = boxStrokeColor) {
        editText?.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        style {
            isPasswordVisibilityToggleEnabled = true
            setPasswordVisibilityToggleTintList(ColorStateList.valueOf(colorToggled))
        }
    }


    fun defineType (type: InputEditTextType) = when(type) {

        InputEditTextType.EMAIL -> {
            editText?.inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        }

        InputEditTextType.NUMERIC -> {
            editText?.inputType = InputType.TYPE_CLASS_NUMBER
        }

        InputEditTextType.URI -> {
            editText?.inputType = InputType.TYPE_TEXT_VARIATION_URI
        }

        InputEditTextType.PHONE -> {
            editText?.let{ editText ->
                editText.inputType = InputType.TYPE_CLASS_NUMBER
                editText.keyListener = DigitsKeyListener.getInstance("1234567890")
                editText.limitLength(18)

                editText.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        if (before == 0) {
                            val newPhone = editText.phoneMask(s)
                            editText.setText(newPhone)
                            editText.setSelection(editText.text?.length ?: 0)
                        }
                    }
                })
            }


        }

        InputEditTextType.TEXT -> {}
    }

    fun setInactiveUnderlineColor(color: Int) {
        editText?.backgroundTintList = ColorStateList.valueOf(color)
    }

}

class BaseTextInputEditText: TextInputEditText(currentActivity) {
}

enum class InputEditTextType{
    TEXT, NUMERIC, EMAIL, URI, PHONE;
}


/*
val textInputL by lazy {
    val textInputL = BaseTextInputLayout()
    textInputL.hint = "phone"
    textInputL.boxStrokeColor = Color.TRANSPARENT
    textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_FILLED)
    textInputL.defineType(InputEditTextType.PHONE)
    textInputL.setInactiveUnderlineColor(Color.GREEN)
    textInputL.editText?.textColor = Color.MAGENTA
    textInputL
}

val textInputPassword by lazy {
    val textInputL = BaseTextInputLayout()
    textInputL.hint = "password"
    textInputL.boxStrokeColor = Color.MAGENTA
    textInputL.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
    textInputL.initPassword()
    textInputL
}*/
