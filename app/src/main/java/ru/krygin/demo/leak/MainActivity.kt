package ru.krygin.demo.leak

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    private lateinit var webViewContainer: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WebView.setWebContentsDebuggingEnabled(true)
        setContentView(R.layout.activity_main)
        webViewContainer = findViewById(R.id.container)
        webViewContainer.addView(WebView(this).apply {
            settings.apply {
                javaScriptEnabled = true
                webChromeClient = WebChromeClient()
            }
            loadUrl("about:blank")
        })

    }

    override fun onDestroy() {
        val webView = webViewContainer.getChildAt(0) as WebView
        webView.destroy()
        super.onDestroy()

    }
}