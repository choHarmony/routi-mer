package com.example.routi_mer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.databinding.ActivityAddRoutineBinding
import com.example.routi_mer.databinding.ActivityMainBinding
import com.example.routi_mer.databinding.LayoutDialogSetTimerTitleBinding


class AddRoutineActivity : AppCompatActivity(), SendNewTimerListener, SendPositionToDeleteListener, SendEditTimerListener {

    private lateinit var binding: ActivityAddRoutineBinding
    private lateinit var dialogBinding: LayoutDialogSetTimerTitleBinding
    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var timerList = ArrayList<TimerListData>()

    private val recyclerViewAdapter by lazy { TimerListAdapter(timerList) }
    private val itemTouchHelper by lazy { ItemTouchHelper(ItemTouchCallback(recyclerViewAdapter)) }

    private val addTimerBTSFragment = AddTimerBottomSheetFragment(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoutineBinding.inflate(layoutInflater)
        dialogBinding = LayoutDialogSetTimerTitleBinding.inflate(layoutInflater)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnExit.setOnClickListener {
            finish()
        }

        binding.textRoutineTitle.setOnClickListener {
            setEditTitleDialog()
        }

        setTimerRecyclerview()
//        timerList.apply {
//            add(TimerListData("목 옆으로 당기기", "목을 옆으로 당기면 된다", "20", "3", "", ""))
//        }

        binding.addTimer.setOnClickListener {
            addTimerBTSFragment.show(supportFragmentManager, addTimerBTSFragment.tag)
        }

        val routineDB = RoutineDB.getRoutineList(this)
        binding.btnAdd.setOnClickListener {

            routineDB!!.RoutineListDao().insertRoutine(
                RoutineRoomData(
                    binding.textRoutineTitle.text.toString(),
                    binding.routineDes.text.toString(),
                    binding.routineGroup.text.toString(),
                    timerList.toMutableList()
                )
            )

            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("title", binding.textRoutineTitle.text.toString())
                putExtra("des", binding.routineDes.text.toString())
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()


        }






    }


    private fun setTimerRecyclerview() {
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = recyclerViewAdapter
        recyclerView = binding.timerList.apply {
            //setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        itemTouchHelper.attachToRecyclerView(binding.timerList)

    }


    private fun setEditTitleDialog() {
        val dialog = EditRoutineTitleDialog(this)
        dialog.showDialog(binding.textRoutineTitle.text.toString(), binding.routineDes.text.toString())
        // 후에 db 구축하면 des 부분은 db에서 불러오는 거로 하면 됨!
        dialog.setOnClickListener(object : EditRoutineTitleDialog.OnDialogClickListener {
            override fun onClicked(title: String, des: String, group: String) {
                binding.textRoutineTitle.text = title
                binding.routineDes.text = des
                binding.routineGroup.text = group
                // des는 이 화면에 보이지 않고 데이터베이스에 저장만 해놓을 예정
                // 따라서 여기는 더 추가할 필요 없음
            }

        })
    }


    override fun sendTimerData(t: String, d: String, sec: String, set: String, one: String, full: String) {

        val rView: RecyclerView = findViewById(R.id.timer_list)
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = recyclerViewAdapter
        recyclerView = rView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        itemTouchHelper.attachToRecyclerView(rView)

        timerList.add(TimerListData(t, d, sec, set, one, full))
        viewAdapter.notifyItemInserted(timerList.size)

        // delete

    }

    override fun sendPositionToDelete(pos: Int) {

        val rView: RecyclerView = findViewById(R.id.timer_list)
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = recyclerViewAdapter
        recyclerView = rView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        itemTouchHelper.attachToRecyclerView(rView)

        timerList.removeAt(pos)
        viewAdapter.notifyItemRemoved(pos)
        viewAdapter.notifyItemChanged(pos)

    }

    override fun editTimerData(t: String, d: String, sec: String, set: String, one: String, full: String) {

        val rView: RecyclerView = findViewById(R.id.timer_list)
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = recyclerViewAdapter
        recyclerView = rView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
        itemTouchHelper.attachToRecyclerView(rView)

        timerList[GetTimerItemPosition.pos].timerTitle = t
        timerList[GetTimerItemPosition.pos].timerDescription = d
        timerList[GetTimerItemPosition.pos].timerSec = sec
        timerList[GetTimerItemPosition.pos].timerSet = set
        timerList[GetTimerItemPosition.pos].oneSetMusicTitle = one
        timerList[GetTimerItemPosition.pos].fullSetMusicTitle = full


        viewAdapter.notifyItemChanged(GetTimerItemPosition.pos)

    }


}
