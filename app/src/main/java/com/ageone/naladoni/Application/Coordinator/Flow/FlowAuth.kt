package com.ageone.naladoni.Application.Coordinator.Flow

import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Flow.Regular.runFlowFAQ
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Modules.Auth.AuthRegistrationModel
import com.ageone.naladoni.Modules.Auth.AuthRegistrationView
import com.ageone.naladoni.Modules.Auth.AuthRegistrationViewModel
import com.ageone.naladoni.Modules.City.CityModel
import com.ageone.naladoni.Modules.City.CityView
import com.ageone.naladoni.Modules.City.CityViewModel
import com.ageone.naladoni.Modules.LoadingScreen.LoadingScreenModel
import com.ageone.naladoni.Modules.LoadingScreen.LoadingScreenView
import com.ageone.naladoni.Modules.LoadingScreen.LoadingScreenViewModel
import com.ageone.naladoni.Modules.SMS.SMSView
import com.ageone.naladoni.Modules.SMSModel
import com.ageone.naladoni.Modules.SMSViewModel

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
        var modelStart = LoadingScreenModel()
        var modelRegistration = AuthRegistrationModel()
        var modelRegistrationSMS = SMSModel()
        var modelSelectCity = CityModel()
    }

    fun runModuleStart() {
        val module = LoadingScreenView(InitModuleUI(
            isBottomNavigationVisible = false,
            isToolbarHidden = true
        ))
        module.viewModel.initialize(models.modelStart) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(LoadingScreenViewModel.EventType.valueOf(event)) {
                LoadingScreenViewModel.EventType. OnRegistrationPhonePressed -> {
                    runModuleRegistration()
                }

            }
        }
        push(module)
    }
    fun runModuleRegistration() {
        val module = AuthRegistrationView(InitModuleUI(
            isBottomNavigationVisible = false
        ))
        module.viewModel.initialize(models.modelRegistration) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when(AuthRegistrationViewModel.EventType.valueOf(event)) {
                AuthRegistrationViewModel.EventType.OnRegistrationPressed -> {

                    models.modelRegistrationSMS.inputName = models.modelRegistration.inputName
                    models.modelRegistrationSMS.inputPhone = models.modelRegistration.inputPhone

                    runModuleSMS()
                }
            }
        }
        push(module)
    }

    fun runModuleSMS() {
        val module = SMSView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))
        module.viewModel.initialize(models.modelRegistrationSMS) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (SMSViewModel.EventType.valueOf(event)) {
                SMSViewModel.EventType.onSityPresed -> {
                    models.modelSelectCity.code = models.modelRegistrationSMS.code
                    runModuleCity()
                }
                SMSViewModel.EventType.onTimerPresed -> {
                    runModuleRegistration()

                }
            }
        }
        push(module)
    }


    fun runModuleCity() {
        val module = CityView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))
        module.viewModel.initialize(models.modelSelectCity) { module.reload() }
        settingsCurrentFlow.isBottomNavigationVisible = false
        module.emitEvent = { event ->
            when (CityViewModel.EventType.valueOf(event)) {
                CityViewModel.EventType.onSityPresed -> {
                    coordinator.runFlowFAQ()
                }
            }
        }
        push(module)
    }
}