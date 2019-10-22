package com.ageone.naladoni.Modules.Navigation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.content.res.AppCompatResources
import com.ageone.naladoni.Application.mapViewHowGo
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.Map.drawPolyline
import com.ageone.naladoni.External.Base.Map.loadRoutePath
import com.ageone.naladoni.External.Base.Map.setMyLocation
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import timber.log.Timber
import yummypets.com.stevia.*

class NavigationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI){

    val viewModel = NavigationViewModel()

    val buttonMyLocation by lazy {
        val imageView = BaseImageView()
        imageView.initialize()
        imageView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageView.setBackgroundResource(R.drawable.pic_navigationbuttom)
        imageView.elevation = 5F.dp
        imageView
    }

    init {
//        viewModel.loadRealmData()

        mapViewHowGo.getMapAsync{ map ->
            Timber.i("Map ready!")
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.context, R.raw.map_style))
            map.setMyLocation(buttonMyLocation)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))

            map.setUpMarkers()
        }

        setBackgroundResource(R.drawable.base_background)
        toolbar.title = "Как добраться"
        renderToolbar()

        renderUIO()
        bindUI()

        onDeInit = {
            innerContent.removeView(mapViewHowGo)
        }
    }

    fun bindUI() {
        /*compositeDisposable.add(
          RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
              bodyTable.adapter?.notifyDataSetChanged()
          }
      )*/
    }

    fun GoogleMap.setUpMarkers() {
        clear()
        rxData.currentStock?.let { stock ->
            stock.location?.let {location ->
                var endLocation = LatLng(
                    location.latitude,
                    location.longitude
                )

                val markerEnd = addMarker(
                    MarkerOptions()
                        .position(
                            endLocation
                        )
                        .icon(
                            getMarker(R.drawable.ic_end_path)
                        )
                        .anchor(0.6F, 1F)
                )


                val markerStart = addMarker(
                    MarkerOptions()
                        .position(startLocation)
                        .icon(
                            getMarker(R.drawable.pic_start_flag)
                        )
                        .anchor(0.4F, 0.6F)
                )

                loadRoutePath(startLocation, endLocation, emptyArray()) { route ->
                    drawPolyline(route, Color.parseColor("#F06F28"), 6F)
                }

            }
        }
    }

}

fun NavigationView.getMarker(id: Int) = //bitmapDescriptorFromVector(this.context, id)
BitmapDescriptorFactory.fromBitmap(getBitmapFromVectorDrawable(context, id))

fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
    val drawable = AppCompatResources.getDrawable(context, drawableId)
    lateinit var bitmap: Bitmap
    drawable?.let { drawable ->
        bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
    }

    return bitmap
}


fun NavigationView.renderUIO() {

        innerContent.subviews(
            mapViewHowGo,
            buttonMyLocation
        )

        mapViewHowGo
            .fillHorizontally()
            .fillVertically()

    buttonMyLocation
        .constrainRightToRightOf(innerContent, 16)
        .constrainBottomToBottomOf(innerContent, 16)
        .height(24.dp)
        .width(24.dp)
    
}