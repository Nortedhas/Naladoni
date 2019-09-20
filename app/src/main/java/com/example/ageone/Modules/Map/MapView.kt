package com.example.ageone.Modules.Map

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.Map.rows.MapViewHolder
import com.example.ageone.Modules.Map.rows.initialize
import yummypets.com.stevia.*

class MapView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {
    override fun unBind() {}

    val viewModel = MapViewModel()
    val imageView1 by lazy {
        val imageView = BaseImageView()
        imageView.setImageResource(R.drawable.map)
        imageView.initialize()
        imageView.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        imageView
    }
val buttonSkip by lazy {
    val button = BaseButton()
    button.typeface = Typeface.DEFAULT
    button.backgroundColor = Color.TRANSPARENT
    button.setBackgroundResource(R.drawable.setings)
    button.orientation = GradientDrawable.Orientation.BOTTOM_TOP
    button.initialize()
    button
}
    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }
    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Карта подарков"
        toolbar.titleTextSize = 30F
        toolbar
            .constrainLeftToLeftOf(innerContent, 100)
        renderToolbar()
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


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

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val MapVType = 0

        override fun getItemCount() = 1//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> MapVType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                MapVType -> {
                    MapViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is MapViewHolder -> {
                    holder.initialize()
                }

            }

        }

    }

}

fun MapView.renderUIO() {

    innerContent.subviews(
//        imageView1
        buttonSkip
    )
//    imageView1
    buttonSkip
        .constrainLeftToLeftOf(innerContent, 0)


    renderBodyTable()
}


