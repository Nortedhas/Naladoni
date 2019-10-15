package com.ageone.naladoni.Modules.City

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Extensions.Activity.hideKeyboard
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.list
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.Modules.City.rows.CityViewHolder
import com.ageone.naladoni.Modules.City.rows.initialize
import com.ageone.naladoni.R
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.EditTextViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent

class CityView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = CityViewModel()
    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        //        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "Город подарков"
        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

           inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

            private val SelectalertManager = 0
            private val SelectCityTextType = 1
            private val SelectCityButtonType = 2
            override fun getItemCount() = 3

            override fun getItemViewType(position: Int): Int = when (position) {
                0 -> SelectalertManager
                1 -> SelectCityTextType
                2 -> SelectCityButtonType
                else -> -1
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
                val layout = ConstraintLayout(parent.context)
                layout
                    .width(matchParent)
                    .height(wrapContent)

                val holder = when (viewType) {
                    SelectalertManager -> {
                        EditTextViewHolder(layout)
                    }
                    SelectCityTextType -> {
                        CityViewHolder(layout)
                    }
                    SelectCityButtonType -> {
                        ButtonViewHolder(layout)
                    }
                    else -> {
                        BaseViewHolder(layout)
                    }
                }

                return holder
            }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            val city = arrayOf("Екатеринбург", "Москва")
            when (holder) {
                is EditTextViewHolder -> {
                    alertManager.single(
                        message = "мы определили ваш город как ${city[0]}",
                        title = "Ваш город подарков", button = "Отлично"
                    ) { _, _ ->
                        holder.editText.setText(
                            city[0])
                    }


                    holder.editText.setOnClickListener{
                        currentActivity?.hideKeyboard()
                        alertManager.list( "Выберите город", city) {_, int ->
                            when (int) {
                                0 -> {
                                    holder.editText.setText(
                                        city[0])
                                }
                                1 -> {
                                    holder.editText.setText(
                                        city[1])
                                }
                            }

                        }

                    }

                }

                is ButtonViewHolder -> {
                    holder.initialize("Подтверждаю")
                    holder.button.setOnClickListener {
                        emitEvent?.invoke(CityViewModel.EventType.onSityPresed.toString())
                    }
                }
                is CityViewHolder -> {
                    holder.initialize("Система автоматически определает ваш город")
                }

            }

        }

    }
}

fun CityView.renderUIO() {

    renderBodyTable()
}


