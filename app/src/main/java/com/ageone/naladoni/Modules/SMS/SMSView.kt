package com.ageone.naladoni.Modules.SMS

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.Application.router
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.City.CityViewModel
import com.ageone.naladoni.Modules.RegistrationSMSViewModel
import com.ageone.naladoni.Modules.SMS.rows.RegistrationSMSTextViewHolder
import com.ageone.naladoni.Modules.SMS.rows.initialize
import com.ageone.naladoni.R
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent
import java.util.*

class SMSView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {
    var timerSMS: Timer? = null

    val viewModel = RegistrationSMSViewModel()

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

        private val RegistrationSMSInputType = 0
        private val RegistrationSMSTextType = 1
        private val RegistrationSMSButtonType = 2

        override fun getItemCount(): Int = 3

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> RegistrationSMSInputType
            1 -> RegistrationSMSTextType
            2 -> RegistrationSMSButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                RegistrationSMSInputType -> {
                    InputViewHolder(layout)
                }
                RegistrationSMSTextType -> {
                    RegistrationSMSTextViewHolder(layout, timerSMS)
                }
                RegistrationSMSButtonType -> {
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
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.code = text.toString()
                    }
                }
                is RegistrationSMSTextViewHolder -> {
                    holder.initialize{
                        router.onBackPressed()
                    }
                }
                is ButtonViewHolder -> {
                    holder.initialize("Подтверждаю")
                    holder.button.setOnClickListener {
                        timerSMS?.cancel()
                        rootModule.emitEvent?.invoke(CityViewModel.EventType.onSityPresed.toString())

                    }
                }
            }

        }
    }

    fun SMSView.renderUIO() {
        renderBodyTable()
//        var timer = object : CountDownTimer(60000, 1000) {
//            override fun onTick(millisUntilFinished: Long) {
//                millisUntilFinished / 1000
//
//
//            }
//
//            override fun onFinish() {
//                emitEvent?.invoke(RegistrationSMSViewModel.EventType.onTimerPresed.toString())
//
//            }
//        }
//        timer.start()
    }
}