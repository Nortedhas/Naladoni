package com.ageone.naladoni.Application.Coordinator.Flow.Regular


import android.graphics.Color
import androidx.core.view.children
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.TabBar
import com.ageone.naladoni.Application.Coordinator.Router.createStackFlows
import com.ageone.naladoni.Application.router
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Base.Module.Module
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Filter.FilterModel
import com.ageone.naladoni.Modules.Filter.FilterView
import com.ageone.naladoni.Modules.Filter.FilterViewModel
import com.ageone.naladoni.Modules.InnerFilter.InnerFilterModel
import com.ageone.naladoni.Modules.InnerFilter.InnerFilterView
import com.ageone.naladoni.Modules.InnerFilter.InnerFilterViewModel
import timber.log.Timber

fun FlowCoordinator.runFlowFilter(previousFlow: BaseFlow) {

    var flow: FlowFilter? = FlowFilter(previousFlow)

    flow?.let{ flow ->

        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)

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
        var modelFilter = FilterModel()
        var modelInnerFilter = InnerFilterModel()
    }

    fun runModuleFilter() {
        val module = FilterView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            text = "Очистить",
            textListener = {
                rxData.filterCategory = -1
            }
        ))

        module.viewModel.initialize(models.modelFilter) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (FilterViewModel.EventType.valueOf(event)) {
                FilterViewModel.EventType.OnFilterPressed -> {
                    models.modelInnerFilter.filterName = models.modelFilter.filterName
                    models.modelInnerFilter.currentFilterIndex = models.modelFilter.currentFilterIndex
                    runModuleInnerFilter()
                }

                FilterViewModel.EventType.OnSearch -> {
                    router.onBackPressed()
                    TabBar.bottomNavigation.setCurrentItem(2, true)
                }
            }
        }
        push(module)
    }


    fun runModuleInnerFilter() {
        val module = InnerFilterView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            colorToolbar = Color.parseColor("#F27A25")
        ))

        module.viewModel.initialize(models.modelInnerFilter) {
            module.reload()
        }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (InnerFilterViewModel.EventType.valueOf(event)) {
                InnerFilterViewModel.EventType.OnInnerFilterPressed -> {
                    router.onBackPressed()
                }
            }
        }
        push(module)
    }
}