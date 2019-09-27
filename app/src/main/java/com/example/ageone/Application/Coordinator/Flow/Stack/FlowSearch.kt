package com.example.ageone.Application.Coordinator.Flow.Stack


import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Application.*
import com.example.ageone.R
import com.example.ageone.Modules.Search.SearchModel
import com.example.ageone.Modules.Search.SearchViewModel

fun FlowCoordinator.runFlowSearch() {

    var flow: FlowSearch? = FlowSearch()

    flow?.let { flow ->
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

//    flow?.start()


}

class FlowSearch : BaseFlow() {

    private var models = FlowSearchModels()

    override fun start() {
        onStarted()
        runModuleSearch()
    }

    inner class FlowSearchModels {
        var modelSearch = SearchModel()
    }

    fun runModuleSearch() {
        val module = com.example.ageone.Modules.Search.SearchView(
            InitModuleUI(
                isBottomNavigationVisible = true
            )
        )
        module.viewModel.initialize(models.modelSearch) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.Search.SearchViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.Search.SearchViewModel.EventType.OnlouderSearch -> {
                }

            }
        }
        push(module)
    }
}