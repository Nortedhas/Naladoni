package com.ageone.naladoni.Modules.ChangeSMS

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.ageone.naladoni.Application.router
import com.ageone.naladoni.External.Base.ConstraintLayout.dismissFocus
import com.ageone.naladoni.External.Base.EditText.limitLength
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextInputLayout.InputEditTextType
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.ChangeSMS.rows.ChangeSMSTextViewHolder
import com.ageone.naladoni.Modules.ChangeSMS.rows.initialize
import com.ageone.naladoni.R
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.InputViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.height
import yummypets.com.stevia.matchParent
import yummypets.com.stevia.width
import yummypets.com.stevia.wrapContent
import java.util.*

class ChangeSMSView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    var timerSMS: Timer? = null

    val viewModel = ChangeSMSViewModel()

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

        private val ChangeSMSInputType = 0
        private val ChangeSMSTextType = 1
        private val ChangeSMSButtonType = 2

        override fun getItemCount(): Int = 3

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ChangeSMSInputType
            1 -> ChangeSMSTextType
            2 -> ChangeSMSButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ChangeSMSInputType -> {
                    InputViewHolder(layout)
                }
                ChangeSMSTextType -> {
                    ChangeSMSTextViewHolder(layout, timerSMS)
                }
                ChangeSMSButtonType -> {
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

                    holder.textInputL.editText?.limitLength(4)

                    innerContent.dismissFocus(holder.textInputL.editText)
                }
                is ChangeSMSTextViewHolder -> {
                    holder.initialize {
                        router.onBackPressed()
                    }
                }
                is ButtonViewHolder -> {
                    holder.initialize("Подтверждаю")
                    holder.button.setOnClickListener {

                        timerSMS?.cancel()
                        rootModule.emitEvent?.invoke(ChangeSMSViewModel.EventType.OnlouderChangeSMS.toString())

                    }
                }
            }

        }
    }
}

fun ChangeSMSView.renderUIO() {

    renderBodyTable()
}
