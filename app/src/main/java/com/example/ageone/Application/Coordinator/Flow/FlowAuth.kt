package com.example.ageone.Application.Coordinator.Flow

import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.coordinator
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Auth.AuthRegistrationModel
import com.example.ageone.Modules.Auth.AuthRegistrationView
import com.example.ageone.Modules.Auth.AuthRegistrationViewModel
import com.example.ageone.Modules.City.CityModel
import com.example.ageone.Modules.City.CityView
import com.example.ageone.Modules.City.CityViewModel
import com.example.ageone.Modules.FAQ.FAQModel
import com.example.ageone.Modules.SMS.SMSView
import com.example.ageone.Modules.RegistrationSMSModel
import com.example.ageone.Modules.RegistrationSMSViewModel
import com.example.ageone.Modules.LoadingScreen.LoadingScreenModel
import com.example.ageone.Modules.LoadingScreen.LoadingScreenView
import com.example.ageone.Modules.LoadingScreen.LoadingScreenViewModel
import com.example.ageone.Modules.Map.MapModel

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
        var modelRegistrationSMS = RegistrationSMSModel()
        var modelSelectCity = CityModel()
        var modelFAQ = FAQModel()
        var modelMap = MapModel()

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

                    runModuleRegistrationSMS()
                }
            }
        }
        push(module)
    }

    fun runModuleRegistrationSMS() {
        val module = SMSView(InitModuleUI(
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
                RegistrationSMSViewModel.EventType.onSityPresed -> {

                  models.modelSelectCity.code = models.modelRegistrationSMS.code

                   runModuleCity()
                }
            }
        }
        push(module)
    }
       fun runModuleCity() {
        val module = CityView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelSelectCity) { module.reload() }
        settingsCurrentFlow.isBottomNavigationVisible = false
        module.emitEvent = { event ->
            when (CityViewModel.EventType.valueOf(event)) {
                CityViewModel.EventType.onSityPresed -> {
                    runModuleFAQ()
                }
            }
        }
        push(module)
    }
    fun runModuleFAQ() {
        val module = com.example.ageone.Modules.FAQ.StartView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isToolbarHidden = true
            )
        )
        module.viewModel.initialize(models.modelFAQ) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.FAQ.FAQViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.FAQ.FAQViewModel.EventType.OnLoaded -> {
                    runModuleMap()
                }

            }
        }
        push(module)

    }
    fun runModuleMap() {
        val module = com.example.ageone.Modules.Map.MapView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                        isToolbarHidden = true
            )
        )
        module.viewModel.initialize(models.modelMap) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.Map.MapViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.Map.MapViewModel.EventType.OnlouderMap -> {
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