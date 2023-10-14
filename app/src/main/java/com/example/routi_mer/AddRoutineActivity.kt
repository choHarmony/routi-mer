package com.example.routi_mer

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.databinding.ActivityAddRoutineBinding
import com.example.routi_mer.databinding.ActivityMainBinding
import com.example.routi_mer.databinding.LayoutDialogSetTimerTitleBinding
import java.util.logging.Logger.global
import kotlin.concurrent.timer


class AddRoutineActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRoutineBinding
    private lateinit var dialogBinding: LayoutDialogSetTimerTitleBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var timerList = ArrayList<TimerListData>()

    private val addTimerBTSFragment = AddTimerBottomSheetFragment(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("좀돼라", "oncreated")
        binding = ActivityAddRoutineBinding.inflate(layoutInflater)
        dialogBinding = LayoutDialogSetTimerTitleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.textRoutineTitle.setOnClickListener {
            setEditTitleDialog()
        }

        setTimerRecyclerview()
        timerList.apply {
            add(TimerListData("목 옆으로 당기기", "목을 옆으로 당기면 된다", "20", "3"))
        }

        binding.addTimer.setOnClickListener {
            addTimerBTSFragment.show(supportFragmentManager, addTimerBTSFragment.tag)
//            timerList.apply{
//                add(TimerListData("a", "b", "20", "3"))
//            }
//            viewAdapter.notifyItemInserted(timerList.size)
        }






    }


    private fun setTimerRecyclerview() {
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = TimerListAdapter(timerList)
        recyclerView = binding.timerList.apply {
            //setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }


    private fun setEditTitleDialog() {
        val dialog = EditRoutineTitleDialog(this)
        dialog.showDialog(binding.textRoutineTitle.text.toString(), "")
        // 후에 db 구축하면 des 부분은 db에서 불러오는 거로 하면 됨!
        dialog.setOnClickListener(object : EditRoutineTitleDialog.OnDialogClickListener {
            override fun onClicked(title: String, des: String) {
                binding.textRoutineTitle.text = title
                // des는 이 화면에 보이지 않고 데이터베이스에 저장만 해놓을 예정
                // 따라서 여기는 더 추가할 필요 없음
            }

        })
    }


    fun receiveData(t: String, d: String, sec: String, set: String) {
        Log.d("좀돼라", "data received, ${t}")

        viewAdapter = TimerListAdapter(timerList)

        timerList.add(TimerListData(t, d, sec, set))
        viewAdapter.notifyItemInserted(timerList.size)

        // delete

    }






}
