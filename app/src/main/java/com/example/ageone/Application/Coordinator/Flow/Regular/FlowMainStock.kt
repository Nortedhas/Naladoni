package com.example.ageone.Application.Coordinator.Flow.Regular


import androidx.core.view.size
import com.example.ageone.Application.*
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.MainStock.MainStockModel
import com.example.ageone.Modules.MainStock.MainStockView
import com.example.ageone.Modules.MainStock.MainStockViewModel
import com.example.ageone.Modules.Navigation.NavigationModel
import com.example.ageone.Modules.Navigation.NavigationView
import com.example.ageone.Modules.Navigation.NavigationViewModel
import com.example.ageone.R

fun FlowCoordinator.runFlowMainStock(previousFlow: BaseFlow) {

    var flow: FlowMainStock? = FlowMainStock(previousFlow)

    flow?.let{ flow ->

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

class FlowMainStock(previousFlow: BaseFlow? = null) : BaseFlow() {

    private var models = FlowMainStockModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleMainStock()
    }

    inner class FlowMainStockModels {
        var modelMainStock = MainStockModel()
        var modelNavigation = NavigationModel()
    }

    fun runModuleMainStock() {
        val module = MainStockView(
            InitModuleUI(
                isBottomNavigationVisible = false,

                exitListener = {},

                exitIcon = R.drawable.ic_share,

                isBackPressed = true,

                backListener = { this}
            )
        )

        module.viewModel.initialize(models.modelMainStock) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (MainStockViewModel.EventType.valueOf(event)) {
                MainStockViewModel.EventType.OnlouderMainStock -> {
                    runModuleNavigation()
                }
            }
        }
        push(module)
    }
    fun runModuleNavigation() {
        val module = NavigationView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = { pop() }

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