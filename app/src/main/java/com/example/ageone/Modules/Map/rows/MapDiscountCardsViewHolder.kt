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
import com.example.ageone.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class MapDiscountCardsViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

        val imageLock by lazy {
            val image = BaseImageView()
            image.setBackgroundResource(R.drawable.pic_food)
            image.elevation = 5F.dp
            image
        }

//        val imageViewPhoto by lazy {
//            val imageView = BaseImageView()
//            imageView.cornerRadius = 8.dp
//            imageView.orientation= GradientDrawable.Orientation.TOP_BOTTOM
//            imageView.backgroundColor = Color.parseColor("#EBEBF0")
//            imageView.initialize()
//            imageView.elevation = 4F.dp
//            imageView
//        }

        val textViewTitle by lazy {
            val textView = BaseTextView()
            textView.gravity = Gravity.START
            textView.typeface = Typeface.DEFAULT_BOLD
            textView.textSize = 15F
            textView.textColor = Color.parseColor("#333333")
            textView.setBackgroundColor(Color.TRANSPARENT)
            textView
        }

        val textViewDescribe by lazy {
            val textView = BaseTextView()
            textView.gravity = Gravity.START
            textView.typeface = Typeface.DEFAULT
            textView.textSize = 11F
            textView.textColor = Color.parseColor("#979797")
            textView.setBackgroundColor(Color.TRANSPARENT)
            textView
        }
        val ButonEnter by lazy {
            val ButonEnter = BaseButton()
            ButonEnter.gravity = Gravity.START
            ButonEnter.textSize = 15F
            ButonEnter.text = "Использовать"
            ButonEnter.textColor = Color.parseColor("#FFFAFA")
            ButonEnter.setBackgroundColor(Color.parseColor("#f2842d"))
            ButonEnter
        }

        init {
            renderUI()
        }

    }
    fun MapDiscountCardsViewHolder.renderUI() {
        constraintLayout.subviews(
//            imageViewPhoto,
            imageLock,
            textViewTitle,
            textViewDescribe,
            ButonEnter

        )

//        imageViewPhoto
//            .constrainTopToTopOf(constraintLayout, 16)
//            .fillHorizontally(8)

        imageLock
            .constrainLeftToLeftOf(constraintLayout, 8)
            .constrainTopToTopOf(constraintLayout, 8)
            .width(35)
            .height(35)

        textViewTitle
            .constrainTopToTopOf(constraintLayout, 8)
            .constrainRightToRightOf(constraintLayout, 8)
            .constrainLeftToLeftOf(constraintLayout, 8)


        textViewDescribe
            .constrainTopToBottomOf(textViewTitle, 4)
            .constrainRightToRightOf(constraintLayout, 8)
            .constrainLeftToLeftOf(constraintLayout, 8)

        ButonEnter
            .constrainBottomToTopOf(constraintLayout, 4)
            .constrainBottomToBottomOf(constraintLayout, 4)
            .fillHorizontally(8)



    }

    fun MapDiscountCardsViewHolder.initialize(width: Int, height: Int,title: String, describe: String) {

        constraintLayout
            .width(width)
            .height(height)
            .backgroundColor = Color.parseColor("#EBEBF0")
        ButonEnter
            .height(height/3)
//        imageViewPhoto
//            .width(width - 20)
//            .height(height)
            imageLock.visibility = View.VISIBLE

        textViewTitle.text = title
        textViewDescribe.text = describe

    }
