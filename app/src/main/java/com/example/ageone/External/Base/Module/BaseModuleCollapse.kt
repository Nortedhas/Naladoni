package com.example.ageone.External.Base.Module

import android.content.Context
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.updatePadding
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.External.Base.Toolbar.BaseToolbar
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ConstraintLayout.BaseConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseRecyclerView
import com.example.ageone.External.Extensions.Activity.hideKeyboard
import com.example.ageone.External.InitModuleUI
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import yummypets.com.stevia.*


open class BaseModuleCollapse(override var initModuleUI: InitModuleUI = InitModuleUI()): CoordinatorLayout(currentActivity as Context), Module {
    override fun getView(): View = this

    override val idView: Int
        get() = id


    val appBarLayout by lazy {
        val appBarLayout = AppBarLayout(currentActivity)
        appBarLayout
    }

    val collapsingToolbarLayout by lazy {
        val collapsingToolbarLayout = CollapsingToolbarLayout(currentActivity)
        collapsingToolbarLayout
    }

    val nestedScrollView by lazy {
        val nestedScrollView = NestedScrollView(currentActivity as Context)
        nestedScrollView
    }

    private val content by lazy {
        val innerContent = BaseConstraintLayout()
        innerContent.setPadding(0, utils.variable.statusBarHeight, 0, 0)
        innerContent
    }

    val toolbar by lazy {
        val toolBar = BaseToolbar(initModuleUI, content)

        toolBar
            .setBackgroundColor(initModuleUI.colorToolbar)

        if (initModuleUI.isToolbarHidden) {
            toolBar.visibility = View.GONE
        }

        toolBar
    }

    val viewManagerBodyTable by lazy {
        val viewManager = LinearLayoutManager(currentActivity)
        viewManager
    }
    
    val bodyTable by lazy {
        val recyclerView = BaseRecyclerView()
        recyclerView.layoutManager = viewManagerBodyTable
        recyclerView
    }

    override var onDeInit: (() -> Unit)? = null
    override var emitEvent: ((String) -> Unit)? = null

    init {
        id = View.generateViewId()
        renderUI()

        currentActivity?.hideKeyboard()
        Timber.i("${this.className()} Init ")
    }

    var compositeDisposable = CompositeDisposable()

    fun unBind() {
        compositeDisposable.dispose()
    }

    fun renderUI() {
        subviews(
            nestedScrollView.subviews(
                content
            ),
            appBarLayout.subviews(
                collapsingToolbarLayout,
                toolbar
            )
        )

        appBarLayout
            .height(100)
            .width(matchParent)


        collapsingToolbarLayout
            .width(matchParent)
            .height(matchParent)
        collapsingToolbarLayout.apply {
            expandedTitleMarginStart = 48.dp
            expandedTitleMarginEnd = 64.dp
        }


        val paramsToolbar = toolbar.layoutParams
        val newParams: CollapsingToolbarLayout.LayoutParams
        newParams = if (paramsToolbar is CollapsingToolbarLayout.LayoutParams) {
            paramsToolbar
        } else {
            CollapsingToolbarLayout.LayoutParams(paramsToolbar)
        }
        newParams.collapseMode = CollapsingToolbarLayout.LayoutParams.COLLAPSE_MODE_OFF
        toolbar.layoutParams = newParams
        toolbar.requestLayout()

        toolbar
//            .constrainTopToTopOf(content, 0)
            .fillHorizontally()
            .height(utils.variable.actionBarHeight)



        val params = nestedScrollView.layoutParams as LayoutParams
        params.behavior = AppBarLayout.ScrollingViewBehavior()
        nestedScrollView.requestLayout()

        nestedScrollView
            .fillHorizontally()
            .fillVertically()

        content
            .fillHorizontally()
            .fillVertically()

    }

    fun renderToolbar() {
        toolbar.initialize()
    }

    fun renderBodyTable() {
        content.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally(8)//TODO: change!
            .fillVertically()
            .constrainTopToTopOf(content)
            .updatePadding(bottom = 24.dp)

        bodyTable
            .clipToPadding = false

    }

    open fun reload() {
        bodyTable.adapter?.notifyDataSetChanged()
    }

    override fun className(): String {
        return utils.tools.getClassName(this.toString())
    }
}
