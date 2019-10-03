package com.example.ageone.Application.Coordinator.Flow

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ProgressBar
import com.example.ageone.Application.AppActivity
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar.bottomNavigation
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.External.Base.ConstraintLayout.BaseConstraintLayout
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.ViewFlipper.BaseViewFlipper
import com.example.ageone.External.Extensions.Activity.setStatusBarColor
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import timber.log.Timber
import yummypets.com.stevia.*

var isBottomNavigationExist = true

class FlowCoordinator {
    fun setLaunchScreen() {

        router.initialize()
        renderUI()

        val launch = object: BaseModule(InitModuleUI(colorToolbar = Color.TRANSPARENT)){
        }
        launch.setBackgroundColor(Color.TRANSPARENT)

        viewFlipperFlow.subviews(
            launch
        )

        launch.toolbar
            .height(0)

    }

    fun start() {
        viewFlipperFlow.removeAllViews()
        when (LaunchInstructor.configure()) {
            LaunchInstructor.Main -> {
                Timber.i("Bottom run flow loading")
                runFlowLoading()
            }
            LaunchInstructor.Auth -> {
                runFlowAuth()
            }
        }
    }

    private fun renderUI() {
//        viewFlipperFlow = BaseViewFlipper()

        router.layout.removeAllViews()
        router.layout.subviews(
            viewFlipperFlow,
            bottomNavigation,
            blockConstraint
        )

        bottomNavigation.constrainBottomToBottomOf(router.layout)

        viewFlipperFlow
            .fillVertically()
            .fillHorizontally()
            .constrainBottomToTopOf(bottomNavigation)

        blockConstraint
            .fillVertically()
            .fillHorizontally()

        blockConstraint.removeAllViews()
        blockConstraint.subviews(
            circularProgress
        )

        circularProgress.centerInParent()

        blockConstraint.visibility = View.GONE
        circularProgress.visibility = View.GONE
    }

    val blockConstraint by lazy {
        val constraint = BaseConstraintLayout()
        constraint.setBackgroundColor(Color.argb(180, 0,0,0))
        constraint
    }

    val circularProgress by lazy {
        val circular = ProgressBar(currentActivity as Context)
        circular
    }

    object ViewFlipperFlowObject{
        var currentFlow: BaseFlow? = null

        val viewFlipperFlow: BaseViewFlipper by lazy {
            val flipper = BaseViewFlipper()
            flipper
        }

    }

}

fun setBottomNavigationVisible(visible: Boolean) = if (visible) {
    bottomNavigation.visibility = View.VISIBLE

} else {
    bottomNavigation.visibility = View.GONE

}

fun setStatusBarColor(color: Int) {
    currentActivity?.setStatusBarColor(color)
}

private enum class LaunchInstructor {

    Main, Auth;

    companion object {

        fun configure(isAutorized: Boolean = user.isAuthorized): LaunchInstructor {
            return when (isAutorized) {
                true -> Main
                false -> Auth
            }

        }

    }
}

