package com.ageone.naladoni.Modules.Auth

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class AuthRegistrationViewModel: InterfaceViewModel {
    var model = AuthRegistrationModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is AuthRegistrationModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnRegistrationPressed
    }
}

class AuthRegistrationModel: InterfaceModel {
    var inputName = ""
    var inputPhone = ""
}