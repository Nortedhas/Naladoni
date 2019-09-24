package com.example.ageone.Modules.LoadingScreen

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.ProgressBar
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.BaseProgressBar
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*

class LoadingScreenView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = LoadingScreenViewModel()

    val textViewHello by lazy {
        val textViewHello = BaseTextView()
        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT
        textViewHello.textSize = 19F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello.text = "Все акции твоего города"
        textViewHello
    }

    val progressBar by lazy {
        val progressBar = BaseProgressBar()
        progressBar.color = Color.WHITE
        progressBar.initialize()
        progressBar
    }

    val textViewName by lazy {
        val textViewHello = BaseTextView()
        textViewHello.gravity = Gravity.CENTER
        textViewHello.typeface = Typeface.DEFAULT_BOLD
        textViewHello.textSize = 29F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello.text = "NALADONI"
        textViewHello
    }

    val imageView by lazy {
        val imageView = BaseImageView()
        imageView
            .width(96)
            .height(95)
        imageView.setImageResource(R.drawable.logo)
        imageView.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
        imageView
    }

    init {

        setBackgroundResource(R.drawable.loading)
        renderUIO()
    }

}

fun LoadingScreenView.renderUIO() {

    innerContent.subviews(
        imageView,
        textViewHello,
        textViewName,
        progressBar
    )

    textViewName
        .constrainBottomToBottomOf(innerContent, 300)
        .fillHorizontally(35)

    textViewHello
        .constrainTopToBottomOf(textViewName, 5)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    imageView
        .constrainBottomToTopOf(textViewName, 5)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)
    progressBar
        .constrainTopToBottomOf(textViewHello, 100)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    var timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            millisUntilFinished / 1000

        }

        override fun onFinish() {
            emitEvent?.invoke(LoadingScreenViewModel.EventType.OnRegistrationPhonePressed.toString())
        }
    }
    timer.start()
}
