package project.guessthenumber.activity.activityFilter.activityGate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Random

class ActivityGateViewModel : ViewModel() {

    private val _playerLife = MutableLiveData<Int>()
    val playerLife: LiveData<Int> = _playerLife

    private val _guessNumber = MutableLiveData<Int>()
    val guessNumber: LiveData<Int> = _guessNumber

    init {
        _playerLife.value = BASE_LIFE_COUNT
        _guessNumber.value = Random().nextInt(NUMBER_RANGE)+1
    }

    fun loseLife() {
        _playerLife.value = _playerLife.value!! - 1
    }

    companion object{
        const val BASE_LIFE_COUNT = 3
        const val NUMBER_RANGE = 100
    }
}