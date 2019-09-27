package com.ageone.potok.External.Base.Toolbar

import android.graphics.Color
import android.view.View
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.InitModuleUI
import com.example.ageone.R
import yummypets.com.stevia.*

class BaseToolbar(val initModuleUI: InitModuleUI, val content: ConstraintLayout): Toolbar(currentActivity) {
    var title: String? = null
    var textColor: Int = Color.WHITE
    var iconOtherSize = 25
    var iconExitSize = 25

    private val viewOther by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_close)
        view.visibility = View.GONE
        view
    }

    private val viewExit by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_exit)
        view.visibility = View.GONE
        view
    }

    fun setTitleToolbar(title: String) {
        setTitle(title)
    }

    fun initialize() {
        setTitle(title)
        setTitleTextColor(textColor)
        if (initModuleUI.isBackPressed) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                router.onBackPressed()
            }
        }

        initModuleUI.exitIcon?.let { icon ->
            viewExit.setImageResource(icon)
        }
        initModuleUI.exitListener?.let { exitListener ->
            viewExit.visibility = View.VISIBLE
            viewExit.setOnClickListener(exitListener)
        }

        initModuleUI.iconListener?.let { listener ->
            viewOther.visibility = View.VISIBLE
            viewOther.setOnClickListener(listener)
        }

        renderUI()

    }

    private fun renderUI() {
        content.subviews(
            viewExit,
            viewOther
        )

        viewExit
            .width(iconExitSize)
            .height(iconExitSize)
            .constrainRightToRightOf(this, 16)
            .constrainCenterYToCenterYOf(this)


        viewOther
            .width(iconOtherSize)
            .height(iconOtherSize)
            .constrainRightToLeftOf(viewExit, 16)
            .constrainCenterYToCenterYOf(this)
    }
}