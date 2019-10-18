package com.ageone.naladoni.Application.Coordinator.Flow.Stack


import androidx.core.view.children
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowMainStock
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Base.Module.Module
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.MainStock.MainStockModel
import com.ageone.naladoni.Modules.Search.SearchModel
import timber.log.Timber

fun FlowCoordinator.runFlowSearch() {

    var flow: FlowSearch? = FlowSearch()

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        flow?.viewFlipperModule?.children?.forEachIndexed { index, view ->
            if (view is Module) {
                Timber.i("Delete module in flow finish")
                view.onDeInit?.invoke()
            }
        }

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
        val module = com.ageone.naladoni.Modules.Search.SearchView(
            InitModuleUI(
                isBottomNavigationVisible = true
            )
        )
        module.viewModel.initialize(models.modelSearch) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.ageone.naladoni.Modules.Search.SearchViewModel.EventType.valueOf(event)) {
                com.ageone.naladoni.Modules.Search.SearchViewModel.EventType.OnlouderSearch -> {
                    coordinator.runFlowMainStock(this)
                }

            }
        }
        push(module)
    }
}