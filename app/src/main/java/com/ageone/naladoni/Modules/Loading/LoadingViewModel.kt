package com.ageone.naladoni.Modules

import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.webSocket
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.Network.HTTP.getCityStocks
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
            user.info.city?.let { city ->
                api.getCityStocks(city.hashId)
            }
            completion.invoke()
        }

    }
}

class LoadingModel : InterfaceModel {

}
