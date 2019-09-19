package com.example.ageone.Modules.City

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Interfaces.InterfaceModel
import com.example.ageone.External.Interfaces.InterfaceViewModel
import com.example.ageone.Modules.SMS.rows.renderUI
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
