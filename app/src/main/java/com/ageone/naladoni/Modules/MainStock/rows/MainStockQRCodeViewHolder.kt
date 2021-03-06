package com.ageone.naladoni.Modules.MainStock.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.naladoni.External.Base.ImageView.BaseImageView
import com.ageone.naladoni.External.Base.RecyclerView.BaseViewHolder
import com.ageone.naladoni.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*
import com.ageone.naladoni.Models.User.user
import net.glxn.qrgen.android.QRCode


class MainStockQRCodeViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val textTitle by lazy {
        val text = BaseTextView()
        text.textColor = Color.parseColor("#f2842d")
        text.textSize = 21F
        text.typeface = Typeface.DEFAULT_BOLD
        text.gravity = Gravity.CENTER
        text.setBackgroundColor(Color.TRANSPARENT)
        text
    }

    val textDescribe by lazy {
        val text = BaseTextView()
        text.textColor = Color.parseColor("#AFAFB4")
        text.textSize = 15F
        text.typeface = Typeface.DEFAULT
        text.gravity = Gravity.CENTER
        text.setBackgroundColor(Color.TRANSPARENT)

        text
    }

    val imageQRCod by lazy {
        val image = BaseImageView()

        image
    }

    val textNumber by lazy {
        val text = BaseTextView()
        text.textColor = Color.parseColor("#F06F28")
        text.textSize = 15F
        text.typeface = Typeface.DEFAULT_BOLD
        text.gravity = Gravity.CENTER
        text.setBackgroundColor(Color.TRANSPARENT)
        text
    }

    init {

        renderUI()
    }

}

fun MainStockQRCodeViewHolder.renderUI() {

    constraintLayout.subviews(
        textTitle,
        textDescribe,
        imageQRCod,
        textNumber
    )

    textTitle
        .constrainTopToTopOf(constraintLayout,22)
        .fillHorizontally(50)

    textDescribe
        .constrainTopToBottomOf(textTitle, 5)
        .fillHorizontally(20)

    imageQRCod
        .width(178)
        .height(178)
        .constrainTopToBottomOf(textDescribe, 5)
        .constrainLeftToLeftOf(constraintLayout)
        .constrainRightToRightOf(constraintLayout)

    textNumber
        .constrainTopToBottomOf(imageQRCod)
        .constrainBottomToBottomOf(constraintLayout,31)
        .fillHorizontally()
}

fun MainStockQRCodeViewHolder.initialize(usesCount: Int, code: String, number: String) {
    val counter = "$usesCount"
    val text: String = "Количество воспользовавшихся предложением: "

    val spannableContent = SpannableString(text + counter)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#f2842d")),
        text.length,  text.length + counter.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textDescribe.text = spannableContent

    textTitle.text = "Получай выгоду!"
    textNumber.text = number

    val myBitmap = QRCode.from("{" +
            "\"clientHashId\":\"${user.hashId}\", " +
            "\"code\":\"$code\"}"
            ).bitmap()
    imageQRCod.setImageBitmap(myBitmap)
}
