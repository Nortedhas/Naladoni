package com.ageone.naladoni.Modules.Map

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import com.ageone.naladoni.SCAG.Stock

class MapViewModel : InterfaceViewModel {
    var model = MapModel()

    enum class EventType {
        OnClickMap

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    var realmData = listOf<Stock>()
    fun loadRealmData() {
        realmData = utils.realm.stock.getAllObjects()
    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is MapModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class MapModel : InterfaceModel {

}
