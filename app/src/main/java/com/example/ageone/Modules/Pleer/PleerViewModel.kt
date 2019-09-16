package com.example.ageone.Modules

import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import android.content.Intent
import android.os.Handler
import com.example.ageone.Application.*
import com.example.ageone.External.Libraries.Alert.alertManager
import com.example.ageone.External.Libraries.Alert.blockUI
import com.example.ageone.SCAG.Audio
import com.example.ageone.SCAG.Product
import timber.log.Timber


class PleerViewModel : InterfaceViewModel {
    var model = PleerModel()

    fun initialize(recievedModel: InterfaceModel, completion: ()->(Unit)) {
        if (recievedModel is PleerModel) {
            model = recievedModel

            rxData.currentMeditation?.audio?.let { audio ->

                Timber.i("Audio duration: ${rxData.duration}")

            }
            completion.invoke()
        }
    }

    fun startDownload(meditation: Audio) {
        alertManager.blockUI(true)
    }

    enum class EventType {

    }
}

class PleerModel : InterfaceModel {
    lateinit var meditation: Product
    var duration = 0L

}
