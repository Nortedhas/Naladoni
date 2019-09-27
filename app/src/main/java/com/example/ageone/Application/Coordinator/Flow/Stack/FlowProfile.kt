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

import com.example.ageone.Modules.Profile.ProfileModel
import com.example.ageone.Modules.Profile.ProfileView

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

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

class FlowProfile : BaseFlow() {

    private var models = FlowProfileModels()

    override fun start() {
        onStarted()
        runModuleProfile()
    }

    inner class FlowProfileModels {
        var modelProfile = ProfileModel(

        )
    }

    fun runModuleProfile() {
        val module = ProfileView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                exitListener = {
                    router.onBackPressed()
                },
                exitIcon = R.drawable.ic_exit
            )
        )

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.Profile.ProfileViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.Profile.ProfileViewModel.EventType.OnlouderProfile -> {
                }

            }
        }
        push(module)
    }
}