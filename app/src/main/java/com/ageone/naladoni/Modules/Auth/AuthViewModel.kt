package com.ageone.naladoni.Modules.Auth

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class AuthViewModel: InterfaceViewModel {
    var model = AuthModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is AuthModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnRegistrationPressed
    }
}

class AuthModel: InterfaceModel {
    var inputName = ""
    var inputPhone = ""
}