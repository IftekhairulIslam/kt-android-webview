package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

class MainActivity : ComponentActivity() {
    private val url: String = "https://app.landknock.com/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebViewPage(url)
        }
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewPage(url: String){
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            // Enable cookie handling
            val cookieManager = CookieManager.getInstance()
            cookieManager.setAcceptCookie(true)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                cookieManager.setAcceptThirdPartyCookies(this, true)
            }

            loadUrl(url)
        }
    }, update = {
        it.loadUrl(url)
    })
}