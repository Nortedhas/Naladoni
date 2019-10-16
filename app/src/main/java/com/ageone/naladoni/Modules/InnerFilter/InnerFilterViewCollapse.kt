package com.ageone.naladoni.Modules.InnerFilter

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.Module.BaseModuleCollapse
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Filter.rows.FilterFilterIconsViewHolder
import com.ageone.naladoni.Modules.Filter.rows.initialize
import com.ageone.naladoni.R
import yummypets.com.stevia.*

class InnerFilterViewCollapse(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModuleCollapse(initModuleUI) {

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

    val all_icons = arrayOf(
        arrayOf(
            R.drawable.pic_food_1,
            R.drawable.pic_food_2,
            R.drawable.pic_food_3,
            R.drawable.pic_food_4,
            R.drawable.pic_food_5,
            R.drawable.pic_food_6,
            R.drawable.pic_food_7,
            R.drawable.pic_food_8
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

    val all_names = arrayOf(
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

        setBackgroundResource(R.drawable.back_filter)

        toolbar.title = "Hello"

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.layoutManager = layoutManager

        renderUIO()
        bindUI()
    }

    fun bindUI() {

    }

    inner class Factory(val rootModule: BaseModuleCollapse) : BaseAdapter<BaseViewHolder>() {

        private val InnerFilterFilterIconsType = 0

        override fun getItemCount() = names.size//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            in names.indices -> InnerFilterFilterIconsType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                InnerFilterFilterIconsType -> {
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
                    holder.initialize(names[position], icons[position], false)
                    holder.card.setOnClickListener {
                        rxData.selectedFilter = viewModel.model.currentFilterIndex
                        rootModule.emitEvent?.invoke(InnerFilterViewModel.EventType.OnInnerFilterPressed.name)
                    }
                }

            }

        }

    }


    override fun reload() {
//        toolbar.setTitleToolbar(viewModel.model.filterName)

        icons = all_icons[viewModel.model.currentFilterIndex]
        names = all_names[viewModel.model.currentFilterIndex]

        super.reload()
    }
}

fun InnerFilterViewCollapse.renderUIO() {
    renderBodyTable()
}


