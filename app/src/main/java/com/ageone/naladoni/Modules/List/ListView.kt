package com.ageone.naladoni.Modules.List

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.ageone.naladoni.R
import com.ageone.naladoni.Application.currentActivity
import com.ageone.naladoni.Application.rxData
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.RecyclerView.ColumnEqualsPaddingItemDecoration
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.UIComponents.ViewHolders.СardViewHolder
import com.ageone.naladoni.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class ListView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ListViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    val layoutManager by lazy {
        val layoutManager = GridLayoutManager(currentActivity, 2)
        layoutManager
    }

    init {
        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)

        toolbar.title = "Подарки города"
        renderToolbar()
        bodyTable.layoutManager = layoutManager
        bodyTable.adapter = viewAdapter

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        /*compositeDisposable.add(
          RxBus.listen(RxEvent.Event::class.java).subscribe {//TODO: change type event
              bodyTable.adapter?.notifyDataSetChanged()
          }
      )*/
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ListСardType = 0

        override fun getItemCount() = viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = ListСardType

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                ListСardType -> {
                    СardViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }


        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is СardViewHolder -> {
                    val stock = viewModel.realmData[position]

                    holder.initialize(
                        stock.shortAbout,
                        stock.name,
                        stock.avaliableTo,
                        stock.image?.preview ?: ""
                    )

                    holder.viewCard.setOnClickListener {
                        rxData.currentStock = stock

                        rootModule.emitEvent?.invoke(ListViewModel.EventType.OnListPressed.name)

                    }
                }

            }

        }

    }

}

fun ListView.renderUIO() {

    innerContent.subviews(
        bodyTable
    )

    bodyTable
        .fillHorizontally(0)
        .fillVertically()
        .constrainTopToTopOf(innerContent)
        .updatePadding(bottom = 24.dp)

    bodyTable
        .clipToPadding = false


    bodyTable.addItemDecoration(ColumnEqualsPaddingItemDecoration(8.dp, 2))
}


