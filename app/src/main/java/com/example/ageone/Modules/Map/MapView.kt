package com.example.ageone.Modules.Map

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.FAQ.Factory
import com.example.ageone.Modules.FAQ.rows.SliderViewHolder
import com.example.ageone.Modules.Map.rows.MapDiscountCardsViewHolder
import com.example.ageone.Modules.Map.rows.initialize
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapFragment
import com.google.android.gms.maps.MapsInitializer
import yummypets.com.stevia.*

class MapView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MapViewModel()

    //    val imageView by lazy {
//        val imageView = BaseImageView()
//        imageView.initialize()
//        imageView.orientation = GradientDrawable.Orientation.BOTTOM_TOP
//        imageView.setBackgroundResource(R.drawable.ic_barbac)
//        imageView
//    }
    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)
//
//        toolbar.title = "Карта подарков"
//        toolbar.titleTextSize = 30F
//        renderToolbar()
        bodyTable.adapter = Factory(this)
        bodyTable.layoutManager =
            LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
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

//    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {
//
//        private val MapDiscountCardsType = 0
//
//        override fun getItemCount() = 1//viewModel.realmData.size
//
//        override fun getItemViewType(position: Int): Int = 0
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
//
//            val layout = ConstraintLayout(parent.context)
//
//            layout
//                .width(matchParent)
//                .height(wrapContent)
//
//            val holder = when (viewType) {
//                MapDiscountCardsType -> {
//                    MapDiscountCardsViewHolder(layout)
//                }
//                else -> {
//                    BaseViewHolder(layout)
//                }
//            }
//
//            return holder
//        }
//
//        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//
//            when (holder) {
//                is MapDiscountCardsViewHolder-> {
//                    viewHolder.textView.text = list[position]
//                    viewHolder.imageView.setBackgroundResource(resourceImages[position])
//                    holder.initialize(240,120,"шаверма","0.5 колы в подарок!")
//
//                }
//            }
//
//        }
//
//    }
class Factory(val rootModule: BaseModule) : BaseAdapter<MapDiscountCardsViewHolder>() {

    private val list = listOf(
        "Шаверма",
        "одежда",
        "игры"
    )

    override fun getItemCount() = list.size
    override fun getItemViewType(position: Int): Int = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapDiscountCardsViewHolder {
        val layout = ConstraintLayout(parent.context)

        layout
            .width(matchParent)
            .height(wrapContent)

        return MapDiscountCardsViewHolder(layout)
    }

    override fun onBindViewHolder(viewHolder: MapDiscountCardsViewHolder, position: Int) {
        viewHolder.textViewTitle.text = list[position]
        viewHolder.initialize(240, 120, list[position], "0.5 колы в подарок!")


    }

}
}


fun MapView.renderUIO() {

    innerContent.subviews(
        bodyTable
            .constrainTopToTopOf(innerContent, 20)
            .constrainBottomToBottomOf(innerContent, 20)
            .constrainLeftToLeftOf(innerContent)
            .constrainRightToRightOf(innerContent)

    )

//            renderBodyTable()
}


