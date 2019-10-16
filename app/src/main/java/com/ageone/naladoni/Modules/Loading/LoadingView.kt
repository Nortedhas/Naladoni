package com.ageone.naladoni.Modules

import com.ageone.naladoni.R
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.InitModuleUI
import timber.log.Timber
import yummypets.com.stevia.subviews

class LoadingView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = LoadingViewModel()

    init {
        setBackgroundResource(R.drawable.base_background)

        innerContent.subviews(
        )

        Timber.i("Bottom init loading view")

    }

    fun loading(){
        viewModel.startLoading {
            emitEvent?.invoke(LoadingViewModel.EventType.onFinish.name)
        }

    }



}
