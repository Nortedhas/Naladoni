package com.example.ageone.Modules.Start

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
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
        textViewHello.typeface = Typeface.DEFAULT_BOLD
        textViewHello.textSize = 29F
        textViewHello.textColor = Color.WHITE
        textViewHello.setBackgroundColor(Color.TRANSPARENT)
        textViewHello.text = "Добро пожаловать\nв Поток "
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


    val buttonEnter by lazy {
        val button = BaseButton()
        button.textSize = 17F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.rgb(0xA8, 0xAC, 0xEB)
        button.cornerRadius = 60
        button.gradient = Color.rgb(0x8B, 0x91, 0xC7)
        button.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        button.text = "Вход в приложение"
        button.initialize()
        button
    }

    val timerFirst = Timer()
    val timerSecond = Timer()

    init {
        setBackgroundColor(Color.TRANSPARENT)

        bodyTable.adapter = Factory(this)
        bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
        bodyTable.addItemDecoration(CirclePagerIndicatorDecoration())

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(bodyTable)

        buttonEnter.setOnClickListener {
            timerFirst.cancel()
            timerSecond.cancel()
            emitEvent?.invoke(StartViewModel.EventType.OnLoaded.toString())
        }

        renderUIO()
        timerFirst.schedule(5000){
            if ((bodyTable.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) {
                bodyTable.smoothScrollToPosition(1)
            }
        }
        timerSecond.schedule(10000){
            if ((bodyTable.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 1) {
                bodyTable.smoothScrollToPosition(2)
            }
        }
    }

    override fun unBind() {

    }

    companion object {
        var indexCurrentItem: Int = 0
    }

}

fun StartView.renderUIO() {

    innerContent.subviews(
        imageView,
        textViewHello,
        bodyTable,
        buttonEnter
    )

    buttonEnter
        .constrainBottomToBottomOf(innerContent, 40)
        .fillHorizontally(32)

    bodyTable
        .constrainBottomToTopOf(buttonEnter, 72)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    textViewHello
        .constrainBottomToTopOf(bodyTable, 56)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)

    imageView
        .constrainBottomToTopOf(textViewHello, 40)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)
}

class Factory(val rootModule: BaseModule): BaseAdapter<Factory.TextViewHolder>() {

    private val list = listOf(
        "Повседневная практика показывает, что новая модель организационной деятельности",
        "Повседневная практика показывает, что новая модель организационной деятельности",
        "Повседневная практика показывает, что новая модель организационной деятельности")

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(90)

        return TextViewHolder(layout)
    }

    override fun onBindViewHolder(viewHolder: TextViewHolder, position: Int) {
        viewHolder.textView.text = list[position]
    }

    class TextViewHolder(constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
        val textView by lazy {
            val textViewSmall = BaseTextView()
            textViewSmall.gravity = Gravity.CENTER
            textViewSmall.typeface = Typeface.DEFAULT
            textViewSmall.textSize = 19F
            textViewSmall.textColor = Color.WHITE
            textViewSmall.setBackgroundColor(Color.TRANSPARENT)
            textViewSmall
        }

        init {
            constraintLayout.subviews(
                textView
            )

            textView
                .constrainTopToTopOf(constraintLayout)
                .centerHorizontally()
                .width(320)
        }


    }

}