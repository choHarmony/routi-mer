package com.example.routi_mer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.HorizontalScrollView
import android.widget.LinearLayout.HORIZONTAL
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.routi_mer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var routineList = ArrayList<RoutineListData>()
    private var groupList = ArrayList<GroupListData>()

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
        routineList.apply {
            add(RoutineListData("거북목 스트레칭", "개쩌는 효과!"))
            add(RoutineListData("너무 덥다", "에어컨 각"))
            add(RoutineListData("외로운 날들이여", "모두 다 안녕~"))
            add(RoutineListData("거북목 스트레칭", "개쩌는 효과!"))
        }

        val groupNameList = resources.getStringArray(R.array.routine_group).toList()
        groupList.apply {
            for (i in 1 until groupNameList.size) {
                add(GroupListData(groupNameList[i]))
            }

        }

        binding.addRoutine.setOnClickListener {
            val intent = Intent(this, AddRoutineActivity::class.java)
            startActivity(intent)
        }

        binding.btnGroupManage.setOnClickListener {
            val adapter = GroupListAdapter()
            val bottomDialogFragment = AddGroupDialogFragment(adapter)
            adapter.setItem(groupList)
            bottomDialogFragment.show(supportFragmentManager, "TAG")
        }



    }


    // 루틴 추가하고 다시 돌아왔을 때 recyclerview 갱신하기
//    override fun onRestart() {
//        super.onRestart()
//    }







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
                Toast.makeText(this@MainActivity, "${spinnerGroupData[binding.spinnerGroupSelect.selectedItemPosition]} selected", Toast.LENGTH_SHORT).show()
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