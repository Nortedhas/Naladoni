package com.ageone.naladoni.External.Base.Module

import android.view.View
import com.ageone.naladoni.External.InitModuleUI

interface Module {
    val idView: Int
    var initModuleUI: InitModuleUI

    var onDeInit: (() -> Unit)?
    var emitEvent: ((String) -> Unit)?

    fun className(): String
    fun getView(): View
}