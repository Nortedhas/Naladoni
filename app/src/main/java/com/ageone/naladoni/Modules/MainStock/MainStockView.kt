package com.ageone.naladoni.Modules.MainStock

import android.graphics.BitmapFactory
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.MainStock.rows.*
import com.ageone.naladoni.R
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

        private val MainStockDescribeType = 0
        private val MainStockTextType = 1
        private val MainStockButtomType = 2
        private val MainStockQRCodType = 3

        override fun getItemCount() = 8//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> MainStockDescribeType
            1, 2 -> MainStockTextType
            3 -> MainStockButtomType
            4 -> MainStockQRCodType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MainStockDescribeType -> {
                    MainStockDescribeViewHolder(layout)
                }
                MainStockTextType -> {
                    MainStockTextViewHolder(layout)
                }
                MainStockButtomType -> {
                    MainStockButtonViewHolder(layout)
                }
                MainStockQRCodType -> {
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
                        "пн-пт: 10:00 до 18:00. сб-вс: 09:00 до 16:00",
                        "Вкусная шаверма",
                        R.drawable.ic_category_0
                    )
                }
                is MainStockTextViewHolder -> {
                    when (position) {
                        1 -> {
                            holder.initialize(
                                position, "Равным образом новая модель" +
                                        " организационной деятельности способствует подготовки и реализации " +
                                        "соответствующий условий активизации. Не следует, однако забывать."
                            )
                        }

                        2 -> {
                            holder.initialize(position, "с 25.08.2019 до 30.08.2019")
                        }
                    }
                }
                is MainStockButtonViewHolder -> {
                    holder.initialize()
                    holder.button.setOnClickListener {
                        rootModule.emitEvent?.invoke(MainStockViewModel.EventType.OnlouderMainStock.name)
                    }
                }

                is MainStockQRCodViewHolder -> {
                    holder.initialize("146", R.drawable.pic_qarcod, "145 678 345"
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


