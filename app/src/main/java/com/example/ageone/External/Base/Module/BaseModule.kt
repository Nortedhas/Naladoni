package com.example.ageone.External.Base.Module

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ageone.External.Base.Toolbar.BaseToolbar
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.hideKeyboard
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.ConstraintLayout.BaseConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseRecyclerView
import com.example.ageone.External.InitModuleUI
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import yummypets.com.stevia.*


abstract class BaseModule(val initModuleUI: InitModuleUI = InitModuleUI()) : ConstraintLayout(currentActivity) {

    val backgroundImage by lazy {
        val image = BaseImageView()
        image
    }

    private val content by lazy {
        val innerContent = BaseConstraintLayout()
        innerContent.setPadding(0, utils.variable.statusBarHeight, 0, 0)
        innerContent
    }

    val innerContent by lazy {
        val innerContent = BaseConstraintLayout()
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

    var onDeInit: (() -> Unit)? = null
    var emitEvent: ((String) -> Unit)? = null

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
            backgroundImage,
            content.subviews(
                toolbar,
                innerContent
            )
        )

        content
            .fillHorizontally()
            .fillVertically()

        toolbar
            .constrainTopToTopOf(content, 0)
            .fillHorizontally()
            .height(utils.variable.actionBarHeight)

        innerContent
            .fillHorizontally()
            .fillVertically()
            .constrainTopToBottomOf(toolbar)

        backgroundImage
            .fillHorizontally()
            .fillVertically()

    }

    fun renderToolbar() {
        toolbar.initialize()
    }

    fun renderBodyTable() {
        innerContent.subviews(
            bodyTable
        )

        bodyTable
            .fillHorizontally(8)//TODO: change!
            .fillVertically()
            .constrainTopToTopOf(innerContent)
            .updatePadding(bottom = 24.dp)

        bodyTable
            .clipToPadding = false

    }



    fun reload() {
        bodyTable.adapter?.notifyDataSetChanged()
    }

    fun className(): String {
        return utils.tools.getClassName(this.toString())
    }
}
