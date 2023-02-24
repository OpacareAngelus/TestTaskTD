package project.guessthenumber.activity.activityFilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import project.guessthenumber.network.NetworkService.requestAPI
import project.guessthenumber.network.remote.response.LocationResponse

class ActivityFilterViewModel : ViewModel() {

    private val _locationResponse = MutableLiveData<LocationResponse>()
    val locationResponse: LiveData<LocationResponse> = _locationResponse

    fun getLocation(ip: String) {
        viewModelScope.launch {
            try {
                val response = requestAPI.getLocation(ip)
                _locationResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}