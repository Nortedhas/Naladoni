package com.example.ageone.External.Libraries.Glide

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.ageone.Application.currentActivity
import io.github.armcha.coloredshadow.ShadowImageView
import yummypets.com.stevia.dp

@GlideModule
class MyGlideModule : AppGlideModule()

fun addImageFromGlide(image: ImageView, uri: String) {
    val placeholder = createDownloadPlaceholder()

    GlideApp
        .with(image)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(8.dp))
        .placeholder(placeholder)
        .into(image)

}

fun addImageFromGlide(image: ImageView, uri: Int) {
    val placeholder = createDownloadPlaceholder()

    GlideApp
        .with(image)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(8.dp))
        .placeholder(placeholder)
        .into(image)

}

fun addImageFromGlideWithShadow(image: ShadowImageView, uri: String) {
    val placeholder = createDownloadPlaceholder()

    GlideApp
        .with(image)
        .load(uri)
        .transform(CenterCrop(), RoundedCorners(8.dp))
        .placeholder(placeholder)
        .into(object : ViewTarget<ImageView, Drawable>(image) {
            override fun onLoadStarted(placeholder: Drawable?) {
                super.onLoadStarted(placeholder)
                image.setImageDrawable(placeholder, withShadow = false)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                super.onLoadCleared(placeholder)
                image.setImageDrawable(placeholder, withShadow = false)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                image.setImageDrawable(errorDrawable, withShadow = false)
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                image.setImageDrawable(resource)
            }
        })

}

private fun createDownloadPlaceholder(): LayerDrawable {
    val circularProgressDrawable = CircularProgressDrawable(currentActivity as Context)
    circularProgressDrawable.strokeWidth = 15f.dp
    circularProgressDrawable.centerRadius = 100f.dp
    circularProgressDrawable.start()

    val placeholderImage = GradientDrawable()
    placeholderImage.setColor(Color.GRAY)

    val placeholder = LayerDrawable(
        arrayOf(
            placeholderImage,
            circularProgressDrawable
        )
    )
    return placeholder
}
