package com.ageone.naladoni.Modules.ChangeName

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ChangeNameViewModel : InterfaceViewModel {
    var model = ChangeNameModel()

    enum class EventType {

        OnlouderChangeName
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeNameModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangeNameModel : InterfaceModel {
    var inputName = ""

}
