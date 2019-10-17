package com.ageone.naladoni.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowFilter
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowMainStock
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack.flows
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Icon
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Map.MapModel
import com.ageone.naladoni.R

fun FlowCoordinator.runFlowMain() {

    var flow: FlowMain? = FlowMain()

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

//    flow?.start()
}

class FlowMain: BaseFlow() {

    private var models = FlowMainModels()

    override fun start() {
        onStarted()
        runModuleMap()
    }

    inner class FlowMainModels {
        var modelMap = MapModel()
    }

    fun runModuleMap() {
        val module = com.ageone.naladoni.Modules.Map.MapView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                firstIcon = Icon(
                    icon = R.drawable.pic_filter,
                    listener = {
                        coordinator.runFlowFilter(this)
                    }
                )
            )
        )
        module.viewModel.initialize(models.modelMap) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.ageone.naladoni.Modules.Map.MapViewModel.EventType.valueOf(event)) {
                com.ageone.naladoni.Modules.Map.MapViewModel.EventType.OnlouderMap -> {
                    coordinator.runFlowMainStock(this)

                }

            }
        }
        push(module)

    }

}