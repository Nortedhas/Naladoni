package com.ageone.naladoni.Modules.ChangeName

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.EditText.limitLength
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.External.Utils.Validation.isValidPhone
import com.ageone.naladoni.External.Utils.Validation.isValidText
import com.ageone.naladoni.Modules.Auth.AuthViewModel
import com.ageone.naladoni.Modules.ChangeSMS.ChangeSMSViewModel
import com.ageone.naladoni.R
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ChangeNameView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ChangeNameViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "Имя и фамилия"

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

        private val CangeNameInputType = 0
        private val CangeNameButtonType = 1

        override fun getItemCount(): Int = 4

        override fun getItemViewType(position: Int): Int = when (position) {

            0 -> CangeNameInputType
            1 -> CangeNameButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                CangeNameInputType -> {
                    InputViewHolder(layout)
                }
                CangeNameButtonType -> {
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

                    holder.initialize("Введите ваше имя и фамилию:", InputEditTextType.TEXT)
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.inputName = text.toString()
                    }

                    innerContent.dismissFocus(holder.textInputL.editText)

                }

                is ButtonViewHolder -> {
                    holder.initialize("Изменить")
                    holder.button.setOnClickListener {
                        if (!viewModel.model.inputName.isValidText()) {
                            alertManager.single("Неверное имя", "Введено неверное имя", null) { _, _ ->
                            }
                        }else {

                            rootModule.emitEvent?.invoke(ChangeNameViewModel.EventType.OnlouderChangeName.toString())
                        }

                    }
                }
            }
        }

    }
}

fun ChangeNameView.renderUIO() {

    bodyTable
        .constrainTopToTopOf(innerContent, 130)

    renderBodyTable()
}

