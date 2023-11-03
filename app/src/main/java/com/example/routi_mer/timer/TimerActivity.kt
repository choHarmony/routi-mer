package com.example.routi_mer.timer

import android.media.Ringtone
import android.media.RingtoneManager
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.routi_mer.GetRoutineItemPosition
import com.example.routi_mer.R
import com.example.routi_mer.databinding.ActivityTimerBinding
import com.example.routi_mer.room.RoutineDB

class TimerActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private lateinit var ringtone: Ringtone
    var isClicked = false // 타이머 화면을 중도에 닫더라도 스레드가 동작하여 cursor을 사용하는 것을 방지

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
            isClicked = true
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

        val musicList = this.resources.getStringArray(R.array.music_array).toMutableList()
        val rtManager = RingtoneManager(this)
        rtManager.setType(RingtoneManager.TYPE_NOTIFICATION)
        rtManager.cursor.run {
            while (moveToNext()) {
                musicList.add(getString(RingtoneManager.TITLE_COLUMN_INDEX))
            }
        }

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


                    if (st < set && !isClicked) {
                        ringtone = rtManager.getRingtone(musicList.indexOf(timerInRoutine[i].oneSetMusicTitle))
                        ringtone.play()
                    }

                }

                if (!isClicked) {
                    ringtone = rtManager.getRingtone(musicList.indexOf(timerInRoutine[i].fullSetMusicTitle))
                    ringtone.play()
                }



            }
            binding.btnTimerPause.text = "다시 시작"
            binding.btnTimerPause.isClickable = true

        }.start()


    }


}
