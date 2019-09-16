package com.example.ageone.Modules.Start

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.SetsInModel

class StartViewModel: InterfaceViewModel {
    var model = StartModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is StartModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnLoaded
    }
}

class StartModel: InterfaceModel {

}