package com.ageone.naladoni.Modules.Search

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class SearchViewModel : InterfaceViewModel {
    var model = SearchModel()

    enum class EventType {
        OnlouderSearch
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SearchModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class SearchModel : InterfaceModel {

}
