package com.ageone.naladoni.Modules.Auth

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class AuthViewModel: InterfaceViewModel {
    var model = AuthModel()

    enum class EventType{
        OnAuthPressed
    }

    /*var realmData = listOf<>()
      fun loadRealmData() {
          realmData = utils.realm.product.getAllObjects()//TODO: change type data!
      }*/

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is AuthModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class AuthModel: InterfaceModel {
    var inputName = ""
    var inputPhone = ""
}