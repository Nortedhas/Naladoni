package com.ageone.naladoni.Application.Coordinator.Flow.Regular

import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.mapViewHowGo
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Navigation.NavigationModel
import com.ageone.naladoni.Modules.Navigation.NavigationView
import com.ageone.naladoni.Modules.Navigation.NavigationViewModel
import io.realm.internal.sync.BaseModule

fun FlowCoordinator.runFlowNavigation(previousFlow: BaseFlow) {

    var flow: FlowNavigation? = FlowNavigation(previousFlow)

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.removeAllViews()
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