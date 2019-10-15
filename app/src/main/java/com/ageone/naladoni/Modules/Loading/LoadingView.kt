package com.ageone.naladoni.Modules

import com.ageone.naladoni.R
import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.router
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.HTTP.update
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.Libraries.Alert.alertManager
import com.ageone.naladoni.External.Libraries.Alert.single
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.SCAG.DataBase
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundResource(R.drawable.base_background)

        innerContent.subviews(
        )

        /*viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.toString())
        }
*/
        Timber.i("Bottom init loading view")

    }

    fun loading(){
        viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.name)
        }

    }



}
