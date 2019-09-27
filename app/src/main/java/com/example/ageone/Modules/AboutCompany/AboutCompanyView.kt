package com.example.ageone.Modules.AboutCompany

import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.Base.RecyclerView.BaseAdapter
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.InitModuleUI
import com.example.ageone.Modules.AboutCompany.rows.AboutCompanyViewHolder
import com.example.ageone.Modules.AboutCompany.rows.TextAboutViewHolder
import com.example.ageone.Modules.AboutCompany.rows.TextEmailViewHolder
import com.example.ageone.Modules.AboutCompany.rows.initialize
import com.example.ageone.R
import com.example.ageone.UIComponents.ViewHolders.ButtonViewHolder
import com.example.ageone.UIComponents.ViewHolders.initialize
import yummypets.com.stevia.*

class AboutCompanyView(initModuleUI: InitModuleUI = InitModuleUI()) : BaseModule(initModuleUI) {

    val viewModel = AboutCompanyViewModel()

    val viewAdapter by lazy {
        val viewAdapter = Factory(this)
        viewAdapter
    }

    init {
//        viewModel.loadRealmData()

        setBackgroundResource(R.drawable.base_background)//TODO: set background

        toolbar.title = "О сервисе"

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

        private val AboutCompanyvType = 0
        private val AboutTextType = 1
        private val AboutTextButtonType = 2
        private val AboutTextEmailType = 3

        override fun getItemCount() = 7//viewModel.realmData.size

        override fun getItemViewType(position: Int): Int = when (position) {
            0 -> AboutCompanyvType
            1,2 -> AboutTextType
            3 -> AboutTextButtonType
            4 -> AboutTextEmailType
            else -> -1
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {

            val layout = ConstraintLayout(parent.context)

            layout
                .width(matchParent)
                .height(wrapContent)

            val holder = when (viewType) {
                AboutCompanyvType -> {
                    AboutCompanyViewHolder(layout)
                }
                AboutTextType -> {
                    TextAboutViewHolder(layout)
                }
                AboutTextButtonType -> {
                    ButtonViewHolder(layout)
                }
                AboutTextEmailType -> {
                    TextEmailViewHolder(layout)
                }
                else -> {
                    BaseViewHolder(layout)
                }
            }

            return holder
        }

        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

            when (holder) {
                is AboutCompanyViewHolder -> {
                    holder.initialize("Все акции твоего города ","NALADONI")
                }

                is TextAboutViewHolder -> {
                    when (position) {
                        1 -> {
                            holder.initialize("Значимость этих проблем настолько очевидна, что консультация " +
                                    "с широким активом играет важную роль в формировании дальнейших направлений развития." +
                                    " Задача организации, в особенности же консультация с широким активом требуют определения " +
                                    "и уточнения дальнейших направлений развития.","О компании")

                        }
                        2 -> {
                            holder.initialize("Значимость этих проблем настолько очевидна, что консультация с широким активом" +
                                    " играет важную роль в формировании дальнейших направлений развития.","Партнерам")
                            }
                        }
                    }

                is ButtonViewHolder -> {
                    holder.initialize("Позвонить в компанию")
                }

                is TextEmailViewHolder -> {
                    holder.initialize()
                }

                }
            }

        }

    }

fun AboutCompanyView.renderUIO() {

    renderBodyTable()

    bodyTable
        .constrainTopToTopOf(innerContent,54)
}


