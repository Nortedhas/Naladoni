package com.ageone.naladoni.Modules

import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.webSocket
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import timber.log.Timber

class LoadingViewModel : InterfaceViewModel {
    var model = LoadingModel()

    enum class EventType{
        onFinish
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun startLoading(completion: () -> Unit) {
        /*api.requestMainLoad {
            Timber.i("completion invoke")
            webSocket.initialize()
            completion.invoke()
        }*/

        completion.invoke()
    }
}

class LoadingModel : InterfaceModel {

}
