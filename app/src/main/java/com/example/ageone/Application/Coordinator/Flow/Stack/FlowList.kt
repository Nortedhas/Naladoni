package com.example.ageone.Application.Coordinator.Flow.Stack
import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.R
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.List.ListModel
import com.example.ageone.Modules.List.ListView

fun FlowCoordinator.runFlowList() {

    var flow: FlowList? = FlowList()

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

class FlowList : BaseFlow() {

    private var models = FlowListModels()

    override fun start() {
        onStarted()
        runModuleList()
    }

    inner class FlowListModels {
        var modelList = ListModel()
    }

    fun runModuleList() {
        val module = ListView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                exitListener = {
                    runModuleList()
                },
                exitIcon = R.drawable.ic_filter2
            )
        )
        module.viewModel.initialize(models.modelList) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.List.ListViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.List.ListViewModel.EventType.OnlouderList -> {
                }

            }
        }
        push(module)
    }
}