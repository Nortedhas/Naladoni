package com.example.ageone.Modules.ChangePhone

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

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
