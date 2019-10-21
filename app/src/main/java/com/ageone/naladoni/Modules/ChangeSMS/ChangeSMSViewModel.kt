package com.ageone.naladoni.Modules.ChangeSMS

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ChangeSMSViewModel : InterfaceViewModel {
    var model = ChangeSMSModel()

    enum class EventType {
        OnClickChangeSMS
    }

    /*var realmData = listOf<>()
      fun loadRealmData() {
          realmData = utils.realm.product.getAllObjects()//TODO: change type data!
      }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ChangeSMSModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ChangeSMSModel : InterfaceModel {
    var inputName = ""
    var inputPhone = ""
    var code = ""
}
