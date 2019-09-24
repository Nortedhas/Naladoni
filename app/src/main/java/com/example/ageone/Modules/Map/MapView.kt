package com.example.ageone.Modules.Map

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.MapView.BaseMapView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.City.rows.CityEditTextViewHolder
import com.example.ageone.Modules.Map.rows.MapDiscountCardsViewHolder
import com.example.ageone.Modules.Map.rows.initialize
import yummypets.com.stevia.*

class MapView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = MapViewModel()

    val imageNavigationView by lazy {
        val imageNavigationView = BaseImageView()
        imageNavigationView.initialize()
        imageNavigationView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageNavigationView.setBackgroundResource(R.drawable.ic_navigationbuttom)
        imageNavigationView
    }

    val mapView by lazy {
       val mapView = BaseMapView()
        mapView
    }

    init {
//        viewModel.loadRealmData()

//        setBackgroundResource(R.drawable.base_background)
//
//        toolbar.title = "Карта подарков"
//        toolbar.titleTextSize = 30F
//        renderToolbar()
        bodyTable.adapter = Factory(this)
        bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
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

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<MapDiscountCardsViewHolder>() {

        override fun getItemCount() = 3

        override fun getItemViewType(position: Int): Int = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapDiscountCardsViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(wrapContent)
                .height(wrapContent)

            return MapDiscountCardsViewHolder(layout)
        }

        override fun onBindViewHolder(holder: MapDiscountCardsViewHolder, position: Int) {
            holder.initialize("title", "describe", R.drawable.ic_navigationbuttom)
        }
    }
}


fun MapView.renderUIO() {

    innerContent.subviews(
//        mapView,
        bodyTable,
        imageNavigationView

    )

//    mapView
//        .fillHorizontally()
//        .fillVertically()

    bodyTable
        .constrainBottomToBottomOf(innerContent,10)

    imageNavigationView
        .constrainRightToRightOf(innerContent,16)
        .constrainBottomToTopOf(bodyTable, 16)
        .height(25.dp)
        .width(25.dp)

//    renderBodyTable()
}


