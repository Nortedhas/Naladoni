package com.example.ageone.Modules.Meditation

import android.graphics.Color
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.rxData
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.list
import com.example.ageone.External.Libraries.WebView.openUrl
import com.example.ageone.External.RxBus.RxBus
import com.example.ageone.External.RxBus.RxEvent
import com.example.ageone.Modules.Meditation.rows.MeditationPopularViewHolder
import com.example.ageone.Modules.Meditation.rows.MeditationSearchViewHolder
import com.example.ageone.Modules.Meditation.rows.initialize
import com.example.ageone.UIComponents.ViewHolders.MeditationCardViewHolder
import com.example.ageone.UIComponents.ViewHolders.TitleViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import io.reactivex.disposables.Disposable
import timber.log.Timber
import yummypets.com.stevia.*

class MeditationView(initModuleUI: InitModuleUI = InitModuleUI()): BaseModule(initModuleUI) {

    val viewModel = MeditationViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (position) {
                    in 0..3 -> 2
                    else -> 1
                }
            }
        }
        layoutManager
    }

//    fun bindUI() after renderUI() обновление

    init {
        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Здравствуйте!"

        renderToolbar()

        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


        renderUIO()
        bindUI()
    }

    private lateinit var personDisposable: Disposable
    fun bindUI() {
        personDisposable = RxBus.listen(RxEvent.EventAddMeditation::class.java).subscribe { meditation ->
            Timber.i("Some event ${meditation.meditationName}")
        }
        bodyTable.adapter?.notifyDataSetChanged()
    }


    inner class Factory(val rootModule: BaseModule): BaseAdapter<BaseViewHolder>() {

        private val MeditationSearchType = 0
        private val MeditationTitleType = 1
        private val MeditationPopularType = 2
        private val MeditationCardType = 3


        override fun getItemCount() = viewModel.model.quickMeditation.size + 4

        override fun getItemViewType(position: Int):Int = when(position) {
            0 -> MeditationSearchType
            1, 3 -> MeditationTitleType
            2 -> MeditationPopularType
            else -> MeditationCardType
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when(viewType) {
                MeditationSearchType -> {
                    MeditationSearchViewHolder(layout)
                }
                MeditationTitleType -> {
                    TitleViewHolder(layout)
                }
                MeditationPopularType -> {
                    MeditationPopularViewHolder(layout)
                }
                MeditationCardType -> {
                    MeditationCardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when(holder) {
                is MeditationSearchViewHolder -> {
                    holder.initialize(R.drawable.button_meditation_search)
                    holder.imageViewSearch.setOnClickListener {
                        rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnSearchPressed.toString())
                    }
                }

                is TitleViewHolder -> {
                    val title = if (position == 1) "Популярные медитации" else "Быстрый старт"
                    holder.initialize(title, Color.parseColor("#707ABA"))
                    holder.textViewTitle
                        .fillHorizontally(16)
                }

                is MeditationCardViewHolder -> {
                    val meditation = viewModel.model.quickMeditation[position - 4]
                    Timber.i("$meditation")
                    holder.initialize(
                        utils.variable.displayWidth / 2 - 8, R.drawable.kitty,
                        meditation.name, meditation.txtInfo, position, meditation.isFree)

                    holder.constraintLayout.setOnClickListener {
                        RxBus.publish(RxEvent.EventAddMeditation("meditations are loaded"))
                        if (meditation.isFree || meditation.isIntro || rxData.isVip()) //TODO: meditation in orders
                        {
                            rxData.currentMeditation = meditation
                            rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnMeditationPressed.toString())

                        } else {
                            alertManager.list(
                                "Данная медитация является платной, пожалуйста, оплатите доступ",
                                rxData.payVariants(isSet = false),
                                rxData.createPayVariantCallback(meditation.hashId, isSet = false) {json ->
                                    val url = json.optString("formUrl", "")
                                    if (url.isNotBlank()) {
                                        viewModel.model.url = url
                                        rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnPayed.toString())
                                    }

                                }
                            )
                        }

                    }

                }

                is MeditationPopularViewHolder -> {
                    holder.initialize()
                    holder.onTap = { position ->
                        rootModule.emitEvent?.invoke(MeditationViewModel.EventType.OnMeditationPressed.toString())
                    }
                }

            }

        }

    }

}

fun MeditationView.renderUIO() {

    renderBodyTable()
    bodyTable
        .fillHorizontally(0)
}

