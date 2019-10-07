package com.example.ageone.Modules.Filter

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class FilterViewModel : InterfaceViewModel {
    var model = FilterModel()

    enum class EventType {
        OnlouderFilter
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

}
