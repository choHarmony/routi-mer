package com.example.routi_mer

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.routi_mer.databinding.ActivityTimerBinding
import java.util.*

class TimerActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private var timerTask: Timer? = null
    var everStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnExitTimer.setOnClickListener {
            finish()
        }

        binding.btnTimerPause.setOnClickListener {
            if (binding.btnTimerPause.text == "중지") {
                binding.btnTimerPause.text = "시작"
                // 타이머 일시정지
            } else if (binding.btnTimerPause.text == "시작" && !everStarted) { // 타이머 최초 시작
                everStarted = true
                binding.btnTimerPause.text = "중지"
                runTimer()
            }
            else if (binding.btnTimerPause.text == "시작" && everStarted) { // 타이머 resume
                binding.btnTimerPause.text = "중지"
                timerTask?.cancel()
            }


        }









        }

    private fun runTimer() {
        val routineDatabase = RoutineDB.getRoutineList(this)
        val clickedPos = GetRoutineItemPosition.routinePos
        val allRoutine = routineDatabase!!.RoutineListDao().selectAllRoutine()
        binding.timerRoutineTitle.text = allRoutine[clickedPos].mainTitle
        val timerInRoutine = allRoutine[clickedPos].routineTimer

        timerTask = kotlin.concurrent.timer(period = 1000) {
            for (i in 0 until timerInRoutine.size) {
                val set = timerInRoutine[i].timerSet.toInt()
                val second = timerInRoutine[i].timerSec.toInt()
                runOnUiThread {
                    binding.timerTimerTitle.text = timerInRoutine[i].timerTitle
                    binding.timerTimerDes.text = timerInRoutine[i].timerDescription
                }

                for (st in 1 until set+1) {
                    runOnUiThread {
                        binding.timerSetNum.text = "$st/$set 세트"
                    }

                    for (j in second downTo 0) {
                        val min = "%02d".format(j / 60)
                        val sec = "%02d".format(j % 60)
                        runOnUiThread {
                            binding.timerTime.text = "$min:$sec"
                        }
                        Thread.sleep(1000)
                    }
                }


            }
        }
    }



}
