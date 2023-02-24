package project.guessthenumber.activity.activityFilter

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import project.guessthenumber.activity.activityFilter.activityGate.ActivityGate
import project.guessthenumber.activity.activityFilter.activityWebView.ActivityWebView
import project.guessthenumber.architecture.BaseActivity
import project.guessthenumber.databinding.ActivityFilterBinding

class ActivityFilter : BaseActivity<ActivityFilterBinding>(ActivityFilterBinding::inflate) {

    private val viewModel: ActivityFilterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserLocation()
        setObserver()
    }

    private fun setObserver() {
        viewModel.locationResponse.observe(this, Observer {
            changeActivity()
        })
    }

    private fun changeActivity() {
        val intentToGate = Intent(this, ActivityGate::class.java)
        val intentToWebView = Intent(this, ActivityWebView::class.java)
        when (viewModel.locationResponse.value?.country) {
            KAZAKHSTAN -> {
                startActivity(intentToGate)
                finish()
            }
            INDIA -> {
                startActivity(intentToGate)
                finish()
            }
            else -> {
                startActivity(intentToWebView)
                finish()
            }
        }
    }

    private fun getUserLocation() {
        viewModel.getLocation(EMPTY_IP)
    }

    companion object {
        const val KAZAKHSTAN = "Kazakhstan"
        const val INDIA = "India"

        // In this case API get device IP.
        const val EMPTY_IP = ""

        //This is IP for tests. I remember u are from Kazakhstan, so use TEST_IP_UKRAINE for open WebView.
        //I don't have idea whose this IP :D
        const val TEST_IP_KAZAKHSTAN = "185.22.67.13"
        const val TEST_IP_INDIA = "103.10.24.130"
        const val TEST_IP_UKRAINE = "77.123.136.0"
    }
}