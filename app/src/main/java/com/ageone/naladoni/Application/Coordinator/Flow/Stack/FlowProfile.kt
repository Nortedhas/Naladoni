package com.ageone.naladoni.Application.Coordinator.Flow.Stack
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Icon
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.Modules.AboutCompany.AboutCompanyModel
import com.ageone.naladoni.Modules.AboutCompany.AboutCompanyView
import com.ageone.naladoni.Modules.AboutCompany.AboutCompanyViewModel
import com.ageone.naladoni.Modules.ChangeCity.ChangeCityModel
import com.ageone.naladoni.Modules.ChangeCity.ChangeCityView
import com.ageone.naladoni.Modules.ChangeCity.ChangeCityViewModel
import com.ageone.naladoni.Modules.ChangeName.ChangeNameModel
import com.ageone.naladoni.Modules.ChangeName.ChangeNameView
import com.ageone.naladoni.Modules.ChangeName.ChangeNameViewModel
import com.ageone.naladoni.Modules.ChangePhone.ChangePhoneModel
import com.ageone.naladoni.Modules.ChangePhone.ChangePhoneView
import com.ageone.naladoni.Modules.ChangePhone.ChangePhoneViewModel

import com.ageone.naladoni.Modules.Profile.ProfileModel
import com.ageone.naladoni.Modules.Profile.ProfileView
import com.ageone.naladoni.Modules.Profile.ProfileViewModel
import com.ageone.naladoni.R
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
        var modelChangeCity = ChangeCityModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                firstIcon = Icon(
                    icon = R.drawable.ic_exit,
                    listener = {
                        user.isAuthorized = false
                        api.cashTime = 0
                        Realm.getDefaultInstance().executeTransaction { realm ->
                            realm.deleteAll()
                        }
                        coordinator.start()
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnlouderProfileN -> {
                    runModuleChangeName()
                }

                ProfileViewModel.EventType.OnlouderProfileP -> {
                    runModuleChangePhone()
                }

                ProfileViewModel.EventType.OnlouderProfileA -> {
                    runModuleAboutCompany()
                }

                ProfileViewModel.EventType.OnlouderProfileC -> {
                    runModuleChangeCity()
                }

            }
        }
        push(module)
    }


    fun runModuleChangeName() {
        val module = ChangeNameView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
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
            isBackPressed = true
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
    fun runModuleChangeCity() {
        val module = ChangeCityView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
        ))
        module.viewModel.initialize(models.modelChangeCity) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangeCityViewModel.EventType.valueOf(event)) {
                ChangeCityViewModel.EventType.OnlouderChangeCity -> {

                    runModuleProfile()
                }
            }
        }
        push(module)
    }

    fun runModuleAboutCompany() {
        val module = AboutCompanyView(InitModuleUI(
            isBottomNavigationVisible = false,
            isBackPressed = true
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