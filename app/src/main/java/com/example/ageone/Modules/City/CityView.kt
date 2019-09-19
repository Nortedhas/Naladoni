package com.example.ageone.Modules.City

import android.graphics.Color
import android.os.CountDownTimer
import android.view.ViewGroup
import android.widget.EditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextInputLayout.InputEditTextType
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.list
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.Auth.RegistrationViewModel
import com.example.ageone.Modules.City.rows.CityViewViewHolder
import com.example.ageone.Modules.City.rows.initialize
import com.example.ageone.Modules.Loading.StartViewModel
import com.example.ageone.Modules.SMS.rows.RegistrationSMSTextViewHolder
import com.example.ageone.Modules.SMS.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.InputViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class CityView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {
    override fun unBind() {}

    val viewModel = CityViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "Город подарков"
        renderToolbar()
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER
        renderUIO()
    }

//    fun bindUI() {
////        /*compositeDisposable.add(
////            RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
////                bodyTable.adapter?.notifyDataSetChanged()
////            }
////        )*/
////    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private  val SelectalertManager =0
        private  val SelectCityTextType = 1
        private  val SelectCityButtonType =2
        override fun getItemCount() = 3//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {

            0 -> SelectalertManager
            1 -> SelectCityTextType
            2 -> SelectCityButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            val layout = ConstraintLayout(parent.context)
            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                SelectalertManager -> {
                    InputViewHolder(layout)
                }
                SelectCityTextType ->{
                    CityViewViewHolder(layout)
                }
                SelectCityButtonType ->{
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
                is InputViewHolder -> {

                    holder.initialize("Мой город:", InputEditTextType.NUMERIC)
                    holder.textInputL.editText?.doOnTextChanged { text, start, count, after ->
                        viewModel.model.code = text.toString()

                    }
                    holder.textInputL.setOnClickListener{
                        val array = arrayOf("dsf","sfdas")
                        alertManager.list( "sdfjkbhas", arrayOf("dsf","sfdas")  ) {_, int ->
                            when (int)
                            {
                              0 ->{
                                  holder.textInputL.editText?.setText(array[0]) }
                               1 -> {
                                   holder.textInputL.editText?.setText(array[1]) }
                               }

                            }

                        }

                    }

                is  ButtonViewHolder ->{
                    holder.initialize("Подтверждаю")
                   holder.button.setOnClickListener{
//                       alertManager.single(message = "мы определили ваш город как ",
//                           title = "Ваш город подарков",button = "Отлично"){_,_->}
                       emitEvent?.invoke(CityViewModel.EventType.onSityPresed.toString())

                   }
                }
               is CityViewViewHolder ->{
                   holder.initialize("Система ватоматически определает ваш город" )
               }

            }

        }

    }

}

fun CityView.renderUIO() {

    renderBodyTable()
}


