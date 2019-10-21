package com.ageone.naladoni.Modules.MainStock

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Shader
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Internal.Utilities.getIdCategoryIcon
import com.ageone.naladoni.Modules.MainStock.rows.MainStockDescribeViewHolder
import com.ageone.naladoni.Modules.MainStock.rows.MainStockQRCodViewHolder
import com.ageone.naladoni.Modules.MainStock.rows.MainStockTextViewHolder
import com.ageone.naladoni.Modules.MainStock.rows.initialize
import com.ageone.naladoni.Modules.MainStock.rows.*
import com.ageone.naladoni.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import yummypets.com.stevia.*


class MainStockView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MainStockViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        Glide.with(this)
            .asBitmap()
            .load(rxData.currentStock?.image?.original ?: "")
            .into(object : CustomTarget<Bitmap>(){
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap>?
                ) {
                    val bitmapDrawable = BitmapDrawable(resources, resource)
                    bitmapDrawable.setTileModeXY(
                        Shader.TileMode.REPEAT,
                        Shader.TileMode.REPEAT
                    )
                    background = bitmapDrawable
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })

        toolbar.title = ""
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

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
        private val  MainStockDataTextType = 2
        private val MainStockButtomType = 3
        private val MainStockQRCodType = 4

        override fun getItemCount() = 8 //viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> MainStockDescribeType
            1 -> MainStockTextType
            2 -> MainStockDataTextType
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
                MainStockDataTextType -> {
                    MainStockDataTextViewHolder(layout)
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
                        rxData.currentStock?.name ?: "",
                        getIdCategoryIcon(rxData.currentStock?.category?.serialNum ?: 0),
                        rxData.currentStock?.workTimeFrom ?: "",
                        rxData.currentStock?.workTimeTo ?: "",
                        rxData.currentStock?.workTimeFrom ?: "",//TODO: change
                        rxData.currentStock?.workTimeTo ?: ""//TODO: change
                    )
                }
                is MainStockTextViewHolder -> {
                    holder.initialize(rxData.currentStock?.longAbout ?: "")
                }

                is MainStockDataTextViewHolder ->{
                    holder.initialize(
                        rxData.currentStock?.avaliableTo ?: 0,//TODO: change
                        rxData.currentStock?.avaliableTo ?: 0
                    )
                }

                is MainStockButtonViewHolder -> {
                    holder.constraintLayout.backgroundColor = Color.WHITE
                    holder.initialize()
                    holder.button.setOnClickListener {
                        rootModule.emitEvent?.invoke(MainStockViewModel.EventType.OnlouderMainStock.name)
                    }
                }

                is MainStockQRCodViewHolder -> {
                    holder.constraintLayout.backgroundColor = Color.WHITE
                    holder.initialize(
                        "146",
                        R.drawable.pic_qarcod,
                        "145 678 345"
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


