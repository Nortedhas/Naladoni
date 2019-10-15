package com.ageone.naladoni.Modules.City

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.ageone.naladoni.Application.utils
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import com.ageone.naladoni.External.Interfaces.InterfaceModel
import com.ageone.naladoni.External.Interfaces.InterfaceViewModel
import com.ageone.naladoni.Modules.SMS.rows.renderUI
import yummypets.com.stevia.textColor

class CityViewModel : InterfaceViewModel {
    var model = CityModel()


    fun initialize(recievedModel: InterfaceModel, completion: () -> (Unit)) {
        if (recievedModel is CityModel) {
            model = recievedModel
            completion.invoke()
        }
    }
    enum class EventType{
        onSityPresed
    }
}

class CityModel : InterfaceModel {
    var code = ""
}
