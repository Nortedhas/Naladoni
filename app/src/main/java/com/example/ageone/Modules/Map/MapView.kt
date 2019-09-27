package com.example.ageone.Modules.Map

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Map.rows.MapDiscountCardsViewHolder
import com.example.ageone.Modules.Map.rows.initialize
import yummypets.com.stevia.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import android.os.Bundle


val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
class MapView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI),
    OnMapReadyCallback {

    val viewModel = MapViewModel()

    val imageNavigationView by lazy {
        val imageNavigationView = BaseImageView()
        imageNavigationView.initialize()
        imageNavigationView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageNavigationView.setBackgroundResource(R.drawable.ic_navigationbuttom)
        imageNavigationView
    }

    val mapView by lazy {
       val mapView = com.google.android.gms.maps.MapView(currentActivity)
        mapView
    }

    init {
//        viewModel.loadRealmData()

        var mapViewBundle: Bundle? = null
        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

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
            holder.initialize("Шаверма Mix",
                "При покупке шавермы big получи 0.5 колы в подарок!", R.drawable.pic_groupfood)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        /*googleMap.setMinZoomPreference(12F)*/
        val ny = LatLng(40.7143528, -74.0059731)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny))
    }
}


fun MapView.renderUIO() {

    innerContent.subviews(
        mapView,
        bodyTable,
        imageNavigationView


    )

    mapView
        .fillHorizontally()
        .fillVertically()


    bodyTable
        .constrainBottomToBottomOf(innerContent)

    imageNavigationView
        .constrainRightToRightOf(innerContent, 16)
        .constrainBottomToTopOf(bodyTable, 16)
        .height(24.dp)
        .width(24.dp)

//    renderBodyTable()
}


