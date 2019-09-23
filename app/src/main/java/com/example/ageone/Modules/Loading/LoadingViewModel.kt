package com.example.ageone.Modules

import com.example.ageone.Application.api
import com.example.ageone.Application.webSocket
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
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
        api.requestMainLoad {
            Timber.i("completion invoke")
            webSocket.initialize()
            completion.invoke()
        }
    }
}

class LoadingModel : InterfaceModel {

}
