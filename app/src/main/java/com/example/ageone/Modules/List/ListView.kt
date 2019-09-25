package com.example.ageone.Modules.List

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.List.rows.СardViewHolder
import com.example.ageone.Modules.List.rows.initialize
import yummypets.com.stevia.*

class ListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ListViewModel()

    val filterView by lazy {
        val filterView = BaseImageView()
        filterView.initialize()
        filterView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        filterView.setBackgroundResource(R.drawable.ic_filter)
        filterView
    }
    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager
    }
    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Подарки города"
        renderToolbar()
        bodyTable.layoutManager = layoutManager
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

        private val СardType = 0

        override fun getItemCount() = 10//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = СardType

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                СardType -> {
                    СardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is СardViewHolder -> {
                    holder.initialize("Скидка 500 при покупке от 2500",
                        "Nike","до 12.08.2019", R.drawable.pic_photo_logo)
                }

            }

        }

    }

}

fun ListView.renderUIO() {
    toolbar.subviews(
        filterView
    )
    filterView
        .constrainRightToRightOf(innerContent, 5)
    renderBodyTable()
}


