package com.example.virtualcare.Activity

import android.os.Build
import android.os.Bundle
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.virtualcare.R
import com.example.virtualcare.call.CallClass

class VideoCallView : AppCompatActivity() {

    lateinit var end: ImageView
    lateinit var webview: WebView
    lateinit var clientId: String
    var isPeerConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_call_view)

        webview = findViewById(R.id.webview)
        end = findViewById(R.id.end)

        val ID = intent.getStringExtra("connectId")
        clientId = ID ?: ""

        setWebView()
        createCall()
    }

    private fun setWebView() {
        webview.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    request.grant(request.resources)
                }
            }
        }

        webview.settings.javaScriptEnabled = true
        webview.settings.mediaPlaybackRequiresUserGesture = false

        webview.addJavascriptInterface(CallClass(this), "Android")

        loadVideoCall()
    }

    private fun loadVideoCall() {
        val filepath = "file:///android_asset/call.html"
        webview.loadUrl(filepath)

        webview.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)

                callJavaScriptFunction("javascript:init(\"$clientId\")")
            }
        }
    }

    private fun callJavaScriptFunction(function: String) {
        webview.post {
            webview.evaluateJavascript(function, null)
        }
    }

    private fun createCall() {
        callJavaScriptFunction("javascript:startCall(\"$clientId\")")
    }
    fun onPeerConnected() {
        isPeerConnected = true
    }
}
