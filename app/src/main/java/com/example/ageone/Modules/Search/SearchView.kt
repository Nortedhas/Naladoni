package com.example.ageone.Modules.Search

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ageone.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.View.BaseView
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.List.rows.initialize
import com.example.ageone.Modules.List.rows.СardViewHolder
import yummypets.com.stevia.*
import com.example.ageone.External.Base.SearchView.BaseSearchView


class SearchView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = SearchViewModel()

    val card by lazy {
        val view = BaseView()
        view.cornerRadius = 12.dp
        view.elevation = 5F.dp
        view.backgroundColor = Color.argb(100, 255, 255, 255)
        view.orientation = GradientDrawable.Orientation.BOTTOM_TOP
        view.initialize()
        view
    }

    val searchView by lazy {
        val searchView = BaseSearchView()
        searchView.color = Color.WHITE
        searchView.isAlwaysExpand = true
        searchView.queryHint = "Поиск"
        searchView.initialize()
        searchView
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_search)

        toolbar.title = "Хочу найти"

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.layoutManager = layoutManager

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

        private val SearchViewType = 0

        override fun getItemCount() = 10//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = SearchViewType

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                SearchViewType -> {
                    СardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is СardViewHolder -> {
                    holder.initialize(
                        "Скидка 500 при покупке от 2500",
                        "Nike", "до 12.08.2019", R.drawable.pic_washing
                    )
                }

            }

        }

    }

}

fun SearchView.renderUIO() {

    innerContent.subviews(
        card.subviews(
            searchView
        )
    )

    card
        .constrainTopToBottomOf(toolbar, 5)
        .constrainRightToRightOf(innerContent)
        .constrainLeftToLeftOf(innerContent)

    renderBodyTable()

    bodyTable
        .constrainTopToBottomOf(card, 37)
}


