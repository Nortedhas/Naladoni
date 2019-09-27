package com.example.ageone.Application.Coordinator.Flow.Stack

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack.flows
import com.example.ageone.Application.R
import com.example.ageone.Application.coordinator
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Map.MapModel
import timber.log.Timber

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
        val module = com.example.ageone.Modules.Map.MapView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                exitListener = {
                    runModuleMap()
                },
                exitIcon = R.drawable.pic_filter
            )
        )
        module.viewModel.initialize(models.modelMap) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.Map.MapViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.Map.MapViewModel.EventType.OnlouderMap -> {
//                     module.runFlowList()
                }

            }
        }
        push(module)

    }

}