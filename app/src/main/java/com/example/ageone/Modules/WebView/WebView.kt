package com.example.ageone.Modules

import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.ageone.Application.R
import com.example.ageone.Application.currentActivity
import com.example.ageone.External.Base.Module.BaseModule
import com.example.ageone.External.InitModuleUI
import yummypets.com.stevia.fillHorizontally
import yummypets.com.stevia.fillVertically
import yummypets.com.stevia.subviews

class WebView(initModuleUI: InitModuleUI = InitModuleUI(), url: String) : BaseModule(initModuleUI) {
    val webView by lazy {
        val view = WebView(currentActivity)
        view
    }

    init {
        setBackgroundResource(R.drawable.base_background)
        toolbar.title = "Оплата"
        renderToolbar()

        innerContent.subviews(
            webView
        )

        webView
            .fillHorizontally()
            .fillVertically()

        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
        webView.webViewClient = MyWebViewClient()
    }
}

class MyWebViewClient : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        view?.loadUrl(request?.url.toString())
        return true
    }
}
