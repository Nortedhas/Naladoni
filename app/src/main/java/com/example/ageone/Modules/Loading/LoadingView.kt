package com.example.ageone.Modules

import com.example.ageone.Application.R
import com.example.ageone.Application.api
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.router
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.HTTP.update
import com.example.ageone.External.InitModuleUI
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.single
import com.example.ageone.Models.User.user
import com.example.ageone.SCAG.DataBase
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundResource(R.drawable.base_background)

        innerContent.subviews(
        )

        viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.toString())
        }
    }

}
