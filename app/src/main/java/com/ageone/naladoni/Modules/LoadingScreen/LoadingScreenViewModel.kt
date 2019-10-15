package com.ageone.naladoni.Modules.LoadingScreen

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class LoadingScreenViewModel : InterfaceViewModel {
    var model = LoadingScreenModel()

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is LoadingScreenModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnRegistrationPhonePressed
    }
}

class LoadingScreenModel : InterfaceModel {

}