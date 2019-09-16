package com.example.ageone.External.Base.Toolbar

import android.graphics.Color
import android.view.View
import android.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.*

class BaseToolbar(val initModuleUI: InitModuleUI, val content: ConstraintLayout): Toolbar(currentActivity) {
    var title: String? = null
    var textColor: Int = Color.WHITE
//    var titleTextSize: Float = 18F

    var viewIconRes: Int? = null
    var viewIconSize: Int = 30

    private val viewExit by lazy {
        val view = BaseImageView()
        view.setImageResource(R.drawable.ic_exit)
        view.visibility = View.GONE
        view
    }

    /*private val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    private val viewIcon by lazy {
        val view = BaseImageView()
        view.visibility = View.GONE
        view
    }

    private val viewArrow by lazy {
        val view = BaseButton()
        view.imageIcon = R.drawable.ic_arrow_back
        view.sizeIcon = Pair(35F, 35F)
        //setImageResource(R.drawable.ic_arrow_back)
        view.visibility = View.GONE
        view.initialize()
        view
    }*/

    fun initialize() {
        setTitle(title)
        setTitleTextColor(textColor)
        if (initModuleUI.isBackPressed) {
            setNavigationIcon(R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                router.onBackPressed()
            }
        }

        initModuleUI.exitListener?.let { exitListener ->
            viewExit.visibility = View.VISIBLE
            viewExit.setOnClickListener(exitListener)
        }
//        textViewTitle.textColor = textColor

        /*initModuleUI.backListener?.let { backListener ->
            viewArrow.visibility = View.VISIBLE
            viewArrow.setOnClickListener(backListener)
        }



        viewIconRes?.let{ iconRes ->
            viewIcon
                .setImageResource(iconRes)

            viewIcon
                .width(viewIconSize)
                .height(viewIconSize)
                .visibility = View.VISIBLE

            initModuleUI.iconListener?.let { iconListener ->
                viewIcon.setOnClickListener(iconListener)
            }
        }

        viewArrow.setPadding(16)*/

        renderUI()

    }

    private fun renderUI() {
        content.subviews(
            viewExit
        )

        viewExit
            .width(25)
            .height(25)
            .constrainRightToRightOf(this, 16)
            .constrainCenterYToCenterYOf(this)

        /*subviews(
            viewArrow,
            textViewTitle,
            viewIcon,
            viewExit
        )

        viewArrow
            .fillVertically()
            .width(35)
            .height(35)
            .constrainLeftToLeftOf(this, 8)

        textViewTitle
            .fillHorizontally()
            .fillVertically()

        viewIcon
            .fillVertically()
            .constrainRightToLeftOf(viewExit,16)

        viewExit
            .fillVertically()
            .width(20)
            .height(20)
            .constrainRightToRightOf(this, 16)*/
    }
}