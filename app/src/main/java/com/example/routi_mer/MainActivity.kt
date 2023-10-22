package com.example.routi_mer

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SendRoutineListPositionListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var routineList = ArrayList<RoutineRecyclerViewData>()
    private var groupList = ArrayList<GroupListData>()

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar() // toolbar 설정, title 지우기
        setSpinner() // spinner adapter, selected 요소 관리
        binding.btnSettings.setOnClickListener {
            Toast.makeText(this, "setting btn is clicked", Toast.LENGTH_SHORT).show()
        }

        setRoutineRecyclerview()
        // 루틴 리스트 더미데이터 추가
//        routineList.apply {
//            add(RoutineRecyclerViewData("거북목 스트레칭", "개쩌는 효과!"))
//            add(RoutineRecyclerViewData("너무 덥다", "에어컨 각"))
//        }

        val routineDB = RoutineDB.getRoutineList(this)
        if (routineDB != null) {
            val allRoutine = routineDB.RoutineListDao().selectAllRoutine()

            for (i in allRoutine.indices) {
                routineList.add(RoutineRecyclerViewData(allRoutine[i].mainTitle, allRoutine[i].mainDes))
            }
        }

        binding.addRoutine.setOnClickListener {
            val intent = Intent(this, AddRoutineActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        var groupNameList = resources.getStringArray(R.array.routine_group).toMutableList()
        groupList.apply {
            for (i in 1 until groupNameList.size) {
                add(GroupListData(groupNameList[i]))
            }

        }

        binding.btnGroupManage.setOnClickListener {
            // bottomsheetfragment에서 roomdb에 새 그룹 추가해주고
            // 위에 grouplist에는 roomdb에 있는 그룹명을 add해주어 fragment 지웠다 다시 호출해도
            // 그룹명이 유지될 수 있도록 해주기
            val adapter = GroupListAdapter()
            val bottomDialogFragment = AddGroupBottomSheetFragment(adapter)
            adapter.setItem(groupList)
            bottomDialogFragment.show(supportFragmentManager, "TAG")
        }

        // 루틴 추가하고 다시 돌아왔을 때 recyclerview 갱신하기
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val title = it.data?.getStringExtra("title") ?: ""
                val des = it.data?.getStringExtra("des") ?: ""

                routineList.add(RoutineRecyclerViewData(title, des))
                viewAdapter.notifyItemInserted(routineList.size)
            }
        }


    }


    override fun onResume() {
        super.onResume()
        if (GetEditedTitleDes.isRoutineChanged) {
            routineList[GetRoutineItemPosition.routinePos].routineTitle = GetEditedTitleDes.editedTitle
            routineList[GetRoutineItemPosition.routinePos].routineDescription = GetEditedTitleDes.editedDes
            viewAdapter.notifyItemChanged(GetRoutineItemPosition.routinePos)
            GetEditedTitleDes.isRoutineChanged = false
        }

    }






    private fun setToolbar() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false) // toolbar title 제거
    }


    private fun setSpinner() {
        // spinner
        var spinnerGroupData = resources.getStringArray(R.array.routine_group)
        var spinnerAdapter = ArrayAdapter<String>(this, R.layout.spinner_open, spinnerGroupData)
        binding.spinnerGroupSelect.adapter = spinnerAdapter

        binding.spinnerGroupSelect.setSelection(0)
        binding.spinnerGroupSelect.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(this@MainActivity, "${spinnerGroupData[binding.spinnerGroupSelect.selectedItemPosition]} selected", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }


    private fun setRoutineRecyclerview() {
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = RoutineListAdapter(routineList)
        recyclerView = binding.routineList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun sendRoutinePos(routinePos: Int) {
        val routineRView: RecyclerView = findViewById(R.id.routine_list)
        viewManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        viewAdapter = RoutineListAdapter(routineList)
        recyclerView = routineRView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        routineList.removeAt(routinePos)
        viewAdapter.notifyItemRemoved(routinePos)
        viewAdapter.notifyItemChanged(routinePos)
    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item!!.itemId) {
//            R.id.btn_setting -> {
//                Toast.makeText(this, "setting btn is clicked", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }


}