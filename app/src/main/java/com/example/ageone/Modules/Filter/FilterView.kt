package com.example.ageone.Modules.Filter

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.Modules.Filter.rows.FilterButtonViewHolder
import com.example.ageone.Modules.Filter.rows.FilterSwitchViewHolder
import com.example.ageone.Modules.Filter.rows.initialize
import com.example.ageone.R
import yummypets.com.stevia.*

class FilterView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = FilterViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_filter)//TODO: set background

        toolbar.title = "Покажи мне"

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

        private val FilterType = 0
        private val ButtonType = 1


        override fun getItemCount() = 5//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0, 1 -> FilterType
            2 -> ButtonType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                FilterType -> {
                    FilterSwitchViewHolder(layout)
                }
                ButtonType -> {
                    FilterButtonViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is FilterSwitchViewHolder -> {
                    when (position) {
                        0 -> {
                            holder.initialize("Только популярные")
                            holder.switch.setOnClickListener {
                                if (holder.switch.isChecked) {
                                    alertManager.single(
                                        message = "В вашем городе нет подарков в данной категории, попробуйте поискать в другой ",
                                        title = "Мы ничего не нашли", button = "Понятно"
                                    ) { _, _ ->
                                        val toast = Toast.makeText(
                                            currentActivity?.applicationContext,
                                            "МММ жаль", Toast.LENGTH_SHORT
                                        )
                                        toast.show()
                                    }
                                }
                            }
                        }
                        1 -> {
                            holder.initialize("Только ближайшие")
                            holder.linetop.visibility = View.INVISIBLE
                        }

                    }


                }
                is FilterButtonViewHolder -> {
                    holder.initialize("Вперёд!")
                }

            }

        }
    }
}

fun FilterView.renderUIO() {

  //  renderBodyTable()
    innerContent.subviews(
        bodyTable
    )
    bodyTable
        .fillHorizontally()
        .constrainBottomToBottomOf(innerContent)

}


