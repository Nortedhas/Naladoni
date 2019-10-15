package com.ageone.naladoni.Modules.Filter

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class FilterViewModel : InterfaceViewModel {
    var model = FilterModel()

    enum class EventType {
        OnInnerFilterPressed
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is FilterModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class FilterModel : InterfaceModel {
    var filterName = ""
    var currentFilterIndex = 0
}
