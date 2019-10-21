package com.ageone.naladoni.Modules.Profile

import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.Module.BaseModule
import com.ageone.naladoni.External.Base.RecyclerView.BaseAdapter
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.InitModuleUI
import com.ageone.naladoni.External.RxBus.RxBus
import com.ageone.naladoni.External.RxBus.RxEvent
import com.ageone.naladoni.Models.User.user
import com.ageone.naladoni.Modules.Profile.rows.*
import com.ageone.naladoni.R
import yummypets.com.stevia.*

class ProfileView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = ProfileViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "Мой Профиль"
        renderToolbar()

        bodyTable.adapter = viewAdapter
        bodyTable.overScrollMode = View.OVER_SCROLL_NEVER

        renderUIO()
        bindUI()
    }

    fun bindUI() {
        compositeDisposable.add(
            RxBus.listen(RxEvent.EventChangeCity::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
        compositeDisposable.add(
            RxBus.listen(RxEvent.EventChangeName::class.java).subscribe {
                bodyTable.adapter?.notifyDataSetChanged()
            }
        )
    }

    inner class Factory(val rootModule: BaseModule) : BaseAdapter<BaseViewHolder>() {

        private val ProfileUserInformationType = 0
        private val ProfileUsedDiscountType = 1
        private val ProfileInformationType = 2
        private val ProfileServiceInformationType = 3

        override fun getItemCount() = 6

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> ProfileUserInformationType
            1 -> ProfileUsedDiscountType
            2, 3 -> ProfileInformationType
            4 -> ProfileServiceInformationType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {

                ProfileUserInformationType -> {
                    ProfileUserInformationViewHolder(layout)
                }
                ProfileUsedDiscountType -> {
                    ProfileUsedDiscountViewHolder(layout)
                }
                ProfileInformationType -> {
                    ProfileInformationViewHolder(layout)
                }
                ProfileServiceInformationType -> {
                    ProfileServiceInformationViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {

                is ProfileUserInformationViewHolder -> {
                    holder.initialize(user.data.name, R.drawable.pic_naladoni)
                    holder.view.setOnClickListener{
                        rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnClickProfileName.name)
                    }
                }

                is ProfileUsedDiscountViewHolder -> {
                    holder.initialize(user.data.giftsTakenNum,"Количество использованных мною скидок:")
                }

                is ProfileInformationViewHolder -> {
                    when (position) {
                        2 -> {
                            holder.initialize("Мой город", user.info.city?.name ?: "")
                            holder.back.setOnClickListener{
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnClickProfileCity.name)
                            }
                        }

                        3 -> {
                            holder.initialize("Мой номер моб. телефона", user.data.phone)
                            holder.back.setOnClickListener{
                                rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnClickProfilePhone.name)
                            }
                        }

                    }
                }
                is ProfileServiceInformationViewHolder -> {
                    holder.initialize("О нашем сервисе")
                    holder.back.setOnClickListener{
                        rootModule.emitEvent?.invoke(ProfileViewModel.EventType.OnClickProfileAboutCompany.name)
                    }
                }
            }

        }

    }

}

fun ProfileView.renderUIO() {

    renderBodyTable()

    bodyTable
        .constrainTopToTopOf(innerContent)

}


