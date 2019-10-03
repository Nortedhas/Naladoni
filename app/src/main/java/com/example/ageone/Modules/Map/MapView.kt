package com.example.ageone.Modules.Map

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.mapView
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Map.setLocationButtonInMap
import com.example.ageone.External.Base.Map.setMyLocation
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Extensions.Activity.currentLocation
import com.example.ageone.External.Extensions.Activity.locationBase
import com.example.ageone.External.Extensions.Activity.startLocation
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Map.rows.MapDiscountCardViewHolder
import com.example.ageone.Modules.Map.rows.initialize
import com.example.ageone.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import timber.log.Timber
import yummypets.com.stevia.*


class MapView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = MapViewModel()

    val imagefringeView by lazy {
        val imageView = BaseImageView()
        imageView.initialize()
        imageView.orientation = GradientDrawable.Orientation.BOTTOM_TOP
        imageView.setBackgroundResource(R.drawable.pic_map_top_image)
        imageView
    }
    /*val buttonMyLocation by lazy {
        val imageNavigationView = BaseImageView()
        imageNavigationView.initialize()
        imageNavigationView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageNavigationView.setBackgroundResource(R.drawable.pic_navigationbuttom)
        imageNavigationView.elevation = 5F.dp
        imageNavigationView
    }*/

    var buttonMyLocation: ImageView

    init {
//        viewModel.loadRealmData()

        Timber.i("Start init map")
        buttonMyLocation = (mapView.findViewById<View>(Integer.parseInt("1")).parent as View)
            .findViewById(Integer.parseInt("2"))

        mapView.getMapAsync{ map ->
            Timber.i("Map ready!")
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.context, R.raw.map_style))
//            map.setMyLocation()
            map.isMyLocationEnabled = true
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))

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

    fun setUpLocationButton() {
        buttonMyLocation.setImageResource(R.drawable.pic_navigationbuttom)

        buttonMyLocation
            .height(24.dp)
            .width(24.dp)
            .setLocationButtonInMap(0,0,10,150)
    }

}


fun MapView.renderUIO() {

    innerContent.subviews(
        mapView,
        bodyTable,
        imagefringeView
    )

    mapView
        .fillHorizontally()
        .fillVertically()

    bodyTable
        .constrainBottomToBottomOf(innerContent)

    imagefringeView
        .constrainTopToTopOf(toolbar)
        .height(20)
        .fillHorizontally()

    setUpLocationButton()
}


