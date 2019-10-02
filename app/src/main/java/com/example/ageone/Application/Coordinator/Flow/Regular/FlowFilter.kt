package com.example.ageone.Application.Coordinator.Flow.Regular


import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Filter.FilterModel
import com.example.ageone.Modules.Filter.FilterView
import com.example.ageone.Modules.Filter.FilterViewModel
import com.example.ageone.R

fun FlowCoordinator.runFlowFilter() {

    var flow: FlowFilter? =
        FlowFilter()
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

class FlowFilter : BaseFlow() {

    private var models = FlowFilterModels()

    override fun start() {
        onStarted()
        runModuleFiltern()
    }

    inner class FlowFilterModels {
        var modelFiltern = FilterModel()
    }

    fun runModuleFiltern() {
        val module = FilterView(   InitModuleUI(
            isBottomNavigationVisible = false,

            isBackPressed = true,

            backListener = { router.onBackPressed() }
        ))

        module.viewModel.initialize(models.modelFiltern) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (FilterViewModel.EventType.valueOf(event)) {

            }
        }
        push(module)
    }
}