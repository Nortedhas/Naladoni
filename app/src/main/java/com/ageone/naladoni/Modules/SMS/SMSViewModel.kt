package com.ageone.naladoni.Modules

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class SMSViewModel : InterfaceViewModel {
    var model = SMSModel()

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnAcceptCode,
        onTimerPresed
    }
}

class SMSModel : InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var code = ""
}
