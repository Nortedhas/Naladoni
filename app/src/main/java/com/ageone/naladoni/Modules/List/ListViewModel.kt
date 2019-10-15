package com.ageone.naladoni.Modules.List

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ListViewModel : InterfaceViewModel {
    var model = ListModel()

    enum class EventType {
        OnlouderList

    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ListModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ListModel : InterfaceModel {
}
