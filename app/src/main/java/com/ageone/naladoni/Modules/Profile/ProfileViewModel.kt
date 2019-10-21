package com.ageone.naladoni.Modules.Profile

import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class ProfileViewModel : InterfaceViewModel {
    var model = ProfileModel()

    enum class EventType {
        OnClickProfileName,
        OnClickProfilePhone,
        OnClickProfileAboutCompany,
        OnClickProfileCity

    }

    /*var realmData = listOf<>()
       fun loadRealmData() {
           realmData = utils.realm.product.getAllObjects()//TODO: change type data!
       }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is ProfileModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class ProfileModel : InterfaceModel {

}
