package com.ageone.naladoni.Modules.Navigation
import android.graphics.drawable.GradientDrawable
import com.ageone.naladoni.Application.mapViewHowGo
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Extensions.Activity.startLocation
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MapStyleOptions
import timber.log.Timber
import yummypets.com.stevia.*

class NavigationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI){

    val viewModel = NavigationViewModel()

    val imageNavigationView by lazy {
        val imageNavigationView = BaseImageView()
        imageNavigationView.initialize()
        imageNavigationView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageNavigationView.setBackgroundResource(R.drawable.pic_navigationbuttom)
        imageNavigationView.elevation = 5F.dp
        imageNavigationView
    }

    init {
//        viewModel.loadRealmData()

        mapViewHowGo.getMapAsync{ map ->
            Timber.i("Map ready!")
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this.context, R.raw.map_style))
//            map.setMyLocation()
            map.isMyLocationEnabled = true
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 13f))

        }

        setBackgroundResource(R.drawable.base_background)
        toolbar.title = "Как добраться"
        renderToolbar()

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
}


fun NavigationView.renderUIO() {
    
    innerContent.subviews(
        mapViewHowGo,
        imageNavigationView
    )

    mapViewHowGo
        .fillHorizontally()
        .fillVertically()

    imageNavigationView
        .constrainRightToRightOf(innerContent, 16)
        .constrainBottomToBottomOf(innerContent, 16)
        .height(24.dp)
        .width(24.dp)
    
}