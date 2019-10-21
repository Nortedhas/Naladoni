package com.ageone.naladoni.Modules.ChangeCity

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.EditText.disableKeyboard
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.EditTextViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*
import android.view.View.OnFocusChangeListener
import com.ageone.naladoni.External.Libraries.Alert.list


class ChangeCityView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangeCityViewModel()


    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(com.ageone.naladoni.R.drawable.base_background)

        toolbar.title = "Город"

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

            val city = arrayOf("Екатеринбург", "Москва")
            when (holder) {
                is EditTextViewHolder -> {
                    holder.initialize("","Выберите город")
                    holder.editText.setOnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            alertManager.list( "Выберите город", city) { _, int ->
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
                    holder.editText.setOnClickListener{
                      //  currentActivity?.hideKeyboard()
                        alertManager.list( "Выберите город", city) { _, int ->
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

                    holder.editText?.disableKeyboard()

                    innerContent.dismissFocus(holder.editText)

                }

                is ButtonViewHolder -> {

                    holder.initialize("Изменить")
                    holder.button.setOnClickListener {
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
