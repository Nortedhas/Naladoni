package com.example.ageone.Modules.LoadingScreen

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class LoadingScreenViewModel: InterfaceViewModel {
    var model = LoadingScreenModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is LoadingScreenModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnRegistrationPhonePressed
    }
}

class LoadingScreenModel: InterfaceModel {

}