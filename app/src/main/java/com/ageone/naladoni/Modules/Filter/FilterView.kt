package com.ageone.naladoni.Modules.Filter

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.External.RxBus.RxBus
import com.ageone.naladoni.External.RxBus.RxEvent
import com.ageone.naladoni.Modules.Filter.rows.*
import com.ageone.naladoni.R
import yummypets.com.stevia.*

class FilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FilterViewModel()

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
        compositeDisposable.add(
            RxBus.listen(RxEvent.EventChangeFilterIndex::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {
        private val names = listOf(
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

        private val icons = arrayOf(
            R.drawable.pic_categories_1,
            R.drawable.pic_categories_2,
            R.drawable.pic_categories_3,
            R.drawable.pic_categories_4,
            R.drawable.pic_categories_5,
            R.drawable.pic_categories_6,
            R.drawable.pic_categories_7,
            R.drawable.pic_categories_8,
            R.drawable.pic_categories_9,
            R.drawable.pic_categories_10,
            R.drawable.pic_categories_11,
            R.drawable.pic_categories_12,
            R.drawable.pic_categories_13
        )

        private val FilterFilterIconsType = 0
        private val FilterSwitchType = 1
        private val FilterButtonType = 2

        override fun getItemCount() = 16//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            in 0..12 -> FilterFilterIconsType
            13, 14 -> FilterSwitchType
            15 -> FilterButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FilterFilterIconsType -> {
                    FilterFilterIconsViewHolder(layout)
                }
                FilterSwitchType -> {
                    FilterSwitchViewHolder(layout)
                }
                FilterButtonType -> {
                    FilterButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when (holder) {

                is FilterFilterIconsViewHolder -> {
                    Timber.i("Position: $position - selected: ${rxData.selectedFilter} - ${position == rxData.selectedFilter}")
                    holder.initialize(names[position], icons[position], position == rxData.selectedFilter)

                    holder.card.setOnClickListener {
                        if (position in setOf(0, 1, 2, 3, 5, 9, 10)) {
                            viewModel.model.filterName = names[position]
                            viewModel.model.currentFilterIndex = position
                            rootModule.emitEvent?.invoke(FilterViewModel.EventType.OnInnerFilterPressed.name)
                        } else {
                            rxData.selectedFilter = position
                            notifyDataSetChanged()
                        }
                    }
                }

                is FilterSwitchViewHolder -> {
                    when (position) {
                        13 -> {
                            holder.initialize("Только популярные",false)
                            holder.switch.setOnClickListener {
                            }
                        }
                        14 -> {
                            holder.initialize("Только ближайшие",true)
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




