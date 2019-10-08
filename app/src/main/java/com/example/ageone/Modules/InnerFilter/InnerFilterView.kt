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



    val iconsAll = arrayOf(
        arrayOf(
            R.drawable.pic_bistro,
            R.drawable.pic_alcohol,
            R.drawable.pic_dinner,
            R.drawable.pic_fast_food,
            R.drawable.pic_cup_and_plate,
            R.drawable.pic_pizza,
            R.drawable.pic_lunch,
            R.drawable.pic_food
        ),
        arrayOf(
            R.drawable.pic_car_1,
            R.drawable.pic_car_2,
            R.drawable.pic_car_3,
            R.drawable.pic_car_4,
            R.drawable.pic_car_5
        ),
        arrayOf(
            R.drawable.pic_beauty_1,
            R.drawable.pic_beauty_2,
            R.drawable.pic_beauty_3,
            R.drawable.pic_beauty_4,
            R.drawable.pic_beauty_5,
            R.drawable.pic_beauty_6,
            R.drawable.pic_beauty_7,
            R.drawable.pic_beauty_8,
            R.drawable.pic_beauty_9,
            R.drawable.pic_beauty_10
        ),
        arrayOf(
            R.drawable.pic_games_1,
            R.drawable.pic_games_2,
            R.drawable.pic_games_3,
            R.drawable.pic_games_4
        ),
        arrayOf(),//fitness
        arrayOf(
            R.drawable.pic_health_1,
            R.drawable.pic_health_2,
            R.drawable.pic_health_3,
            R.drawable.pic_health_4
        ),
        arrayOf(),// for children
        arrayOf(),// service
        arrayOf(),// products
        arrayOf(
            R.drawable.pic_education_1,
            R.drawable.pic_education_2,
            R.drawable.pic_education_3
        ),
        arrayOf(
            R.drawable.pic_tourism_1,
            R.drawable.pic_tourism_2,
            R.drawable.pic_tourism_3
        ),
        arrayOf(),//for animals
        arrayOf()//18+
    )

    val namesAll = arrayOf(
        arrayOf(
            "Кафе",
            "Бары",
            "Рестораны",
            "Фастфуд",
            "Кофейни",
            "Доставка еды",
            "Програм. питания",
            "Другое"
        ),
        arrayOf(
            "Автомойки",
            "СТО",
            "Шиномонтаж",
            "Детейлинг",
            "Мастерские"
        ),
        arrayOf(
            "Косметология",
            "Барбершопы",
            "Парикмахерская",
            "Тату-студии",
            "Маникюр/Педикюр",
            "Уход за волосами",
            "СПА/Массаж",
            "Коррекция фигуры",
            "Эпиляция",
            "Другое"
        ),
        arrayOf(
            "Экстремальные",
            "Для семьи",
            "С компанией",
            "Другое"
        ),

        arrayOf(),//fitness

        arrayOf(
            "Стоматология",
            "Диагностика",
            "Корр. зрения",
            "Другое"

        ),
        arrayOf(),// for children
        arrayOf(),// service
        arrayOf(),// products
        arrayOf(
            "Ин. языки",
            "Проф. образование",
            "Другое"
        ),
        arrayOf(
            "Отели",
            "Туры",
            "Другое"
        ),
        arrayOf(),//for animals
        arrayOf()//18+
    )

    var icons = arrayOf<Int>()
    var names = arrayOf<String>()


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

    fun bindUI() {}

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val InnerFilterbType = 0

        override fun getItemCount() = names.size//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            in names.indices -> InnerFilterbType
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
            when (holder) {
                is FilterFilterIconsViewHolder -> {
                    holder.initialize(names[position], icons[position])
                }

            }

        }

    }


    override fun reload() {
        icons = iconsAll[viewModel.model.currentFilterIndex]
        names = namesAll[viewModel.model.currentFilterIndex]

        super.reload()
    }
}

fun InnerFilterView.renderUIO() {
    renderBodyTable()
}


