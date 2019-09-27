package com.example.ageone.External

import android.graphics.Color
import android.view.View
import com.example.ageone.Application.Coordinator.Flow.isBottomNavigationExist

data class InitModuleUI (
    var isBottomNavigationVisible: Boolean = isBottomNavigationExist,
    var isToolbarHidden: Boolean = false,
    var isBackPressed: Boolean = false,
    var colorToolbar: Int = Color.TRANSPARENT,
    var backListener: ((View) -> Unit)? = null,
    var exitListener: ((View) -> Unit)? = null,
    var exitIcon: Int? = null,
    var iconListener: ((View) -> Unit)? = null
)