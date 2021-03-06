package com.ageone.naladoni.Modules.City.rows

import android.widget.Spinner

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.subviews


class CitySpinnerViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val spinnerView by lazy {
        val spinnerView = Spinner(currentActivity)

        spinnerView
    }

    init {

        renderUI()
    }

}

fun CitySpinnerViewHolder.renderUI() {

    constraintLayout.subviews(
        spinnerView
    )

    spinnerView
        .constrainTopToTopOf(constraintLayout, 70)
        .fillHorizontally(16)
}

fun CitySpinnerViewHolder.initialize(variants: Array<String>) {

}
