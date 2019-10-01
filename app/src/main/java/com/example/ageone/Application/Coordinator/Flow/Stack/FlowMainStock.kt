package com.example.ageone.Application.Coordinator.Flow.Stack


import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.MainStock.MainStockModel
import com.example.ageone.Modules.MainStock.MainStockView
import com.example.ageone.R

fun FlowCoordinator.runFlowMainStock() {

    var flow: FlowMainStock? =
        FlowMainStock()

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

class FlowMainStock : BaseFlow() {

    private var models = FlowMainStockModels()

    override fun start() {
        onStarted()
        runModuleMainStock()
    }

    inner class FlowMainStockModels {
        var modelMainStock = MainStockModel()
    }

    fun runModuleMainStock() {
        val module = MainStockView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                exitListener = {
                    runModuleMainStock()
                },
                exitIcon = R.drawable.pic_filter,
                isBackPressed = true,
                backListener = {
                    pop()
                }
            )
        )
        module.viewModel.initialize(models.modelMainStock) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.MainStock.MainStockViewModel.EventType.valueOf(
                event
            )) {
                com.example.ageone.Modules.MainStock.MainStockViewModel.EventType.OnlouderMainStock -> {
                }

            }
        }
        push(module)
    }
}
