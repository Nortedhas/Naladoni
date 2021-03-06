package com.ageone.naladoni.Application.Coordinator.Router

import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowNavigation
import com.ageone.naladoni.Application.Coordinator.Flow.Stack.*
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack
import timber.log.Timber

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    Stack.flows.clear()

    // MARK: in order like in navigation


    Timber.i("Bottom create stack flows")

    runFlowMain()
    runFlowList()
    runFlowSearch()
    runFlowProfile()

    Stack.flows[startFlow].start()
}