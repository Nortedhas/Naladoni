package com.ageone.naladoni.Modules.ChangePhone

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.R
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ChangePhoneView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangePhoneViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "Номер телефона"

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

                    InputViewHolder(layout)
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
            when (holder) {
                is InputViewHolder -> {

                    holder.initialize("Введите ваш номер телефона:", InputEditTextType.PHONE)
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.inputPhone = text.toString()
                    }

                    innerContent.dismissFocus(holder.textInputL.editText)

                }

                is ButtonViewHolder -> {
                    holder.initialize("Изменить")
                    holder.button.setOnClickListener {
                        rootModule.emitEvent?.invoke(ChangePhoneViewModel.EventType.OnlouderChangePhone.toString())

                    }
                }
            }
        }

    }
}

fun ChangePhoneView.renderUIO() {

    bodyTable
        .constrainTopToTopOf(innerContent, 130)

    renderBodyTable()
}