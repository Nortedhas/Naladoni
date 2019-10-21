package com.ageone.naladoni.Modules.Auth

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.intent
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.External.Utils.Validation.isValidPhone
import com.ageone.naladoni.External.Utils.Validation.toCorrectPhone
import com.ageone.naladoni.Modules.Auth.rows.InputViewHolderC
import com.ageone.naladoni.Modules.Auth.rows.AuthTextViewHolder
import com.ageone.naladoni.Modules.Auth.rows.initialize
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*
import java.util.regex.Pattern

class AuthView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = AuthViewModel()


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

        private val AuthInputType = 0
        private val AuthInputCType = 1
        private val AuthButtonType = 2
        private val AuthTextType = 3

        override fun getItemCount(): Int = 5

        override fun getItemViewType(position: Int):Int = when(position) {

            0 -> AuthInputType
            1 -> AuthInputCType
            2 -> AuthButtonType
            3 -> AuthTextType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when(viewType) {
                AuthInputType -> {
                    InputViewHolder(layout)
                }
                AuthInputCType -> {
                    InputViewHolderC(layout)
                }
                AuthButtonType -> {
                    ButtonViewHolder(layout)
                }
                AuthTextType -> {
                 AuthTextViewHolder(layout)
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
                        if (!viewModel.model.inputPhone.isValidPhone()) {
                            alertManager.single("Неверный номер", "Введен неверный номер", null) {_,_ ->
                            }
                        }else if (viewModel.model.inputName.length < 3){
                            alertManager.single("Неверное имя", "Имя введено неверно", null) {_,_ ->
                            }
                        }else {
                            var phone = viewModel.model.inputPhone.toCorrectPhone()
                            api.request(mapOf(
                                "router" to "phoneAuth",
                                "phone" to phone)){
                                rootModule.emitEvent?.invoke(AuthViewModel.EventType.OnAuthPressed.name)
                            }

                        }

                    }
                }

                is AuthTextViewHolder -> {
                    holder.initialize()

                    holder.textView.setOnClickListener {
                        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ru/chrome/privacy/eula_text.html"))
                        currentActivity?.startActivity(intent)
                    }
                }
            }
        }

    }
}

fun AuthView.renderUIO() {

    bodyTable
        .constrainTopToTopOf(innerContent, 130)

    renderBodyTable()
}