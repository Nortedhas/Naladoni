package com.example.ageone.Modules.Map

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.mapView
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Map.rows.MapDiscountCardViewHolder
import com.example.ageone.Modules.Map.rows.initialize
import com.example.ageone.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber
import yummypets.com.stevia.*

class MapView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = MapViewModel()

    val imageNavigationView by lazy {
        val imageNavigationView = BaseImageView()
        imageNavigationView.initialize()
        imageNavigationView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageNavigationView.setBackgroundResource(R.drawable.pic_navigationbuttom)
        imageNavigationView.elevation = 5F.dp
        imageNavigationView
    }

    val position = LatLng(-33.920455, 18.466941)

    init {
//        viewModel.loadRealmData()


        Timber.i("Start init map")

        mapView.getMapAsync{ map ->
            Timber.i("Map ready!")
            MapsInitializer.initialize(currentActivity?.applicationContext)
            with(map) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(position, 13f))
                addMarker(MarkerOptions().position(position))
                mapType = GoogleMap.MAP_TYPE_NORMAL
                setOnMapClickListener {
                    Toast.makeText(currentActivity, "Clicked on map", Toast.LENGTH_SHORT).show()
                }
            }
        }

        setBackgroundResource(R.drawable.base_background)
        toolbar.title = "Карта подарков"
        renderToolbar()

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

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<MapDiscountCardViewHolder>() {

        override fun getItemCount() = 3

        override fun getItemViewType(position: Int): Int = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapDiscountCardViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(wrapContent)
                .height(wrapContent)

            return MapDiscountCardViewHolder(layout)
        }

        override fun onBindViewHolder(holder: MapDiscountCardViewHolder, position: Int) {
            holder.initialize("Шаверма Mix",
                "При покупке шавермы big получи 0.5 колы в подарок!", R.drawable.pic_groupfood)
        }
    }

}


fun MapView.renderUIO() {

    mapView?.let { mapView ->

        Timber.i("Map is $mapView")
        innerContent.subviews(
            mapView,
            bodyTable,
            imageNavigationView
        )

        mapView
            .fillHorizontally()
            .fillVertically()
    }



    bodyTable
        .constrainBottomToBottomOf(innerContent)

    imageNavigationView
        .constrainRightToRightOf(innerContent, 16)
        .constrainBottomToTopOf(bodyTable, 16)
        .height(24.dp)
        .width(24.dp)

//    renderBodyTable()
}


