package com.example.ageone.Application.Coordinator.Flow.Stack
import androidx.core.view.size
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator
import com.example.ageone.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.example.ageone.Application.Coordinator.Router.DataFlow
import com.example.ageone.Application.Coordinator.Router.TabBar.Stack
import com.example.ageone.Application.api
import com.example.ageone.R
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Flow.BaseFlow
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Models.User.user
import com.example.ageone.Modules.AboutCompany.AboutCompanyModel
import com.example.ageone.Modules.AboutCompany.AboutCompanyView
import com.example.ageone.Modules.AboutCompany.AboutCompanyViewModel
import com.example.ageone.Modules.Auth.AuthRegistrationView
import com.example.ageone.Modules.Auth.AuthRegistrationViewModel
import com.example.ageone.Modules.ChangeName.ChangeNameModel
import com.example.ageone.Modules.ChangeName.ChangeNameView
import com.example.ageone.Modules.ChangeName.ChangeNameViewModel
import com.example.ageone.Modules.ChangePhone.ChangePhoneModel
import com.example.ageone.Modules.ChangePhone.ChangePhoneView
import com.example.ageone.Modules.ChangePhone.ChangePhoneViewModel
import com.example.ageone.Modules.Navigation.NavigationModel
import com.example.ageone.Modules.Navigation.NavigationView
import com.example.ageone.Modules.Navigation.NavigationViewModel

import com.example.ageone.Modules.Profile.ProfileModel
import com.example.ageone.Modules.Profile.ProfileView
import io.realm.Realm

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
        var modelProfile = ProfileModel()
        var modelChangeName = ChangeNameModel()
        var modelChangePhone = ChangePhoneModel()
        var modelAboutCompany = AboutCompanyModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                exitListener = {
                    router.onBackPressed()
                }
            )
        )

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (com.example.ageone.Modules.Profile.ProfileViewModel.EventType.valueOf(event)) {
                com.example.ageone.Modules.Profile.ProfileViewModel.EventType.OnlouderProfileN -> {

                    runModuleChangeName()

                }

                com.example.ageone.Modules.Profile.ProfileViewModel.EventType.OnlouderProfileP -> {

                    runModuleChangePhone()
                }

                com.example.ageone.Modules.Profile.ProfileViewModel.EventType.OnlouderProfileA -> {
//                    runModuleNavigation()
                    runModuleAboutCompany()

                }

            }
        }
        push(module)
    }


    fun runModuleChangeName() {
        val module = ChangeNameView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelChangeName) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangeNameViewModel.EventType.valueOf(event)) {
                ChangeNameViewModel.EventType.OnlouderChangeName -> {

                    runModuleProfile()
                }
            }
        }
        push(module)
    }

    fun runModuleChangePhone() {
        val module = ChangePhoneView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelChangePhone) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangePhoneViewModel.EventType.valueOf(event)) {
                ChangePhoneViewModel.EventType.OnlouderChangePhone -> {

                    runModuleProfile()
                }
            }
        }
        push(module)
    }

    fun runModuleAboutCompany() {
        val module = AboutCompanyView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true,
            backListener = {
                pop()
            }
        ))
        module.viewModel.initialize(models.modelAboutCompany) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (AboutCompanyViewModel.EventType.valueOf(event)) {
                AboutCompanyViewModel.EventType.OnlouderAboutCompany -> {

                    runModuleProfile()
                }
            }
        }
        push(module)
    }
}