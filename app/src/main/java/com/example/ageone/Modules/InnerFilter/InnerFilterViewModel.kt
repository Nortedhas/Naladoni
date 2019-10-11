package com.example.ageone.Modules.InnerFilter

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class InnerFilterViewModel : InterfaceViewModel {
    var model = InnerFilterModel()

    enum class EventType {
        OnInnerFilterPressed;
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is InnerFilterModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class InnerFilterModel : InterfaceModel {
    var filterName = ""
    var currentFilterIndex = 0
}
