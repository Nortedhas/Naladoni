package com.example.ageone.Application.Coordinator.Flow.Regular

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Pleer.PleerView
import com.example.ageone.Modules.PleerModel
import com.example.ageone.Modules.PleerViewModel

fun FlowCoordinator.runFlowPleer(previousFlow: BaseFlow) {

    var flow: FlowPleer? = FlowPleer(previousFlow)

    flow?.let{ flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)
        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()
}

class FlowPleer(previousFlow: BaseFlow? = null): BaseFlow() {

    private var models = FlowPleerModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModulePleer()
    }

    inner class FlowPleerModels {
        var modelPleer = PleerModel()
    }

    fun runModulePleer() {
        val module = PleerView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                router.onBackPressed()
            }
        ))
        module.viewModel.initialize(models.modelPleer) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (PleerViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}