package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class RegistrationSMSViewModel : InterfaceViewModel {
    var model = RegistrationSMSModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is RegistrationSMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnAcceptPressed
    }
}

class RegistrationSMSModel : InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var inputMail = ""
    var code = ""
}
