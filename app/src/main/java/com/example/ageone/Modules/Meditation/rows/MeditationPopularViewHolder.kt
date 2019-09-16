package com.example.ageone.Modules.Meditation.rows

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.ageone.Application.currentActivity
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.RecyclerView.BaseRecyclerView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.SCAG.Product
import com.example.ageone.UIComponents.ViewHolders.MeditationCardViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class MeditationPopularViewHolder(val constraintLayout: ConstraintLayout): BaseViewHolder(constraintLayout) {

    val recyclerViewHor by lazy {
        val recyclerViewHor = BaseRecyclerView()
        recyclerViewHor
    }

    val viewAdapter by lazy {
        val viewAdapter = Factory()
        viewAdapter
    }

    var onTap: ((Int) -> (Unit))? = null
    var popularMeditation = listOf<Product>()

    init {
        recyclerViewHor.adapter = viewAdapter
        recyclerViewHor.layoutManager = LinearLayoutManager(currentActivity, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewHor.overScrollMode = View.OVER_SCROLL_NEVER

        popularMeditation = utils.realm.product.getAllObjects().filter { meditation ->
            meditation.isPopular
        }

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewHor)

        renderUI()
    }

    inner class Factory: RecyclerView.Adapter<MeditationCardViewHolder>() {

        override fun getItemCount(): Int = popularMeditation.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationCardViewHolder {
            val layout = ConstraintLayout(parent.context)
            layout
                .width(matchParent)
                .height(wrapContent)

            return MeditationCardViewHolder(layout)
        }

        override fun onBindViewHolder(holder: MeditationCardViewHolder, position: Int) {
            val meditation = popularMeditation[position]
            holder.initialize(223 + 20, 135, meditation.image?.preview ?: "",
                meditation.name, meditation.txtInfo, meditation.isFree)
            holder.constraintLayout.setOnClickListener {
                onTap?.invoke(position)
            }
            if (position == 0) {
                holder.imageViewPhoto
                    .constrainLeftToLeftOf(holder.constraintLayout, 16)
                holder.textViewDescribe
                    .constrainLeftToLeftOf(holder.constraintLayout, 16)
                holder.textViewTitle
                    .constrainLeftToLeftOf(holder.constraintLayout, 16)
            }
        }
    }

}

fun MeditationPopularViewHolder.renderUI() {
    constraintLayout.subviews(
        recyclerViewHor
    )

    recyclerViewHor
        .fillHorizontally()
        .constrainTopToTopOf(constraintLayout, 8)
}

fun MeditationPopularViewHolder.initialize() {

}
