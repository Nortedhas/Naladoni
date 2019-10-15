package com.ageone.naladoni.Modules.MainStock

import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.MainStock.rows.MainStockDescribeViewHolder
import com.ageone.naladoni.Modules.MainStock.rows.MainStockQRCodViewHolder
import com.ageone.naladoni.Modules.MainStock.rows.MainStockTextViewHolder
import com.ageone.naladoni.Modules.MainStock.rows.initialize
import com.ageone.naladoni.R
import com.ageone.naladoni.UIComponents.ViewHolders.ButtonViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*


class MainStockView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MainStockViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        val bmp = BitmapFactory.decodeResource(
            resources,
            R.drawable.pic_main_stock_top
        )
        val bitmapDrawable = BitmapDrawable(resources, bmp)
        bitmapDrawable.setTileModeXY(
            Shader.TileMode.REPEAT,
            Shader.TileMode.REPEAT
        )
        background = bitmapDrawable
            //TODO: add white rectangle in bottom

        toolbar.title = ""
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

        private val MainStockType = 0
        private val MainStockTextType = 1
        private val MainStockButtomType = 2
        private val MainStockQrType = 3

        override fun getItemCount() = 8//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> MainStockType
            1, 2 -> MainStockTextType
            3 -> MainStockButtomType
            4 -> MainStockQrType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MainStockType -> {
                    MainStockDescribeViewHolder(layout)
                }
                MainStockTextType -> {
                    MainStockTextViewHolder(layout)
                }
                MainStockButtomType -> {
                    ButtonViewHolder(layout)
                }
                MainStockQrType -> {
                    MainStockQRCodViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is MainStockDescribeViewHolder -> {
                    holder.initialize(
                        "Время работы: ",
                        "пн-пт: 10:00 до 18:00. сб-вс: 09:00 до 16:00",
                        "Вкусная шаверма",
                        R.drawable.pic_food_main_stock
                    )
                }
                is MainStockTextViewHolder -> {
                    when (position) {
                        1 -> {
                            holder.initialize(
                                "Акция: ", "Равным образом новая модель" +
                                        " организационной деятельности способствует подготовки и реализации " +
                                        "соответствующий условий активизации. Не следует, однако забывать."
                            )
                        }

                        2 -> {
                            holder.initialize("Даты проведения: ", "с 25.08.2019 до 30.08.2019")
                        }
                    }
                }

                is ButtonViewHolder -> {
                    holder.constraintLayout.backgroundColor = Color.WHITE
                    holder.initialize("Как добраться?")

                    holder.button.constrainTopToTopOf(innerContent,12)
                    holder.button.constrainBottomToBottomOf(innerContent)
                    holder.button.setOnClickListener {
                        rootModule.emitEvent?.invoke(MainStockViewModel.EventType.OnlouderMainStock.toString())
                    }
                }

                is MainStockQRCodViewHolder -> {
                    holder.constraintLayout.backgroundColor = Color.WHITE
                    holder.initialize(
                        "Получай выгоду!", "Количество воспользовавшихся предложением:",
                        "146", com.ageone.naladoni.R.drawable.pic_qarcod, "145 678 345"
                    )
                }

            }

        }

    }

}

fun MainStockView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally(0)
        .fillVertically()
        .constrainTopToTopOf(innerContent)

    bodyTable
        .clipToPadding = false
}

