package com.ageone.naladoni.Modules.Navigation

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class NavigationViewModel : InterfaceViewModel {
    var model = NavigationModel()

    enum class EventType {
        OnClickNavigation
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
