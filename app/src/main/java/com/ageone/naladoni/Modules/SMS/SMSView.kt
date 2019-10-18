package com.ageone.naladoni.Modules.SMS

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.router
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.EditText.limitLength
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.HTTP.update
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.External.Utils.Validation.isValidSMSCod
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.External.Utils.Validation.isValidPhone
import com.ageone.naladoni.External.Utils.Validation.isValidSMSCod
import com.ageone.naladoni.Modules.Auth.AuthViewModel
import com.ageone.naladoni.Modules.City.CityViewModel
import com.ageone.naladoni.Modules.SMS.rows.SMSTextViewHolder
import com.ageone.naladoni.Modules.SMS.rows.initialize
import com.ageone.naladoni.Modules.SMSViewModel
import com.ageone.naladoni.R
import com.ageone.naladoni.SCAG.DataBase
import com.ageone.naladoni.SCAG.userData
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import timber.log.Timber
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent
import java.util.*

class SMSView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    var timerSMS: Timer? = null

    val viewModel = SMSViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
        timerSMS = Timer()

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Смс-код"
        renderToolbar()

        bodyTable.adapter = viewAdapter

        renderUIO()

        onDeInit = {
            timerSMS?.cancel()
        }
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val SMSInputType = 0
        private val SMSTextType = 1
        private val SMSButtonType = 2

        override fun getItemCount(): Int = 3

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> SMSInputType
            1 -> SMSTextType
            2 -> SMSButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                SMSInputType -> {
                    InputViewHolder(layout)
                }
                SMSTextType -> {
                    SMSTextViewHolder(layout, timerSMS)
                }
                SMSButtonType -> {
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
                    holder.initialize("Введите смс-код:", InputEditTextType.NUMERIC)
                    holder.textInputL.editText?.doOnTextChanged { text, _, _, _ ->
                        viewModel.model.code = text.toString()
                    }

                    holder.textInputL.editText?.limitLength(4)

                    innerContent.dismissFocus(holder.textInputL.editText)
                }
                is SMSTextViewHolder -> {
                    holder.initialize {
                        router.onBackPressed()
                    }

                }
                is ButtonViewHolder -> {
                    holder.initialize("Подтверждаю")

                    holder.button.setOnClickListener {
                        if (!viewModel.model.code.isValidSMSCod()) {
                            alertManager.single("Неверный код", "Введен неверный код", null) { _, _ ->
                            }
                        } else {
                            timerSMS?.cancel()

                            api.request(
                                mapOf(
                                    "router" to "codeCheck",
                                    "phone" to viewModel.model.inputPhone,
                                    "code" to viewModel.model.code
                                ), isErrorShown = true
                            ) { json ->
                                Timber.i("JSON answer $json")

                                api.parser.userData(json)

                                DataBase.User.update(
                                    user.hashId,
                                    mapOf(
//                                    "phone" to viewModel.model.inputPhone,
                                        "name" to viewModel.model.inputName
                                    )
                                )

                                utils.variable.token = json.optString("Token")

                                user.data.name = viewModel.model.inputName
                                user.data.phone = viewModel.model.inputPhone
                                user.isAuthorized = true

                                rootModule.emitEvent?.invoke(SMSViewModel.EventType.OnAcceptCode.name)
                            }
                        }

                    }
                }
            }

        }
    }
}

fun SMSView.renderUIO() {

    renderBodyTable()
}
