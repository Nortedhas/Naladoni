package com.ageone.naladoni.Modules

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class RegistrationSMSViewModel : InterfaceViewModel {
    var model = RegistrationSMSModel()

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is RegistrationSMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        onSityPresed,
        onTimerPresed
    }
}

class RegistrationSMSModel : InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var code = ""
}
