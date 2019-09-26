package com.example.ageone.Modules.Search.rows

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.View.BaseView
import yummypets.com.stevia.*

class SearchViewViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun SearchViewViewHolder.renderUI() {
    constraintLayout.subviews()
}

fun SearchViewViewHolder.initialize() {

}
