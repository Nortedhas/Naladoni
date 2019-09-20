package com.example.ageone.Modules.Map

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class MapViewModel : InterfaceViewModel {
    var model = MapModel()

    enum class EventType {
        OnlouderMap

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MapModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MapModel : InterfaceModel {

}
