package com.ageone.naladoni.Modules.ChangeCity

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ChangeCityViewModel : InterfaceViewModel {
    var model = ChangeCityModel()

    enum class EventType {
        OnlouderChangeCity
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeCityModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangeCityModel : InterfaceModel {
    var inputCity = ""
}
