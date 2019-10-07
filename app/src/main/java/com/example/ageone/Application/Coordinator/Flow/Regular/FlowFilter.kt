package com.example.ageone.Application.Coordinator.Flow.Regular


import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Filter.FilterModel
import com.example.ageone.Modules.Filter.FilterView
import com.example.ageone.Modules.Filter.FilterViewModel
import com.example.ageone.Modules.InnerFilter.InnerFilterModel
import com.example.ageone.Modules.InnerFilter.InnerFilterView
import com.example.ageone.Modules.InnerFilter.InnerFilterViewModel

fun FlowCoordinator.runFlowFilter(previousFlow: BaseFlow) {

    var flow: FlowFilter? = FlowFilter(previousFlow)

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

class FlowFilter (previousFlow: BaseFlow? = null) : BaseFlow()  {

    private var models = FlowFilterModels()

    init {
        this.previousFlow = previousFlow
    }

    override fun start() {
        onStarted()
        runModuleFilter()
    }

    inner class FlowFilterModels {
        var modelFiltern = FilterModel()
        var modelInnerFiltern = InnerFilterModel()
    }

    fun runModuleFilter() {
        val module = FilterView(   InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {

            }
        ))

        module.viewModel.initialize(models.modelFiltern) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (FilterViewModel.EventType.valueOf(event)) {
                FilterViewModel.EventType.OnlouderFilter -> {
                    runModuleInnerFilter()
                }


            }
        }
        push(module)
    }
    fun runModuleInnerFilter() {
        val module = InnerFilterView(   InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,

            backListener = {
                pop() }

        ))

        module.viewModel.initialize(models.modelInnerFiltern) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (InnerFilterViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}