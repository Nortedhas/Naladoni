package com.example.ageone.External.Libraries.WebView

import android.webkit.WebView
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.fillVertically
import yummypets.com.stevia.subviews

fun BaseModule.openUrl(url: String) {
    val webView = WebView(currentActivity)
    innerContent.subviews(
        webView
    )

    webView
        .fillHorizontally()
        .fillVertically()

    webView.settings.javaScriptEnabled = true
    webView.loadUrl(url)
}