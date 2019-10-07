package com.example.ageone.Modules.Filter

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.Modules.Filter.rows.*
import com.example.ageone.R
import yummypets.com.stevia.*

class FilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FilterViewModel()

    val clear by lazy {
        val text = BaseTextView()
        text.textColor = Color.parseColor("#FFFFFF")
        text.textSize = 17F
        text.text = "Очистить"
        text.orientation = GradientDrawable.Orientation.BOTTOM_TOP
        text.typeface = Typeface.DEFAULT
        text.setBackgroundColor(Color.TRANSPARENT)
        text
    }
    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    
    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    13, 14 -> 3
                    15 -> 3
                    else -> 1
                }
            }
        }
        layoutManager
    }
    
    val textViewClear by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 17F
        textView.textColor = Color.WHITE
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView.text = "Очистить"
    // 	textView.elevation = 5F.dp
        textView
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_filter)

        toolbar.title = "Покажи мне"
        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {
        private val list = listOf(
            "Поесть",
            "Автосервис",
            "Красота",
            "Развлечения",
            "Фитнес",
            "Здоровье",
            "Для детей",
            "Услуги",
            "Товары",
            "Образование",
            "Туризм",
            "Для животных",
            "18+"
        )

        private val resourceImages = arrayOf(
            R.drawable.pic_food,
            R.drawable.pic_car,
            R.drawable.pic_women_hairstyling,
            R.drawable.pic_balloons,
            R.drawable.pic_exercise,
            R.drawable.pic_pharmacy,
            R.drawable.pic_duck,
            R.drawable.pic_repairs,
            R.drawable.pic_tovari,
            R.drawable.pic_book,
            R.drawable.pic_hiking,
            R.drawable.pic_paw_print,
            R.drawable.pic_frame
        )

        private val FilterCardType = 0
        private val FilterType = 1
        private val ButtonType = 2

        override fun getItemCount() = 16//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            in 0..12 -> FilterCardType
            13, 14 -> FilterType
            15 -> ButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FilterCardType -> {
                    FilterFilterIconsViewHolder(layout)
                }
                FilterType -> {
                    FilterSwitchViewHolder(layout)
                }
                ButtonType -> {
                    FilterButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            val event = 0..12
            when (holder) {
                is FilterFilterIconsViewHolder -> {
                    for (x in event) {
                        holder.initialize(list[position], resourceImages[position])
                    }
                    holder.card.setOnClickListener {
                        rootModule.emitEvent?.invoke(FilterViewModel.EventType.OnlouderFilter.toString())
                    }

                }
                is FilterSwitchViewHolder -> {
                    when (position) {
                        13 -> {
                            holder.initialize("Только популярные")
                            holder.switch.setOnClickListener {
                                if (holder.switch.isChecked) {
                                    alertManager.single(
                                        message = "В вашем городе нет подарков в данной категории, попробуйте поискать в другой ",
                                        title = "Мы ничего не нашли", button = "Понятно"
                                    ) { _, _ -> }
                                }
                            }
                        }
                        14 -> {
                            holder.initialize("Только ближайшие")
                            holder.linetop.constrainTopToTopOf(innerContent)
                            holder.linetop.visibility = View.INVISIBLE
                        }
                    }
                }

                is FilterButtonViewHolder -> {
                    holder.initialize("Вперёд!")
                }
            }

        }

    }

}

fun FilterView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally(0)
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

}




