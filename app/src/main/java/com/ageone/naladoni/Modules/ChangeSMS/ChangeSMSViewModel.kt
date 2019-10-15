package com.ageone.naladoni.Modules.ChangeSMS

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ChangeSMSViewModel : InterfaceViewModel {
    var model = ChangeSMSModel()

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeSMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }

    enum class EventType {
        OnlouderChangeSMS
    }
}

class ChangeSMSModel : InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var code = ""
}
