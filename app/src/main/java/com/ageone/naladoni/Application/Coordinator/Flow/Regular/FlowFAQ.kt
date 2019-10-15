package com.ageone.naladoni.Application.Coordinator.Flow.Regular


import android.graphics.Color
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Application.*
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Extensions.Activity.clearLightStatusBar
import com.ageone.naladoni.External.Extensions.Activity.setLightStatusBar
import com.ageone.naladoni.Modules.FAQ.FAQModel

fun FlowCoordinator.runFlowFAQ() {

    var flow: FlowFAQ? = FlowFAQ()

    flow?.let { flow ->
        viewFlipperFlow.addView(flow.viewFlipperModule)
        viewFlipperFlow.displayedChild = viewFlipperFlow.indexOfChild(flow.viewFlipperModule)

        flow.settingsCurrentFlow = DataFlow(viewFlipperFlow.size - 1)
        currentActivity?.setLightStatusBar(Color.WHITE, Color.parseColor("#F06F28"))

        Stack.flows.add(flow)
    }

    flow?.onFinish = {
        viewFlipperFlow.removeView(flow?.viewFlipperModule)
        flow?.viewFlipperModule?.removeAllViews()
        flow = null
    }

    flow?.start()


}

class FlowFAQ : BaseFlow() {

    private var models = FlowFAQModels()

    override fun start() {
        onStarted()
        runModuleFAQ()
    }

    inner class FlowFAQModels {
        var modelFAQ = FAQModel()
    }

    fun runModuleFAQ() {
        val module = com.ageone.naladoni.Modules.FAQ.StartView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isToolbarHidden = false,
                text = "Пропустить",
                textColor = Color.BLACK
            )
        )
        module.viewModel.initialize(models.modelFAQ) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (com.ageone.naladoni.Modules.FAQ.FAQViewModel.EventType.valueOf(event)) {
                com.ageone.naladoni.Modules.FAQ.FAQViewModel.EventType.OnLoaded -> {
                    startLoadingFlow()
                    module.onDeInit?.invoke()

                }

            }
        }
        push(module)

    }


    private fun startLoadingFlow() {
        currentActivity?.clearLightStatusBar(Color.TRANSPARENT, Color.TRANSPARENT)
        coordinator.start()
    }
}