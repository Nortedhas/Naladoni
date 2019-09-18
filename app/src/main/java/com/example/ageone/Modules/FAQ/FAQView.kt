package com.example.ageone.Modules.FAQ

import android.graphics.Color
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
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.RecyclerView.CirclePagerIndicatorDecoration
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*
import java.util.*
import kotlin.concurrent.schedule

class FAQView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {
    override fun unBind() {

    }
    val viewModel = FAQViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
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
        button.text = "Продолжить"
        button.initialize()
        button
    }
    val timerFirst = Timer()
    val timerSecond = Timer()

    init {


        setBackgroundResource(R.drawable.base_background)
        bodyTable.adapter = viewAdapter
        toolbar.title = "О приложении"
        toolbar.textColor = Color.rgb(242, 119, 39)

        renderToolbar()

        bodyTable.adapter = Factory(this)
        bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
        bodyTable.addItemDecoration(CirclePagerIndicatorDecoration())

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(bodyTable)

        buttonEnter.setOnClickListener {
            timerFirst.cancel()
            timerSecond.cancel()
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
    fun FAQView.renderUIO() {
        innerContent.subviews(
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
        renderBodyTable()
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

}



