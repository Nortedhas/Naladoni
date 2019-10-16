package com.ageone.naladoni.Modules.Search

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ageone.naladoni.R
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.RecyclerView.ColumnEqualsPaddingItemDecoration
import com.ageone.naladoni.External.Base.View.BaseView
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import com.ageone.naladoni.UIComponents.ViewHolders.СardViewHolder
import yummypets.com.stevia.*
import com.ageone.naladoni.External.Base.SearchView.BaseSearchView
import com.ageone.naladoni.Modules.Search.rows.SearchEmptyViewHolder
import com.ageone.naladoni.Modules.Search.rows.initialize

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

    val linearManager by lazy {
        val layoutManager = LinearLayoutManager(currentActivity)
        layoutManager
    }

    val gridManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.back_search)

        toolbar.title = "Хочу найти"

        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.layoutManager = linearManager

        renderUIO()
        bindUI()
    }

    fun bindUI() {
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val SearchViewType = 0

        private val SearchEmptyViewType = 1

        override fun getItemCount() =
                if (listReceived) {
                    bodyTable.layoutManager = gridManager
                    10
                } else {
                    bodyTable.layoutManager = linearManager
                    1
                }

        override fun getItemViewType(position: Int): Int = if (listReceived) {

            SearchViewType
        } else {
            SearchEmptyViewType
        }



                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

                    val layout = ConstraintLayout(parent.context)

                    layout
                        .width(matchParent)
                        .height(wrapContent)
                    val holder = when (viewType) {
                        SearchViewType -> {
                            СardViewHolder(layout)
                        }
                        SearchEmptyViewType -> {
                            SearchEmptyViewHolder(layout)
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
                            holder.viewCard.setOnClickListener {
                                rootModule.emitEvent?.invoke(SearchViewModel.EventType.OnlouderSearch.toString())

                            }
                        }
                        is SearchEmptyViewHolder -> {
                            holder.initialize("Найдите для себя лучшую акцию в вашем городе!")
                        }

                    }

                }

            }

    }

    var listReceived = true

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

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally(0)
        .fillVertically()
        .constrainTopToBottomOf(card, 8)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false

    bodyTable.addItemDecoration(ColumnEqualsPaddingItemDecoration(8.dp, 2))

}


