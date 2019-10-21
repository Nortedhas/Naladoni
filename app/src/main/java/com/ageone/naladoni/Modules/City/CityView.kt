package com.ageone.naladoni.Modules.City

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.EditText.limitLength
import com.ageone.naladoni.External.Base.Map.distance
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
import com.ageone.naladoni.Modules.City.rows.CityTextViewHolder
import com.ageone.naladoni.Modules.City.rows.initialize
import com.ageone.naladoni.Network.HTTP.getCity
import com.ageone.naladoni.R
import com.ageone.naladoni.SCAG.DataBase
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.EditTextViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import timber.log.Timber
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

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Город подарков"
        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        alertManager.blockUI(true)
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

            private val CityEditTextType = 0
            private val CityTextType = 1
            private val CityButtonType = 2
            override fun getItemCount() = 3

            override fun getItemViewType(position: Int): Int = when (position) {
                0 -> CityEditTextType
                1 -> CityTextType
                2 -> CityButtonType
                else -> -1
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
                val layout = ConstraintLayout(parent.context)
                layout
                    .width(matchParent)
                    .height(wrapContent)

                val holder = when (viewType) {
                    CityEditTextType -> {
                        EditTextViewHolder(layout)
                    }
                    CityTextType -> {
                        CityTextViewHolder(layout)
                    }
                    CityButtonType -> {
                        ButtonViewHolder(layout)
                    }
                    else -> {
                        BaseViewHolder(layout)
                    }
                }

                return holder
            }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is EditTextViewHolder -> {

                    DataBase.City.fetch(""){ json ->

                        api.parser.parseAnyObject(json, DataBase.City)

                        val cities = utils.realm.city.getAllObjects().map {
                                city -> City(city.name, city.hashId)
                        }.toTypedArray()
                        val citiesNames = utils.realm.city.getAllObjects().map { city -> city.name }.toTypedArray()

                        api.getCity(startLocation.latitude, startLocation.longitude) { nearestCity ->
                            user.info.city = nearestCity
                            alertManager.blockUI(false)
                            alertManager.single(
                                message = "Мы определили ваш город как ${nearestCity.name}",
                                title = "Ваш город подарков", button = "Отлично"
                            ) { _, _ ->

                                holder.editText.setText("${nearestCity.name}")
                            }
                        }
                        holder.editText.setOnFocusChangeListener { _, hasFocus ->
                            if (hasFocus) {
                                currentActivity?.hideKeyboard()
                                alertManager.list("Выберите город", citiesNames) { _, index ->
                                    holder.editText.setText(citiesNames[index])
                                    user.info.city = cities[index]
                                }
                            }
                        }

                        holder.editText.setOnClickListener{
                            currentActivity?.hideKeyboard()
                            alertManager.list( "Выберите город", citiesNames) { _, index ->
                                holder.editText.setText(citiesNames[index])
                                user.info.city = cities[index]
                            }
                        }

                    }

                    holder.editText.disableKeyboard()

                    innerContent.dismissFocus(holder.editText)

                }

                is ButtonViewHolder -> {

                    holder.initialize("Подтверждаю")
                    holder.button.setOnClickListener {

                        emitEvent?.invoke(CityViewModel.EventType.OnAcceptCode.name)
                    }
                }
                is CityTextViewHolder -> {

                    holder.initialize("Система автоматически определает ваш город")
                }

            }

        }

    }
}

fun CityView.renderUIO() {

    renderBodyTable()
}


fun getNearestCity(): City {
    val cities = utils.realm.city.getAllObjects()
    var nearCity = City()
    var nearestDistance = -1.0
    cities.forEachIndexed { index, city ->
        city.location?.let { location ->
            val curDistance = distance(
                startLocation.latitude, startLocation.longitude,
                location.latitude, location.longitude, "K")
            if (nearestDistance < 0 || nearestDistance > curDistance) {
                nearestDistance = curDistance
                nearCity = City(city.name, city.hashId)
            }
        }
    }
    return nearCity
}

