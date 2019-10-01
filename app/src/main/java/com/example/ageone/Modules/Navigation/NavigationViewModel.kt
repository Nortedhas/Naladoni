package com.example.ageone.Modules.Navigation

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class NavigationViewModel : InterfaceViewModel {
    var model = NavigationModel()

    enum class EventType {
        OnlouderNavigation
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is NavigationModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class NavigationModel : InterfaceModel {

}
