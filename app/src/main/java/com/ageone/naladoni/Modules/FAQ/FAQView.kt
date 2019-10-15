package com.ageone.naladoni.Modules.FAQ

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.ageone.naladoni.R
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.External.Base.Button.BaseButton
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.CirclePagerIndicatorDecoration
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.Modules.FAQ.rows.SliderViewHolder
import yummypets.com.stevia.*
import java.util.*
import kotlin.concurrent.schedule


class StartView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = FAQViewModel()

    val buttonEnter by lazy {
        val button = BaseButton()
        button.visibility = View.GONE
        button.textSize = 17F
        button.textColor = Color.WHITE
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.rgb(242, 119, 39)
        button.cornerRadius = 15
        button.gradient = Color.rgb(242, 132, 45)
        button.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        button.text = "Все понятно!"
        button.initialize()
        button
    }

    val timerFirst = Timer()
    val timerSecond = Timer()

    init {
        setBackgroundColor(Color.WHITE)

        toolbar.title = "О приложении"
        toolbar.textColor = Color.parseColor("#F06F28")

        toolbar.textView.textColor = Color.BLACK
        toolbar.textView.setOnClickListener {
            nextModule()
        }

        renderToolbar()

        bodyTable.adapter = Factory(this)
        bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
        bodyTable.addItemDecoration(CirclePagerIndicatorDecoration())

        val snapHelper = PagerSnapHelper()

        snapHelper.attachToRecyclerView(bodyTable)

        buttonEnter.setOnClickListener {
            nextModule()
        }

        renderUIO()

        timerFirst.schedule(5000){

            if ((bodyTable.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 0) {
                bodyTable.smoothScrollToPosition(1)
            }
        }
        timerSecond.schedule(10000) {

            if ((bodyTable.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 1) {
                bodyTable.smoothScrollToPosition(2)

            }
        }
    }

    private fun nextModule() {
        timerFirst.cancel()
        timerSecond.cancel()
        user.isAuthorized = true
        emitEvent?.invoke(FAQViewModel.EventType.OnLoaded.name)
    }

}

fun StartView.renderUIO() {

    var timer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            millisUntilFinished / 1000
            if ((bodyTable.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition() == 2) {
                currentActivity?.runOnUiThread { buttonEnter.visibility = View.VISIBLE }
            }

        }

        override fun onFinish() {
            currentActivity?.runOnUiThread { buttonEnter.visibility = View.VISIBLE }

        }
    }

    timer.start()

    innerContent.subviews(
        bodyTable,
        buttonEnter
    )

    buttonEnter
        .constrainTopToBottomOf(bodyTable,25)
        .constrainBottomToBottomOf(innerContent, 40)
        .fillHorizontally(32)

    bodyTable
        .constrainTopToTopOf(innerContent, 20)
        .constrainBottomToBottomOf(innerContent, 20)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)
}

class Factory(val rootModule: BaseModule): BaseAdapter<SliderViewHolder>() {

    private val list = listOf(
        "Смотри какие акции окружают тебя с помощью нашей интерактивной карты, и выбирай лучшие предложения в городе!",
        "Смотри какие акции окружают тебя с помощью нашей интерактивной карты, и выбирай лучшие предложения в городе!",
        "Смотри какие акции окружают тебя с помощью нашей интерактивной карты, и выбирай лучшие предложения в городе!")

    private val resourceImages = arrayOf(
        R.drawable.faq1,
        R.drawable.faq2,
        R.drawable.faq3
    )

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int): Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        return SliderViewHolder(layout)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
        viewHolder.textView.text = list[position]
        viewHolder.imageView.setBackgroundResource(resourceImages[position])
    }

}