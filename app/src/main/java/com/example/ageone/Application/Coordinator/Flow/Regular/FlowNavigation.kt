package com.example.ageone.Application.Coordinator.Flow.Regular

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Navigation.NavigationModel
import com.example.ageone.Modules.Navigation.NavigationView
import com.example.ageone.Modules.Navigation.NavigationViewModel

fun FlowCoordinator.runFlowNavigation(previousFlow: BaseFlow) {

    var flow: FlowNavigation? = FlowNavigation(previousFlow)

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()


}

class FlowNavigation(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowNavigationModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleNavigation()
    }

    inner class FlowNavigationModels {
        var modelNavigation = NavigationModel()
    }

    fun runModuleNavigation() {
        val module = NavigationView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true

        ))
        module.viewModel.initialize(models.modelNavigation) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (NavigationViewModel.EventType.valueOf(event)) {
                NavigationViewModel.EventType.OnlouderNavigation -> {
                }
            }
        }
        push(module)
    }
}