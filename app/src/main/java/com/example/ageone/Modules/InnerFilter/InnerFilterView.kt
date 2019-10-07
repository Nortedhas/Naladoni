package com.example.ageone.Modules.InnerFilter

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Filter.rows.FilterFilterIconsViewHolder
import com.example.ageone.Modules.Filter.rows.initialize
import com.example.ageone.R
import yummypets.com.stevia.*

class InnerFilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = InnerFilterViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {

                    else -> 1
                }
            }
        }
        layoutManager
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_filter)//TODO: set background

        toolbar.title = "Поесть"

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.layoutManager = layoutManager


        renderUIO()
        bindUI()
    }

    fun bindUI() {
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val list = listOf(
            "Кафе", "Бары", "Рестораны", "Фастфуд", "Кофейни", "Доставка еды", "Програм. питания",
            "Другое"
        )
        private val resourceImages = arrayOf(
            R.drawable.pic_bistro,
            R.drawable.pic_alcohol,
            R.drawable.pic_dinner,
            R.drawable.pic_fast_food,
            R.drawable.pic_cup_and_plate,
            R.drawable.pic_pizza,
            R.drawable.pic_lunch,
            R.drawable.pic_food
        )
        private val InnerFilterbType = 0

        override fun getItemCount() = 8//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
           in 0..8 -> InnerFilterbType
            else -> -1
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                InnerFilterbType -> {
                    FilterFilterIconsViewHolder(layout)
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
                }

            }

        }

    }

}

fun InnerFilterView.renderUIO() {

    renderBodyTable()
}


