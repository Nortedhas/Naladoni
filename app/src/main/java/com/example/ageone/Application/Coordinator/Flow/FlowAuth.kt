package com.example.ageone.Application.Coordinator.Flow

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Registration.RegistrationModel
import com.example.ageone.Modules.Registration.RegistrationView
import com.example.ageone.Modules.Registration.RegistrationViewModel
import com.example.ageone.Modules.RegistrationSMS.RegistrationSMSView
import com.example.ageone.Modules.RegistrationSMSModel
import com.example.ageone.Modules.RegistrationSMSViewModel
import com.example.ageone.Modules.Start.StartModel
import com.example.ageone.Modules.Start.StartView
import com.example.ageone.Modules.Start.StartViewModel

fun FlowCoordinator.runFlowAuth() {

    var flow: FlowAuth? = FlowAuth()

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

class FlowAuth: BaseFlow() {

    private var models = FlowAuthModels()

    override fun start() {
        onStarted()
        runModuleStart()
    }

    inner class FlowAuthModels {
        var modelStart = StartModel()
        var modelRegistration = RegistrationModel()
        var modelRegistrationSMS = RegistrationSMSModel()
    }

    fun runModuleStart() {
        val module = StartView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(StartViewModel.EventType.valueOf(event)) {
                StartViewModel.EventType.OnRegistrationPhonePressed -> {
                    runModuleRegistration()

                }

            }
        }
        push(module)
    }
    fun runModuleRegistration() {
        val module = RegistrationView(InitModuleUI(
            isBottomNavigationVisible = false
        ))
        module.viewModel.initialize(models.modelRegistration) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when(RegistrationViewModel.EventType.valueOf(event)) {
                RegistrationViewModel.EventType.OnRegistrationPressed -> {

                    models.modelRegistrationSMS.inputName = models.modelRegistration.inputName
                    models.modelRegistrationSMS.inputPhone = models.modelRegistration.inputPhone

                    runModuleRegistrationSMS()
                }
            }
        }
        push(module)
    }

    fun runModuleRegistrationSMS() {
        val module = RegistrationSMSView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelRegistrationSMS) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (RegistrationSMSViewModel.EventType.valueOf(event)) {
                RegistrationSMSViewModel.EventType.OnAcceptPressed -> {
                    module.startLoadingFlow()
                }
            }
        }
        push(module)
    }
    fun BaseModule.startLoadingFlow() {
        coordinator.start()
        onDeInit?.invoke()
    }
}