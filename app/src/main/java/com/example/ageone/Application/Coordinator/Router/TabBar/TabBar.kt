package com.example.ageone.Application.Coordinator.Router.TabBar

import android.graphics.Color
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.currentFlow
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Flow.isBottomNavigationExist
import com.example.ageone.Application.Coordinator.Flow.setStatusBarColor
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.items
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Flow.BaseFlow

object Stack {
    var flows = arrayListOf<BaseFlow>()
    var items = arrayListOf<AHBottomNavigationItem>()
}

object TabBar {

    fun createBottomNavigation() {
        isBottomNavigationExist = true

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            if (!wasSelected && position < flows.size) {
                viewFlipperFlow.displayedChild = position

                //if flow starts in the first time
                if (!flows[position].isStarted) {
                    flows[position].start()
                }

                //correct image current module
                currentFlow = flows[position]
                setStatusBarColor(flows[position].colorStatusBar)
            }
            true
        }

        createStackItem()
        setUpTabs()
    }

    val bottomNavigation by lazy {
        val bottomNavigation = AHBottomNavigation(currentActivity)
        bottomNavigation.setTitleTextSize(30f,30f)
        bottomNavigation.defaultBackgroundColor = Color.parseColor("#FEFEFE")
        bottomNavigation.isBehaviorTranslationEnabled = true
        bottomNavigation.accentColor = Color.rgb(0x70,0x7A,0xBA)
        bottomNavigation.inactiveColor = Color.GRAY
        bottomNavigation.isForceTint = true
        bottomNavigation.isTranslucentNavigationEnabled = false
        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation
    }

    private fun setUpTabs() {

        bottomNavigation.removeAllItems()
        for (item in items) {
            bottomNavigation.addItem(item)
        }

    }

    private fun createStackItem() {
        items = arrayListOf(
            AHBottomNavigationItem("Главная", R.drawable.home),
            AHBottomNavigationItem("Сеты", R.drawable.sets),
            AHBottomNavigationItem("Анонсы", R.drawable.anons),
            AHBottomNavigationItem("Покупки", R.drawable.buy),
            AHBottomNavigationItem("Профиль", R.drawable.profile)
        )

    }
}

