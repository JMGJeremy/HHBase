package com.jmg.baseproject.webview

import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.jmg.baseproject.HHBaseTheme

@Composable
fun WebViewScreen(
    url: String,
    progress: MutableState<Boolean>
){
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {

                WebView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    setLayerType(View.LAYER_TYPE_HARDWARE, null)
                    setBackgroundColor(0)
//                    settings.allowFileAccess = true
//                    settings.allowContentAccess = true
//                    settings.cacheMode = WebSettings.LOAD_NO_CACHE
//                    settings.databaseEnabled = true
                    settings.domStorageEnabled = true
                    settings.javaScriptEnabled = true
//                    settings.loadsImagesAutomatically = true
//                    settings.mediaPlaybackRequiresUserGesture = false
//                    settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
//                    settings.setSupportZoom(false)
                    settings.userAgentString = "Android"
                    webChromeClient = WebChromeClient()
                    webViewClient = object: WebViewClient(){
                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            progress.value = false
                        }
                    }
//                    loadUrl(WebViewUrl)
//                    addJavascriptInterface(JavaScriptInterface(this, navController!!), "javascript_obj")

                }
            },
            update = { it.loadUrl(url)})
    }
}

@Preview
@Composable
fun WebviewPrev(){
    HHBaseTheme {
        WebViewScreen(
            url = "google.com",
            progress = remember { mutableStateOf(false) }
            )
    }
}