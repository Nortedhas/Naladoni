package com.example.ageone.Modules.Meditation.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class MeditationSearchViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {
    val imageViewSearch by lazy {
        val button = BaseImageView()
        button
    }

    init {

        renderUI()
    }

}

fun MeditationSearchViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewSearch
    )

    imageViewSearch
        .constrainTopToTopOf(constraintLayout, 24)
        .constrainLeftToLeftOf(constraintLayout, 16)
}

fun MeditationSearchViewHolder.initialize(imageRes: Int) {

    imageViewSearch.setImageResource(imageRes)
}