package com.ageone.naladoni.Modules.City.rows
import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updateMargins
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.dp
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

//            spinnerView.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, variants )
//        spinnerView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {}
//
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}
//        }

}
