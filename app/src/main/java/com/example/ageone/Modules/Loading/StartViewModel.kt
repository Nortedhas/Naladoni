package com.example.ageone.Modules.Loading

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class StartViewModel: InterfaceViewModel {
    var model = StartModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is StartModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnRegistrationPhonePressed
    }
}

class StartModel: InterfaceModel {

}