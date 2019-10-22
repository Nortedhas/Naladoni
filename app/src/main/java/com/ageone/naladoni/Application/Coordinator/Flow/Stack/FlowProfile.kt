package com.ageone.naladoni.Application.Coordinator.Flow.Stack

import androidx.core.view.children
import androidx.core.view.size
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator
import com.ageone.naladoni.Application.Coordinator.Flow.FlowCoordinator.ViewFlipperFlowObject.viewFlipperFlow
import com.ageone.naladoni.Application.Coordinator.Router.DataFlow
import com.ageone.naladoni.Application.Coordinator.Router.TabBar.Stack
import com.ageone.naladoni.Application.coordinator
import com.ageone.naladoni.Application.router
import com.ageone.naladoni.External.Base.Flow.BaseFlow
import com.ageone.naladoni.External.Base.Module.Module
import com.ageone.naladoni.External.Extensions.FlowCoordinator.logout
import com.ageone.naladoni.External.Icon
import com.ageone.naladoni.External.InitModuleUI
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
import com.ageone.naladoni.Modules.ChangeSMS.ChangeSMSModel
import com.ageone.naladoni.Modules.ChangeSMS.ChangeSMSView
import com.ageone.naladoni.Modules.ChangeSMS.ChangeSMSViewModel

import com.ageone.naladoni.Modules.Profile.ProfileModel
import com.ageone.naladoni.Modules.Profile.ProfileView
import com.ageone.naladoni.Modules.Profile.ProfileViewModel
import com.ageone.naladoni.R
import timber.log.Timber

fun FlowCoordinator.runFlowProfile() {

    var flow: FlowProfile? = FlowProfile()

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
        var modelChangeSMS = ChangeSMSModel()
    }

    fun runModuleProfile() {
        val module = ProfileView(
            InitModuleUI(
                isBottomNavigationVisible = true,
                firstIcon = Icon(
                    icon = R.drawable.ic_exit,
                    listener = {
                        coordinator.logout()
                    }
                )
            )
        )

        module.viewModel.initialize(models.modelProfile) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = true

        module.emitEvent = { event ->
            when (ProfileViewModel.EventType.valueOf(event)) {
                ProfileViewModel.EventType.OnClickProfileName -> {
                    runModuleChangeName()
                }

                ProfileViewModel.EventType.OnClickProfilePhone -> {
                    runModuleChangePhone()
                }

                ProfileViewModel.EventType.OnClickProfileAboutCompany -> {
                    runModuleAboutCompany()
                }

                ProfileViewModel.EventType.OnClickProfileCity -> {
                    runModuleChangeCity()
                }

            }
        }
        push(module)
    }


    fun runModuleChangeName() {
        val module = ChangeNameView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )
        module.viewModel.initialize(models.modelChangeName) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangeNameViewModel.EventType.valueOf(event)) {
                ChangeNameViewModel.EventType.OnClickChangeName -> {
                    router.onBackPressed()
                }
            }
        }
        push(module)
    }

    fun runModuleChangePhone() {
        val module = ChangePhoneView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )
        module.viewModel.initialize(models.modelChangePhone) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangePhoneViewModel.EventType.valueOf(event)) {
                ChangePhoneViewModel.EventType.OnClickChangePhone -> {
                    models.modelChangeSMS.inputPhone = models.modelChangePhone.inputPhone
                    runModuleChangeSMS()
                }
            }
        }
        push(module)
    }

    fun runModuleChangeSMS() {
        val module = ChangeSMSView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )
        module.viewModel.initialize(models.modelChangeSMS) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangeSMSViewModel.EventType.valueOf(event)) {
                ChangeSMSViewModel.EventType.OnClickChangeSMS -> {
                    router.onBackPressed()
                    router.onBackPressed()
                }
            }
        }
        push(module)
    }

    fun runModuleChangeCity() {
        val module = ChangeCityView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )
        module.viewModel.initialize(models.modelChangeCity) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (ChangeCityViewModel.EventType.valueOf(event)) {
                ChangeCityViewModel.EventType.OnClickChangeCity -> {
                    router.onBackPressed()
                }
            }
            push(module)
        }
    }

    fun runModuleAboutCompany() {
        val module = AboutCompanyView(
            InitModuleUI(
                isBottomNavigationVisible = false,
                isBackPressed = true
            )
        )
        module.viewModel.initialize(models.modelAboutCompany) { module.reload() }

        settingsCurrentFlow.isBottomNavigationVisible = false


        module.emitEvent = { event ->
            when (AboutCompanyViewModel.EventType.valueOf(event)) {
                AboutCompanyViewModel.EventType.OnClickAboutCompany -> {

                    runModuleProfile()
                }
            }
        }
        push(module)
    }

}