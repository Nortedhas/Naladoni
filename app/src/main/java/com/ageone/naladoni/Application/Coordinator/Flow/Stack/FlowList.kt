package com.ageone.naladoni.Application.Coordinator.Flow.Stack
import androidx.core.view.children
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowFilter
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowMainStock
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Base.Module.Module
import com.ageone.naladoni.External.Icon
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.List.ListModel
import com.ageone.naladoni.Modules.List.ListView
import timber.log.Timber

fun FlowCoordinator.runFlowList() {

    var flow: FlowList? = FlowList()

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
                firstIcon = Icon(
                    icon = R.drawable.ic_filter2,
                    listener = {
                        coordinator.runFlowFilter(this)
                    }
                )
            )
        )
        module.viewModel.initialize(models.modelList) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.ageone.naladoni.Modules.List.ListViewModel.EventType.valueOf(event)) {
                com.ageone.naladoni.Modules.List.ListViewModel.EventType.OnListPressed -> {
                    coordinator.runFlowMainStock(this)
                }

            }
        }
        push(module)
    }
}