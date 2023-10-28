package com.example.routi_mer

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.example.routi_mer.databinding.ActivityTimerBinding
import java.text.DecimalFormat

class TimerActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnExitTimer.setOnClickListener {
            finish()
        }


        val routineDatabase = RoutineDB.getRoutineList(this)
        val clickedPos = GetRoutineItemPosition.routinePos
        if (routineDatabase != null) {
            val allRoutine = routineDatabase.RoutineListDao().selectAllRoutine()
            binding.timerRoutineTitle.text = allRoutine[clickedPos].mainTitle

            val timerInRoutine = allRoutine[clickedPos].routineTimer
            Thread {
                for (i in 0 until timerInRoutine.size) {
                    val set = timerInRoutine[i].timerSet.toInt()
                    val second = timerInRoutine[i].timerSec.toInt()
                    val handler = Handler(Looper.getMainLooper())
                    handler.post {
                        binding.timerTimerTitle.text = timerInRoutine[i].timerTitle
                        binding.timerTimerDes.text = timerInRoutine[i].timerDescription
                    }

                    for (st in 1 until set+1) {
                        handler.post {
                            binding.timerSetNum.text = "$st/$set 세트"
                        }

                        for (j in second downTo 0) {
                            val min = "%02d".format(j / 60)
                            val sec = "%02d".format(j % 60)
                            handler.post {
                                binding.timerTime.text = "$min:$sec"
                            }
                            Thread.sleep(1000)
                        }
                    }


                }
            }.start()


        }





        }



    }
