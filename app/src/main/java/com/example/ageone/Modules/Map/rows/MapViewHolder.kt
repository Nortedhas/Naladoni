package com.example.ageone.Modules.Map.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class MapViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun MapViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun MapViewHolder.initialize() {

}
