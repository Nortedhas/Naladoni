package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.*
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import timber.log.Timber

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    Stack.flows.clear()

    // MARK: in order like in navigation


    Timber.i("Bottom create stack flows")

    runFlowMain()
    runFlowList()
    runFlowSearch()

    Stack.flows[startFlow].start()
}