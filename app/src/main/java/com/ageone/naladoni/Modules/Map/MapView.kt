package com.ageone.naladoni.Modules.Map

import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.mapView
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.Map.setMyLocation
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Map.rows.MapDiscountCardViewHolder
import com.ageone.naladoni.Modules.Map.rows.initialize
import com.ageone.naladoni.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber
import yummypets.com.stevia.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory


class MapView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = MapViewModel()

    val imageTopView by lazy {
        val imageView = BaseImageView()
        imageView.initialize()
        imageView.orientation = GradientDrawable.Orientation.BOTTOM_TOP
        imageView.setBackgroundResource(R.drawable.pic_map_top_image)
        imageView
    }

    val buttonMyLocation by lazy {
        val buttonMyLocation = BaseImageView()
        buttonMyLocation.initialize()
        buttonMyLocation.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        buttonMyLocation.setBackgroundResource(R.drawable.pic_navigationbuttom)
        buttonMyLocation.elevation = 5F.dp
        buttonMyLocation
    }

//    var buttonMyLocation: ImageView

    init {
//        viewModel.loadRealmData()

        Timber.i("Start init map")
        /*buttonMyLocation = (mapView.findViewById<View>(Integer.parseInt("1")).parent as View)
            .findViewById(Integer.parseInt("2"))*/

        mapView.getMapAsync{ map ->
            Timber.i("Map ready!")
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.context, R.raw.map_style))
            map.setMyLocation(buttonMyLocation)

            val marker = map.addMarker(
                    MarkerOptions()
                        .position(startLocation)
                        .title("Melbourne")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pic_selected_flag))
                )

            marker.tag = 1

            map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))

            map.setOnMarkerClickListener { marker ->
                if (marker.tag is Int) {
                    val position = marker.tag as Int
                    if (position in 0 until bodyTable.size) {
                        bodyTable.smoothScrollToPosition(position)
                    }
                }

                return@setOnMarkerClickListener false
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

    innerContent.subviews(
        mapView,
        buttonMyLocation,
        bodyTable,
        imageTopView
    )

    mapView
        .fillHorizontally()
        .fillVertically()

    buttonMyLocation
        .height(24.dp)
        .width(24.dp)
        .constrainRightToRightOf(innerContent, 8)
        .constrainBottomToTopOf(bodyTable, 8)

    bodyTable
        .constrainBottomToBottomOf(innerContent)

    imageTopView
        .constrainTopToTopOf(toolbar)
        .height(20)
        .fillHorizontally()

}


