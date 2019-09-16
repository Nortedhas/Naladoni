package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class WebViewViewModel : InterfaceViewModel {
    var model = WebViewModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is WebViewModel) {
            model = recievedModel
            completion.invoke()
        }
    }
    enum class EventType {

    }
}

class WebViewModel : InterfaceModel {
    var url = ""
}
