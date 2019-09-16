package com.example.ageone.Application.Coordinator.Flow

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.TabBar
import com.example.ageone.Application.Coordinator.Router.createStackFlows
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.LoadingModel
import com.example.ageone.Modules.LoadingView
import com.example.ageone.Modules.LoadingViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowLoading() {

    var flow: FlowLoading? = FlowLoading()

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()

        // MARK: first appear flow in bottom bar

        val startFlow = 0
        createStackFlows(startFlow)
        TabBar.createBottomNavigation()
        TabBar.bottomNavigation.currentItem = startFlow
        viewFlipperFlow.displayedChild = startFlow

        flow = null
    }

    flow?.start()

}

class FlowLoading: BaseFlow() {

    private var models = FlowLoadingModels()

    override fun start() {
        onStarted()
        runModuleLoading()
    }

    inner class FlowLoadingModels {
        var modelLoading = LoadingModel()
    }

    //main load, parsing, socket

    fun runModuleLoading() {
        val module = LoadingView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelLoading) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(LoadingViewModel.EventType.valueOf(event)) {
                LoadingViewModel.EventType.onFinish -> {
                    module.startMainFlow()
                }
            }
        }
        push(module)
    }

    fun BaseModule.startMainFlow() {
        Timber.i("Start main load")
//        coordinator.start()
        onFinish?.invoke()
    }
}