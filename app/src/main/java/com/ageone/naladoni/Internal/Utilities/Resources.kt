package com.ageone.naladoni.Internal.Utilities

import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.R

fun getIdCategoryIcon(serialNum: Int): Int = currentActivity?.resources?.getIdentifier(
    "drawable/ic_category_${serialNum}",
    null, currentActivity?.packageName) ?: R.drawable.ic_category_0