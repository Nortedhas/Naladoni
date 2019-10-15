package com.ageone.naladoni.Modules.FAQ

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

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