package com.example.routi_mer

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.routi_mer.databinding.ActivityTimerBinding
import java.util.*

class TimerActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    var isClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val routineDatabase = RoutineDB.getRoutineList(this)
        val clickedPos = GetRoutineItemPosition.routinePos
        val allRoutine = routineDatabase!!.RoutineListDao().selectAllRoutine()
        val timerInRoutine = allRoutine[clickedPos].routineTimer
        val set = timerInRoutine[0].timerSet.toInt()
        val second = timerInRoutine[0].timerSec.toInt()
        val min = "%02d".format(second / 60)
        val sec = "%02d".format(second % 60)

        binding.timerRoutineTitle.text = allRoutine[clickedPos].mainTitle
        binding.timerTimerTitle.text = timerInRoutine[0].timerTitle
        binding.timerTimerDes.text = timerInRoutine[0].timerDescription
        binding.timerSetNum.text = "1/$set 세트"
        binding.timerTime.text = "$min:$sec"


        binding.btnExitTimer.setOnClickListener {
            finish()
        }

        binding.btnTimerPause.setOnClickListener {
            runTimer()
            binding.btnTimerPause.text = "진행 중"
            binding.btnTimerPause.isClickable = false
        }





        }

    private fun runTimer() {
        val routineDatabase = RoutineDB.getRoutineList(this)
        val clickedPos = GetRoutineItemPosition.routinePos
        val allRoutine = routineDatabase!!.RoutineListDao().selectAllRoutine()
        binding.timerRoutineTitle.text = allRoutine[clickedPos].mainTitle
        val timerInRoutine = allRoutine[clickedPos].routineTimer

        Thread() {
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
                            binding.timerProgress.max = second
                            binding.timerTime.text = "$min:$sec"
                            binding.timerProgress.progress = second-j
                        }

                        Thread.sleep(1000)
                    }
                }


            }
            binding.btnTimerPause.text = "다시 시작"
            binding.btnTimerPause.isClickable = true
        }.start()


    }


}
