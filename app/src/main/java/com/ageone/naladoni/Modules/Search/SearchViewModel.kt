package com.ageone.naladoni.Modules.Search

import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import com.ageone.naladoni.SCAG.Stock

class SearchViewModel : InterfaceViewModel {
    var model = SearchModel()

    enum class EventType {
        OnClickSearch
    }

    /*var realmData = listOf<>()
       fun loadRealmData() {
           realmData = utils.realm.product.getAllObjects()//TODO: change type data!
       }*/

    var realmData = listOf<Stock>()
    fun loadRealmData(filter: String) {
        realmData = if (rxData.isSetFilter) {
            rxData.filteredStocks
        } else {
            emptyList()
        }

        if (filter.isNotBlank()) {
            realmData = utils.realm.stock.getAllObjects().filter { stock ->
                stock.name.contains(filter, true) ||
                        stock.shortAbout.contains(filter, true) ||
                        stock.longAbout.contains(filter, true)
            }
        }

    }

    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is SearchModel) {
            model = recievedModel
            completion.invoke()
        }
    }
}

class SearchModel : InterfaceModel {

}
