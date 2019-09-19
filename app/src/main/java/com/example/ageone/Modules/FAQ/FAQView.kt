package com.example.ageone.Modules.FAQ

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
import com.example.ageone.Modules.FAQ.rows.SpinerViewHolder
import com.example.ageone.Modules.Loading.StartViewModel
import yummypets.com.stevia.*
import java.util.*
import java.util.stream.Collectors.toList
import kotlin.concurrent.schedule


class StartView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {
    override fun unBind() {}

    val viewModel = FAQViewModel()
    val buttonEnter by lazy {
        val button = BaseButton()
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
    val buttonSkip by lazy {
        val button = BaseButton()
        button.textSize = 12F
        button.textColor = Color.BLACK
        button.typeface = Typeface.DEFAULT
        button.backgroundColor = Color.TRANSPARENT
        button.orientation = GradientDrawable.Orientation.BOTTOM_TOP
        button.text = "пропустить"
        button.initialize()
        button
    }
    val textToolbar by lazy{
      val textBar = BaseTextView()
//        textBar.gravity = Gravity.CENTER
        textBar.typeface = Typeface.DEFAULT_BOLD
        textBar.textSize = 34F
        textBar.textColor =  Color.rgb(242, 132, 45)
        textBar.text = "О приложении"
        textBar.orientation = GradientDrawable.Orientation.BOTTOM_TOP
        textBar
    }
    val timerFirst = Timer()
        val timerSecond = Timer()

        init {
            setBackgroundColor(Color.WHITE)
            bodyTable.adapter = Factory(this)
            bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
            bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
            bodyTable.addItemDecoration(CirclePagerIndicatorDecoration())

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(bodyTable)

            buttonEnter.setOnClickListener {
                timerFirst.cancel()
                timerSecond.cancel()
                emitEvent?.invoke(FAQViewModel.EventType.OnLoaded.toString())
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

    companion object {
        var indexCurrentItem: Int = 0
    }

}

fun StartView.renderUIO() {

    innerContent.subviews(
        bodyTable,
        buttonEnter,
        buttonSkip,
        textToolbar
    )
    buttonSkip
        .constrainLeftToLeftOf(innerContent, 260)

    textToolbar
        .constrainTopToTopOf(innerContent, 35)
        .constrainLeftToLeftOf(innerContent, 16)

    buttonEnter
        .constrainBottomToBottomOf(innerContent, 40)
        .fillHorizontally(32)

    bodyTable
        .constrainBottomToTopOf(buttonEnter, 52)
        .constrainLeftToLeftOf(innerContent)
        .constrainRightToRightOf(innerContent)
}

class Factory(val rootModule: BaseModule): BaseAdapter<SpinerViewHolder>() {

    private val list = listOf(
        "Смотри какие скидки",
        "Нужна скидка",
        "Все что нужно")
    private val resourceImages = arrayOf(
        R.drawable.faq1,
        R.drawable.faq2,
        R.drawable.faq3)

    override fun getItemCount() = list.size
    override fun getItemViewType(position: Int): Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpinerViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        return SpinerViewHolder(layout)
    }

    override fun onBindViewHolder(ViewHolder: SpinerViewHolder, position: Int) {
        ViewHolder.textView.text = list[position]
        ViewHolder.imageView.setBackgroundResource(resourceImages[position])


    }

}