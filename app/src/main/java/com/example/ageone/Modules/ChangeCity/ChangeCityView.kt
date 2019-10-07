package com.example.ageone.Modules.ChangeCity

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.Extensions.Activity.hideKeyboard
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.list
import com.example.ageone.R
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.EditTextViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ChangeCityView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangeCityViewModel()


    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

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

        private val RegistrationInputType = 0
        private val RegistrationButtonType = 1

        override fun getItemCount(): Int = 4

        override fun getItemViewType(position: Int): Int = when (position) {

            0 -> RegistrationInputType
            1 -> RegistrationButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RegistrationInputType -> {
                    EditTextViewHolder(layout)
                }
                RegistrationButtonType -> {
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
                    holder.editText.setOnClickListener{
                        currentActivity?.hideKeyboard()
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

                is ButtonViewHolder -> {
                    holder.initialize("Изменить")
                    holder.button.setOnClickListener {

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
