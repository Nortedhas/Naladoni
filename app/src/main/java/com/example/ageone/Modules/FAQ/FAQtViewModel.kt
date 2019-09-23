package com.example.ageone.Modules.FAQ

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class FAQViewModel: InterfaceViewModel {
    var model = FAQModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is FAQModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType{
        OnLoaded
    }
}

class FAQModel: InterfaceModel {

}