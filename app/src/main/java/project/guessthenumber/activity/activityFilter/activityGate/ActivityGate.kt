package project.guessthenumber.activity.activityFilter.activityGate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import project.guessthenumber.R
import project.guessthenumber.architecture.BaseActivity
import project.guessthenumber.databinding.ActivityGateBinding

class ActivityGate : BaseActivity<ActivityGateBinding>(ActivityGateBinding::inflate) {

    private val viewModel: ActivityGateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBaseValueOfEditText()
        setListeners()
        setObservers()
    }

    private fun setBaseValueOfEditText() {
        binding.etUserVariant.setText(getString(R.string.zero))
    }

    private fun setListeners() {
        with(binding) {
            ivPlay.setOnClickListener {
                if (viewModel.playerLife.value!! > 0) {
                    if (etUserVariant.text.toString().toInt() == viewModel.guessNumber.value) {
                        showTextAboutWin()
                    } else {
                        showToastAboutWrongVariant()
                    }
                    if (viewModel.playerLife.value == 0) {
                        showTextGameOver()
                    }
                }
            }
        }
    }

    private fun showTextGameOver() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.tvGuessCounter.text =getString(R.string.game_over)
            delay(DELAY_TIME)
            finish()
        }
    }

    private fun showToastAboutWrongVariant() {
        val toastText = String.format(
            this@ActivityGate.getString(R.string.wrong_variant),
            binding.etUserVariant.text
        )
        Toast.makeText(applicationContext, toastText, Toast.LENGTH_SHORT).show()
        viewModel.loseLife()
    }

    private fun showTextAboutWin() {
        CoroutineScope(Dispatchers.Main).launch {
            binding.tvGuessCounter.text = getString(R.string.user_win_text)
            delay(DELAY_TIME)
            finish()
        }
    }

    private fun setObservers() {
        with(binding) {
            viewModel.playerLife.observe(this@ActivityGate) {
                tvGuessCounter.text =
                    String.format(this@ActivityGate.getString(R.string.player_lifes), it)
            }
        }
    }

    companion object{
        const val DELAY_TIME = 3000L
    }
}