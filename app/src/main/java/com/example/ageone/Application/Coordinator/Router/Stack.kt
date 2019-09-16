package com.example.ageone.Application.Coordinator.Router

import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.Stack.*
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack

fun FlowCoordinator.createStackFlows(startFlow: Int) {
    Stack.flows.clear()

    // MARK: in order like in navigation

    runFlowMain()

    Stack.flows[startFlow].start()
}