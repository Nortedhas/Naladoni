package com.ageone.naladoni.Modules.InnerFilter

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

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
