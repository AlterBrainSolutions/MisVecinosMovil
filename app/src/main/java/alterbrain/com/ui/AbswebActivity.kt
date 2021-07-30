package alterbrain.com.ui

import alterbrain.com.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.graphics.Bitmap
import android.webkit.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_absweb.*
import kotlinx.android.synthetic.main.activity_main1.*

class AbswebActivity : AppCompatActivity() {
    // Private

    private val BASE_URL = "https://google.com"
    private val SEARCH_PATH = "/search?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_absweb)

        // Refresh

        swipeRefresh.setOnRefreshListener {
            webView.reload()
        }

        // Search

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(p0: String?): Boolean {

                p0?.let {
                    if(URLUtil.isValidUrl(it)) {
                        // Es una url
                        webView.loadUrl(it)
                    } else {
                        // No es una url
                        webView.loadUrl("$BASE_URL$SEARCH_PATH$it")
                    }
                }

                return false
            }

        })

        // WebView

        webView.webChromeClient = object : WebChromeClient() {

        }

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                searchView.setQuery(url, false)

                swipeRefresh.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                swipeRefresh.isRefreshing = false
            }

        }

        val settings = webView.settings
        settings.javaScriptEnabled = true

        webView.loadUrl(BASE_URL)
        //webView.loadUrl("http://www.alterbrain.com.mx/");

    }

    override fun onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

}