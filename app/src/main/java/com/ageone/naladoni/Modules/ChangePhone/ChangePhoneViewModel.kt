package com.ageone.naladoni.Modules.ChangePhone

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ChangePhoneViewModel : InterfaceViewModel {
    var model = ChangePhoneModel()

    enum class EventType {
        OnlouderChangePhone
    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangePhoneModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangePhoneModel : InterfaceModel {
    var inputPhone = ""
}
