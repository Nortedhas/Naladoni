package com.ageone.naladoni.Modules.Auth

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Auth.rows.InputViewHolderC
import com.ageone.naladoni.Modules.Auth.rows.RegistrationTextHolder
import com.ageone.naladoni.Modules.Auth.rows.initialize
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class AuthRegistrationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = AuthRegistrationViewModel()


    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Авторизация"
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER



        renderUIO()

    }

    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val RegistrationInputType = 0
        private val RegistrationInputTypeC = 1
        private val RegistrationButtonType = 2
      private val RegistrationTextType = 3

        override fun getItemCount(): Int = 5

        override fun getItemViewType(position: Int):Int = when(position) {

            0 -> RegistrationInputType
            1 -> RegistrationInputTypeC
            2 -> RegistrationButtonType
            3 -> RegistrationTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when(viewType) {
                RegistrationInputType -> {
                    InputViewHolder(layout)
                }
                RegistrationInputTypeC -> {
                    InputViewHolderC(layout)
                }
                RegistrationButtonType -> {
                    ButtonViewHolder(layout)
                }
                RegistrationTextType -> {
                 RegistrationTextHolder(layout)
                }
                else ->
                    BaseViewHolder(layout)
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            when(holder) {
                is InputViewHolder -> {

                            holder.initialize("Введите ваше имя и фамилию:", InputEditTextType.TEXT)
                            holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                                viewModel.model.inputName = text.toString()
                            }

                    innerContent.dismissFocus(holder.textInputL.editText)

                    }
                is InputViewHolderC -> {
                    holder.initialize("Введите ваш номер телефона:", InputEditTextType.PHONE)
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.inputPhone = text.toString()
                    }

                    innerContent.dismissFocus(holder.textInputL.editText)
                }

                is ButtonViewHolder -> {
                    holder.initialize("Войти в приложение")
                    holder.button.setOnClickListener {
//                        if (!viewModel.model.inputPhone.isValidPhone()) {
//                            alertManager.single("Неверный номер", "Введен неверный номер", null) {_,_ ->
//                            }
//                        }  else if (viewModel.model.inputName.isBlank()){
//                            alertManager.single("Неверное имя", "Имя не введено", null) {_,_ ->
//                            }
//                        } else {
//                            api.request(mapOf(
//                                "router" to "phoneAuth",
//                                "phone" to viewModel.model.inputPhone)){
                               rootModule.emitEvent?.invoke(AuthRegistrationViewModel.EventType.OnRegistrationPressed.toString())
//                            }
//
//                        }

                    }
                }

                is RegistrationTextHolder -> {
                    holder.initialize()
                }
            }
        }

    }
}

fun AuthRegistrationView.renderUIO() {
      bodyTable
          .constrainTopToTopOf(innerContent, 130)
    renderBodyTable()
}