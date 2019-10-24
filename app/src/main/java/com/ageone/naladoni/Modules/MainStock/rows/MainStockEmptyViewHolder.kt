package com.ageone.naladoni.Modules.MainStock.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class MainStockEmptyViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun MainStockEmptyViewHolder.renderUI() {
    constraintLayout.subviews(

    )
    constraintLayout.fillHorizontally()
    constraintLayout.backgroundColor = Color.WHITE
}

fun MainStockEmptyViewHolder.initialize(heitght: Int) {
    constraintLayout.height(heitght)
}
