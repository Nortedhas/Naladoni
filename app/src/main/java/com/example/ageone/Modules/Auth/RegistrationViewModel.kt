package com.example.ageone.Modules.Auth

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class RegistrationViewModel: InterfaceViewModel {
    var model = RegistrationModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is RegistrationModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnRegistrationPressed
    }
}

class RegistrationModel: InterfaceModel {
    var inputName = ""
    var inputPhone = ""
}