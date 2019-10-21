package com.ageone.naladoni.Modules.FAQ

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class FAQViewModel: InterfaceViewModel {
    var model = FAQModel()

    enum class EventType{
        OnLoaded
    }

    /*var realmData = listOf<>()
       fun loadRealmData() {
           realmData = utils.realm.product.getAllObjects()//TODO: change type data!
       }*/

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is FAQModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class FAQModel: InterfaceModel {

}