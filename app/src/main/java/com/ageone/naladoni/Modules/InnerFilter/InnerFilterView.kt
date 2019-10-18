package com.ageone.naladoni.Modules.InnerFilter

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Filter.rows.FilterFilterIconsViewHolder
import com.ageone.naladoni.Modules.Filter.rows.initialize
import com.ageone.naladoni.R
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

    val all_icons = arrayOf(
        arrayOf(
            R.drawable.ic_category_0_0,//food
            R.drawable.ic_category_0_1,
            R.drawable.ic_category_0_2,
            R.drawable.ic_category_0_3,
            R.drawable.ic_category_0_4,
            R.drawable.ic_category_0_5,
            R.drawable.ic_category_0_6,
            R.drawable.ic_category_0
        ),
        arrayOf(
            R.drawable.ic_category_1_0,//Cars
            R.drawable.ic_category_1_1,
            R.drawable.ic_category_1_2,
            R.drawable.ic_category_1_3,
            R.drawable.ic_category_1_4
        ),
        arrayOf(
            R.drawable.ic_category_2_0,//Beuty
            R.drawable.ic_category_2_1,
            R.drawable.ic_category_2_2,
            R.drawable.ic_category_2_3,
            R.drawable.ic_category_2_4,
            R.drawable.ic_category_2_5,
            R.drawable.ic_category_2_6,
            R.drawable.ic_category_2_7,
            R.drawable.ic_category_2_8,
            R.drawable.ic_category_2_9
        ),
        arrayOf(
            R.drawable.ic_category_3_0,//Entertainment
            R.drawable.ic_category_3_1,
            R.drawable.ic_category_3_2,
            R.drawable.ic_category_3_3
        ),
        arrayOf(),//fitness
        arrayOf(
            R.drawable.ic_category_5_0,//Health
            R.drawable.ic_category_5_1,
            R.drawable.ic_category_5_2,
            R.drawable.ic_category_5_3
        ),
        arrayOf(),// for children
        arrayOf(),// service
        arrayOf(),// products
        arrayOf(
            R.drawable.ic_category_9_0,//Education
            R.drawable.ic_category_9_1,
            R.drawable.ic_category_9_2
        ),
        arrayOf(
            R.drawable.ic_category_10_0,//Tourism
            R.drawable.ic_category_10_1,
            R.drawable.ic_category_10_2
        ),
        arrayOf(),//For animals
        arrayOf()//18+
    )

    val all_names= arrayOf(
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

        toolbar.title = ""

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.layoutManager = layoutManager


        renderUIO()
        bindUI()
    }

    fun bindUI() {

    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

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
        toolbar.setTitleToolbar(viewModel.model.filterName)

        icons = all_icons[viewModel.model.currentFilterIndex]
        names = all_names[viewModel.model.currentFilterIndex]

        super.reload()
    }
}

fun InnerFilterView.renderUIO() {
    renderBodyTable()
}


