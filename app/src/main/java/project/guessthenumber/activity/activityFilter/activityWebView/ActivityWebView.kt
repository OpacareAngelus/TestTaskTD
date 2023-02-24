package project.guessthenumber.activity.activityFilter.activityWebView

import android.os.Bundle
import android.webkit.WebViewClient
import project.guessthenumber.architecture.BaseActivity
import project.guessthenumber.databinding.ActivityWebViewBinding

class ActivityWebView : BaseActivity<ActivityWebViewBinding>(ActivityWebViewBinding::inflate)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showWebView(GOOGLE_LINK)
    }

    private fun showWebView(url: String) {
        with(binding) {
            wvMain.webViewClient = WebViewClient()
            wvMain.loadUrl(url)
        }
    }

    companion object {
        const val GOOGLE_LINK =
            "https://www.google.com/search?client=firefox-b-d&q=%D0%B3%D1%83%D0%B3%D0%BB"
    }
}