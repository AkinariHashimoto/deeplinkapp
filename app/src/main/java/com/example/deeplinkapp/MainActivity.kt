package com.example.deeplinkapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnShowWebsite: Button
    private lateinit var webView: WebView
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShowWebsite = findViewById(R.id.btnShowWebsite)
        webView = findViewById(R.id.webView)
        tvResult = findViewById(R.id.tvResult)

        // WebViewの設定
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null && url.startsWith("myapp://")) {
                    // カスタムスキーマの場合はIntentで処理
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                    startActivity(intent)
                    return true
                }
                return false
            }
        }

        // ボタンクリックでWebサイトを表示
        btnShowWebsite.setOnClickListener {
            webView.loadUrl("http://10.0.2.2:8080/index.html") // エミュレータ用
            // 実機の場合は "http://YOUR_MAC_IP:8080/index.html"
        }

        // ディープリンクからの遷移を処理
        handleDeepLink(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleDeepLink(it) }
    }

    private fun handleDeepLink(intent: Intent) {
        val data: Uri? = intent.data
        if (data != null && data.scheme == "myapp") {
            // ディープリンクから文字列を取得
            val message = data.getQueryParameter("data") ?: "パラメータなし"
            tvResult.text = "受信データ: $message"

            // WebViewを非表示にして結果を表示
            webView.visibility = android.view.View.GONE
            tvResult.visibility = android.view.View.VISIBLE
        }
    }
}