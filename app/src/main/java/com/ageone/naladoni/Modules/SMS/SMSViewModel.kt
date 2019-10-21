package com.ageone.naladoni.Modules

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class SMSViewModel : InterfaceViewModel {
    var model = SMSModel()

    enum class EventType {
        OnAcceptCode,
        OnTimerPressed
    }
    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class SMSModel : InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var code = ""
}
