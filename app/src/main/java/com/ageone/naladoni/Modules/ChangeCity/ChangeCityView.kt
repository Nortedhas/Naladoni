package com.ageone.naladoni.Modules.ChangeCity

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.EditText.disableKeyboard
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Extensions.Activity.hideKeyboard
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.External.HTTP.fetch
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.blockUI
import com.ageone.naladoni.External.Libraries.Alert.list
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.Models.User.City
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.Network.HTTP.getCity
import com.ageone.naladoni.R
import com.ageone.naladoni.SCAG.DataBase
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.EditTextViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*
import android.view.View.OnFocusChangeListener
import com.ageone.naladoni.External.Libraries.Alert.list
import com.ageone.naladoni.External.RxBus.RxBus
import com.ageone.naladoni.External.RxBus.RxEvent


class ChangeCityView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangeCityViewModel()


    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Город"

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


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

        private val CangeCityInputType = 0
        private val CangeCityButtonType = 1

        override fun getItemCount(): Int = 4

        override fun getItemViewType(position: Int): Int = when (position) {

            0 -> CangeCityInputType
            1 -> CangeCityButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {

                CangeCityInputType -> {

                    EditTextViewHolder(layout)
                }
                CangeCityButtonType -> {

                    ButtonViewHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EditTextViewHolder -> {
                    holder.editText.setText(user.info.city?.name)

                    DataBase.City.fetch("") { json ->
                        api.parser.parseAnyObject(json, DataBase.City)

                        val cities = utils.realm.city.getAllObjects().map { city ->
                            City(city.name, city.hashId)
                        }.toTypedArray()
                        val citiesNames = utils.realm.city.getAllObjects().map { city -> city.name }
                            .toTypedArray()

                        holder.editText.setOnClickListener {
                            currentActivity?.hideKeyboard()
                            alertManager.list("Выберите город", citiesNames) { _, index ->
                                holder.editText.setText(citiesNames[index])
                                user.info.city = cities[index]
                            }
                        }
                    }

                    holder.editText.disableKeyboard()
                    innerContent.dismissFocus(holder.editText)

                }

                is ButtonViewHolder -> {

                    holder.initialize("Изменить")
                    holder.button.setOnClickListener {
                        RxBus.publish(RxEvent.EventChangeCity())
                        emitEvent?.invoke(ChangeCityViewModel.EventType.OnlouderChangeCity.name)

                    }
                }
            }
        }

    }
}

fun ChangeCityView.renderUIO() {

    bodyTable
        .constrainTopToTopOf(innerContent, 130)

    renderBodyTable()
}
