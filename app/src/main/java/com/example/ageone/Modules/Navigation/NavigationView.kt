package com.example.ageone.Modules.Navigation
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import com.example.ageone.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import yummypets.com.stevia.*

val MAPVIEW_BUNDLE_KEY = "MapViewBundleKey"
class NavigationView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI),
    OnMapReadyCallback {

    val viewModel = NavigationViewModel()

    val imageNavigationView by lazy {
        val imageNavigationView = BaseImageView()
        imageNavigationView.initialize()
        imageNavigationView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageNavigationView.setBackgroundResource(R.drawable.pic_navigationbuttom)
        imageNavigationView.elevation = 5F.dp
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
    override fun onMapReady(googleMap: GoogleMap) {
        /*googleMap.setMinZoomPreference(12F)*/
        val ny = LatLng(40.7143528, -74.0059731)
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ny))
    }
}


fun NavigationView.renderUIO() {

    innerContent.subviews(
        mapView,
        imageNavigationView


    )

    mapView
        .fillHorizontally()
        .fillVertically()

    imageNavigationView
        .constrainRightToRightOf(innerContent, 16)
        .constrainBottomToBottomOf(innerContent, 16)
        .height(24.dp)
        .width(24.dp)
    
}