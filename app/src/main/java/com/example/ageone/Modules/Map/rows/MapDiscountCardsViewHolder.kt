package com.example.ageone.Modules.Map.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.ageone.Application.R
import com.example.ageone.Application.utils
import com.example.ageone.External.Base.Button.BaseButton
import com.example.ageone.External.Base.ImageView.BaseImageView
import com.example.ageone.External.Base.RecyclerView.BaseViewHolder
import com.example.ageone.External.Base.TextView.BaseTextView
import com.example.ageone.External.Base.View.BaseView
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class MapDiscountCardsViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {
    val back by lazy {
        val view = BaseView()
        view.cornerRadius = 8.dp
        view.elevation = 15F.dp
        view.backgroundColor = Color.WHITE
        view.initialize()
        view
    }

        val imageLock by lazy {
            val image = BaseImageView()
            image.setBackgroundResource(R.drawable.pic_groupfood)
            image.elevation = 5F.dp
            image
        }

        val textViewTitle by lazy {
            val textView = BaseTextView()
            textView.gravity = Gravity.START
            textView.typeface = Typeface.DEFAULT_BOLD
            textView.textSize = 17F
            textView.text = "Шаверма Mix"
            textView.textColor = Color.parseColor("#333333")
            textView.setBackgroundColor(Color.TRANSPARENT)
            textView
        }

        val textViewDescribe by lazy {
            val textView = BaseTextView()
            textView.gravity = Gravity.START
            textView.typeface = Typeface.DEFAULT
            textView.textSize = 13F
            textView.text = "При покупке шавермы big получи 0.5 колы в подарок!"
            textView.textColor = Color.parseColor("#333333")
            textView.setBackgroundColor(Color.TRANSPARENT)
            textView
        }
        val ButonEnter by lazy {
            val butonEnter = BaseButton()
            butonEnter.gravity = Gravity.START
            butonEnter.cornerRadius = 22
            butonEnter.textSize = 11F
            butonEnter.text = "Использовать"
            butonEnter.textColor = Color.parseColor("#FFFAFA")
            butonEnter.setBackgroundColor(Color.parseColor("#f2842d"))
            butonEnter.cornerRadius = 15.dp
            butonEnter.elevation = 8F.dp
            butonEnter
        }

        init {
            renderUI()
        }

    }
    fun MapDiscountCardsViewHolder.renderUI() {

        constraintLayout.subviews(
            back.subviews(
                imageLock,
                textViewTitle,
                textViewDescribe,
                ButonEnter

            )
        )
        back
            .fillHorizontally(8)
            .constrainLeftToLeftOf(constraintLayout, 17)
            .constrainRightToRightOf(constraintLayout, 17)
            .width(283)
            .height(117)

        imageLock
            .constrainLeftToLeftOf(back, 8)
            .constrainTopToTopOf(back, 8)
            .width(44)
            .height(44)

        textViewTitle
            .constrainTopToTopOf(back, 8)
            .fillHorizontally(60)


        textViewDescribe
            .constrainTopToBottomOf(textViewTitle, 4)
            .fillHorizontally(60)

        ButonEnter
            .constrainTopToBottomOf(textViewDescribe, 4)
            .constrainBottomToBottomOf(back, 4)
            .fillHorizontally(5)
            .width(260)
            .height(27)

    }

    fun MapDiscountCardsViewHolder.initialize(title: String, describe: String,icon: Int) {

        textViewTitle.text = title
        textViewDescribe.text = describe
        imageLock.setBackgroundResource(icon)

    }
