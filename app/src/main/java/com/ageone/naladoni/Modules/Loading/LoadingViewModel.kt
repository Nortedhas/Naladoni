package com.ageone.naladoni.Modules

import com.ageone.naladoni.Application.api
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.Application.webSocket
import com.ageone.naladoni.External.Extensions.Realm.deleteAll
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.Network.HTTP.getCityStocks
import timber.log.Timber

class LoadingViewModel : InterfaceViewModel {
    var model = LoadingModel()

    enum class EventType{
        OnTimerFinishPressed
    }

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    fun startLoading(completion: () -> Unit) {
        user.info.city?.let { city ->
            deleteAll(utils.realm.stock.getAllObjects())
            api.getCityStocks(city.hashId)
        }
        api.requestMainLoad {
            Timber.i("completion invoke")
            webSocket.initialize()
            completion.invoke()
        }

    }
}

class LoadingModel : InterfaceModel {

}
