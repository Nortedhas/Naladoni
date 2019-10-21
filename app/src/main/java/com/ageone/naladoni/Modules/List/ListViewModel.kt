package com.ageone.naladoni.Modules.List

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import com.ageone.naladoni.SCAG.Stock

class ListViewModel : InterfaceViewModel {
    var model = ListModel()

    enum class EventType {
        OnlouderList

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
        if (recievedModel is ListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ListModel : InterfaceModel {
}
