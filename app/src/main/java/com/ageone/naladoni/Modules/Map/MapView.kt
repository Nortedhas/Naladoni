package com.ageone.naladoni.Modules.Map

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
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
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import timber.log.Timber
import yummypets.com.stevia.*


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
    val all_icons = arrayOf(
        R.drawable.ic_category_0,
        R.drawable.ic_category_1,
        R.drawable.ic_category_2
    )

    init {
        viewModel.loadRealmData()

        Timber.i("Stocks: ${viewModel.realmData}")

        setMap()

        setBackgroundResource(R.drawable.base_background)
        toolbar.title = "Карта подарков"
        renderToolbar()

        bodyTable.adapter = Factory(this)
        bodyTable.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()

        onDeInit = {
            innerContent.removeView(mapView)
        }
    }

    private fun setMap() {
        mapView.getMapAsync { map ->
            Timber.i("Map ready!")
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.context, R.raw.map_style))
            map.setMyLocation(buttonMyLocation)
            map.setMarkers()

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
    }

    fun createMarkerIconBitmap(back: Bitmap, icon: Bitmap): Bitmap {
        val flag = Bitmap.createBitmap(back.width, back.height, back.config)

        val canvas = Canvas(flag)
        canvas.drawBitmap(back, Matrix(), null)
        canvas.drawBitmap(
            Bitmap.createScaledBitmap(icon, 26.dp, 26.dp, false),
            26F.dp, 11F.dp, null)

        return flag
    }

    fun bindUI() {
        /*compositeDisposable.add(
            RxBus.listen(RxEvent.EventReloadStocks::class.java).subscribe {
                viewModel.loadRealmData()

                Timber.i("Stocks: ${viewModel.realmData}")

                bodyTable.adapter?.notifyDataSetChanged()
            }
        )*/
    }

    fun GoogleMap.setMarkers(){
            viewModel.realmData.forEachIndexed { index, stock ->
                stock.location?.let {location ->
                    val markerIcon = createMarkerIconBitmap(
                        BitmapFactory.decodeResource(context.resources, R.drawable.pic_selected_flag),
                        BitmapFactory.decodeResource(context.resources, R.drawable.pic_categories_1)
                    )

                    val marker = addMarker(
                        MarkerOptions()
                            .position(
                                LatLng(
                                    location.latitude,
                                    location.longitude
                                )
                            )
                            .icon(
                                BitmapDescriptorFactory.fromBitmap(markerIcon)
                            )
                    )

                    marker.tag = index
                }

            }
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<MapDiscountCardViewHolder>() {

        override fun getItemCount() = viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = 0

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapDiscountCardViewHolder {
            val layout = ConstraintLayout(parent.context)

            layout
                .width(wrapContent)
                .height(wrapContent)

            return MapDiscountCardViewHolder(layout)
        }

        override fun onBindViewHolder(holder: MapDiscountCardViewHolder, position: Int) {
            val stock = viewModel.realmData[position]
            holder.initialize(
                stock.name,
                stock.shortAbout,
                currentActivity?.resources?.getIdentifier(
                    "drawable/ic_category_${stock.category?.serialNum ?: 0}",
                    null, context.packageName) ?: R.drawable.ic_category_0
            )

            holder.buttonUse.setOnClickListener {
                emitEvent?.invoke(MapViewModel.EventType.OnlouderMap.name)

            }

        }

        }
    }


fun MapView.renderUIO() {
    innerContent.removeAllViews()

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


