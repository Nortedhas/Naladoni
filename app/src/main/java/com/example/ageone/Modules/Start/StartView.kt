package com.example.ageone.Modules.Start

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.RecyclerView.CirclePagerIndicatorDecoration
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*
import java.util.*
import kotlin.concurrent.schedule

class StartView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = StartViewModel()

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



    val timerFirst = Timer()


    init {
        setBackgroundResource(R.drawable.loading)
        renderUIO()
    }

    override fun unBind() {

    }
}

fun StartView.renderUIO() {

    innerContent.subviews(
        imageView,
        textViewHello,
        textViewName
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

//    timerFirst.schedule(5000){
//        emitEvent?.invoke(StartViewModel.EventType.OnRegistrationPhonePressed.toString())
//
//    }


    var timer = object : CountDownTimer(3000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            millisUntilFinished / 1000

        }
        override fun onFinish() {
            emitEvent?.invoke(StartViewModel.EventType.OnRegistrationPhonePressed.toString())
            imageView.setImageResource(R.drawable.home)

        }
    }
    timer.start()
}
//class TextViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
//        val textView by lazy {
//            val textViewSmall = BaseTextView()
//            textViewSmall.gravity = Gravity.CENTER
//            textViewSmall.typeface = Typeface.DEFAULT
//            textViewSmall.textSize = 19F
//            textViewSmall.textColor = Color.WHITE
//            textViewSmall.setBackgroundColor(Color.TRANSPARENT)
//            textViewSmall
//        }
//
//        init {
//            constraintLayout.subviews(
//                textView
//            )
//
//            textView
//                .constrainTopToTopOf(constraintLayout)
//                .centerHorizontally()
//                .width(320)
//        }
//
//
//    }

