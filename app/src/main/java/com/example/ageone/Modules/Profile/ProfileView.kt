package com.example.ageone.Modules.Profile

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.Profile.rows.*
import yummypets.com.stevia.*

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "Мой Профиль"

        renderToolbar()

        bodyTable.adapter = viewAdapter
//        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER


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

        private val UserInformationType = 0
        private val ProfileInformationType =1
        private val ServiceInformationType = 2
        private val UsedDiscountType = 3

        override fun getItemCount() = 6//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> UserInformationType
            1 -> UsedDiscountType
            2,3 -> ProfileInformationType
            4 -> ServiceInformationType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                UserInformationType -> {
                    UserInformationViewHolder(layout)
                }
                UsedDiscountType -> {
                    UsedDiscountViewHolder(layout)
                }
                ProfileInformationType -> {
                    ProfileInformationViewHolder(layout)
                }
                ServiceInformationType -> {
                    ServiceInformationViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is UserInformationViewHolder -> {
                    holder.initialize("Пупкин Георгий")
                }
                is UsedDiscountViewHolder -> {
                    holder.initialize("24","Количество использованных мною скидок:")
                }
                is ProfileInformationViewHolder -> {
                    holder.initialize("Мой город","Краснодар")
                }
                is ServiceInformationViewHolder -> {
                    holder.initialize("О нашем сервисе")
                }
            }

        }

    }

}

fun ProfileView.renderUIO() {


    renderBodyTable()

    bodyTable
        .constrainTopToTopOf(innerContent, 80)
}


