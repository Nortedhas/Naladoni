package com.example.ageone.Modules.MainStock

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class MainStockViewModel : InterfaceViewModel {
    var model = MainStockModel()

    enum class EventType {
        OnlouderMainStock
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MainStockModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MainStockModel : InterfaceModel {

}
