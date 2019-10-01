package com.example.ageone.Modules.AboutCompany

import com.example.ageone.Application.utils
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel

class AboutCompanyViewModel : InterfaceViewModel {
    var model = AboutCompanyModel()

    enum class EventType {
        OnlouderAboutCompany

    }

    /*var realmData = listOf<>()
    fun loadRealmData() {
        realmData = utils.realm.product.getAllObjects()//TODO: change type data!
    }*/

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is AboutCompanyModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class AboutCompanyModel : InterfaceModel {

}
