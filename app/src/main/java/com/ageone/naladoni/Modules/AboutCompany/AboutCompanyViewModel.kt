package com.ageone.naladoni.Modules.AboutCompany

import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel

class AboutCompanyViewModel : InterfaceViewModel {
    var model = AboutCompanyModel()

    enum class EventType {
        OnClickAboutCompany

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
